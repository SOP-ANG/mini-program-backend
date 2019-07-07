package com.sop.miniprogrambackend.service.impl;

import com.sop.miniprogrambackend.functional.conf.MiniProgramBackendConf;
import com.sop.miniprogrambackend.functional.response.EnumResponseError;
import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.model.ClockInDOMapper;
import com.sop.miniprogrambackend.model.data.ClockInDO;
import com.sop.miniprogrambackend.service.ClockInService;
import com.sop.miniprogrambackend.service.CourseService;
import com.sop.miniprogrambackend.service.domain.ClockInDomain;
import com.sop.miniprogrambackend.service.domain.CourseDomain;
import com.sop.miniprogrambackend.service.domain.UserDomain;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClockInServiceImpl implements ClockInService {

    @Autowired
    private MiniProgramBackendConf conf;

    @Autowired
    private ClockInDOMapper clockInDOMapper;

    @Autowired
    private CourseService courseService;

    /**
     * 传入会员 id，获得会员已经完成的打卡任务，返回会员 id 和打卡任务的 map
     * @param userIds
     * @param needFinished
     * @return
     */
    @Override
    public Map<Integer, List<ClockInDomain>> getClockIn(List<Integer> userIds, boolean needFinished) {
        if(userIds == null || userIds.size() == 0) {
            return null;
        }
        List<ClockInDO> clockInDOList;
        if(needFinished) {
            clockInDOList = this.clockInDOMapper.selectDoneByUserIds(userIds);
        } else {
            clockInDOList = this.clockInDOMapper.selectByUserIds(userIds);
        }
        List<ClockInDomain> clockInDomainList = clockInDOList.stream().map(
                this::convertFromDataObject).collect(Collectors.toList());
        return clockInDomainList.stream().collect(Collectors.groupingBy(ClockInDomain::getUserId));
    }

    /**
     * 传入用户 id，获得用户所有的打卡任务，返回用户 id 和打卡任务的 map
     * @param userIds
     * @return
     */
    @Override
    public Map<Integer, List<ClockInDomain>> getClockIn(List<Integer> userIds) {
        return this.getClockIn(userIds, false);
    }

    /**
     * 根据用户 id 和 年级生成课程列表
     * 这是初始化流程，为预防老数据错误，先清除一下可能存在的残余数据，再生成新的
     * @param userId
     * @param grade
     */
    @Override
    @Transactional
    public void generateClockInList(Integer userId, String grade) {
        // 清理老数据
        this.deleteByUserId(userId);

        // 根据用户 id 和年级生成课程列表
        // 获取年级对应的课程
        List<CourseDomain> courseDomainList = this.courseService.getCourseListByGrade(grade);
        if(courseDomainList == null || courseDomainList.size() == 0) {
            return;
        }
        for(CourseDomain courseDomain: courseDomainList) {
            ClockInDomain clockInDomain = new ClockInDomain();
            clockInDomain.setUserId(userId);
            clockInDomain.setCourseId(courseDomain.getId());
            clockInDomain.setDone(false);
            this.clockInDOMapper.insertSelective(this.convertFromDomain(clockInDomain));
        }
    }

    /**
     * 根据用户 id 删除打卡记录
     * @param userId
     */
    @Override
    @Transactional
    public void deleteByUserId(Integer userId) {
        this.clockInDOMapper.deleteByUserId(userId);
    }

    /**
     * 根据用户 id 获取全部打卡课程
     * @param userId
     * @return
     */
    @Override
    public List<ClockInDomain> getClockInWithCourseByUserId(Integer userId,
                                                            boolean needFinished) throws ResponseException {
        // 获取打卡记录
        List<Integer> userIds = new ArrayList<>();
        userIds.add(userId);
        Map<Integer, List<ClockInDomain>> clockInMap = this.getClockIn(userIds, needFinished);
        if(clockInMap == null) {
            throw new ResponseException("用户没有课程", EnumResponseError.DATA_VALIDATION_ERROR);
        }
        List<ClockInDomain> clockInDomainList = clockInMap.get(userId);
        // 填充标题和内容
        Map<Integer, CourseDomain> courseDomainMap = this.courseService.getCourseListByIds(
                clockInDomainList.stream().map(ClockInDomain::getCourseId).collect(Collectors.toList()));
        for(ClockInDomain clockInDomain: clockInDomainList) {
            if(clockInDomain.getCourseId() == 0 || !courseDomainMap.containsKey(clockInDomain.getCourseId())) {
                continue;
            }
            CourseDomain courseDomain = courseDomainMap.get(clockInDomain.getCourseId());
            clockInDomain.setTitle(courseDomain.getTitle());
            clockInDomain.setContent(courseDomain.getContent());
        }
        return clockInDomainList;
    }

    /**
     * 根据用户 id 获取全部打卡课程（方法重载）
     * @param userId
     * @return
     */
    @Override
    public List<ClockInDomain> getClockInWithCourseByUserId(Integer userId) throws ResponseException {
        return this.getClockInWithCourseByUserId(userId, false);
    }

    /**
     * 分享朋友圈成功后标记打卡成功
     * @param clockInDomain
     * @return 返回成功打卡次数
     */
    @Override
    @Transactional
    public Integer clockInDone(ClockInDomain clockInDomain) {
        clockInDomain.setDone(true);
        clockInDomain.setClockInTs(Long.toString(new DateTime().getMillis()));
        this.clockInDOMapper.updateDoneByUserIdAndCourseIdSelective(this.convertFromDomain(clockInDomain));

        List<Integer> userIds = new ArrayList<>();
        userIds.add(clockInDomain.getUserId());
        Map<Integer, List<ClockInDomain>> idClockInMap = this.getClockIn(userIds, true);
        if(idClockInMap != null && idClockInMap.containsKey(clockInDomain.getUserId())) {
            return idClockInMap.get(clockInDomain.getUserId()).size();
        }
        return 0;
    }

    /**
     * 上传录音
     * @param file
     * @param userId
     * @param courseId
     * @return
     */
    @Override
    public String uploadRecord(MultipartFile file, Integer userId, Integer courseId) throws ResponseException {
        String fileName = file.getOriginalFilename();
        String targetFileName = MessageFormat.format(
                this.conf.getRecord_file_name(),
                new Object[]{"Record", userId, courseId, fileName.substring(fileName.lastIndexOf(".") + 1)});
        File targetFile = new File(this.conf.getRecord_path(), targetFileName);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            throw new ResponseException("录音文件存储失败", EnumResponseError.DATA_CONVERT_ERROR);
        }
        return this.conf.getRelative_record_path() + targetFileName;
    }

    /**
     * 储存录音文件路径
     * @param userId
     * @param courseId
     * @param recordPath
     */
    @Override
    @Transactional
    public void saveClockInRecord(Integer userId, Integer courseId, String recordPath) {
        ClockInDO clockInDO = new ClockInDO();
        clockInDO.setUserId(userId);
        clockInDO.setCourseId(courseId);
        clockInDO.setRecordPath(recordPath);
        this.clockInDOMapper.updateRecordPathByUserIdAndCourseIdSelective(clockInDO);
    }

    public ClockInDomain convertFromDataObject(ClockInDO clockInDO) {
        if(clockInDO == null) {
            return null;
        }
        ClockInDomain clockInDomain = new ClockInDomain();
        BeanUtils.copyProperties(clockInDO, clockInDomain);
        clockInDomain.setDone(clockInDO.getDone().intValue() == 1 ? true : false);
        return clockInDomain;
    }

    public ClockInDO convertFromDomain(ClockInDomain clockInDomain) {
        if(clockInDomain == null) {
            return null;
        }
        ClockInDO clockInDO = new ClockInDO();
        BeanUtils.copyProperties(clockInDomain, clockInDO);
        clockInDO.setDone((byte) (clockInDomain.isDone() ? 1 : 0));
        return clockInDO;
    }
}
