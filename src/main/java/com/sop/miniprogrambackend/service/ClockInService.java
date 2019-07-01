package com.sop.miniprogrambackend.service;

import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.service.domain.ClockInDomain;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 定义打卡服务规范
 */
public interface ClockInService {
    /**
     * 获取会员完成的打卡任务
     * @param memberIds
     * @return
     */
    public Map<Integer, List<ClockInDomain>> getClockIn(List<Integer> memberIds, boolean needFinished);

    /**
     * 获取用户的打卡任务（方法重载）
     * @param userIds
     * @return
     */
    public Map<Integer, List<ClockInDomain>> getClockIn(List<Integer> userIds);

    /**
     * 根据用户 id 和 年级生成课程列表
     * @param userId
     * @param grade
     */
    public void generateClockInList(Integer userId, String grade);

    /**
     * 根据用户 id 删除打卡记录
     * @param userId
     */
    public void deleteByUserId(Integer userId);

    /**
     * 根据用户 id 获取已打卡课程
     * @param userId
     * @return
     */
    public List<ClockInDomain> getClockInWithCourseByUserId(Integer userId, boolean needFinished) throws ResponseException;

    /**
     * 根据用户 id 获取全部打卡课程（方法重载）
     * @param userId
     * @return
     */
    public List<ClockInDomain> getClockInWithCourseByUserId(Integer userId) throws ResponseException;

    /**
     * 分享朋友圈成功后标记打卡成功
     * @param clockInDomain
     * @return 返回成功打卡次数
     */
    public Integer clockInDone(ClockInDomain clockInDomain);

    /**
     * 上传录音
     * @param file
     * @param userId
     * @param courseId
     * @return
     */
    public String uploadRecord(MultipartFile file, Integer userId, Integer courseId) throws ResponseException;

    /**
     * 储存录音文件路径
     * @param userId
     * @param courseId
     * @param recordPath
     */
    public void saveClockInRecord(Integer userId, Integer courseId, String recordPath);
}
