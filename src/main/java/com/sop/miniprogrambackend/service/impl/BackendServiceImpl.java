package com.sop.miniprogrambackend.service.impl;

import com.sop.miniprogrambackend.functional.conf.MiniProgramBackendConf;
import com.sop.miniprogrambackend.service.BackendService;
import com.sop.miniprogrambackend.service.ClockInService;
import com.sop.miniprogrambackend.service.CourseService;
import com.sop.miniprogrambackend.service.UserService;
import com.sop.miniprogrambackend.service.domain.ClockInDomain;
import com.sop.miniprogrambackend.service.domain.CourseDomain;
import com.sop.miniprogrambackend.service.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BackendServiceImpl implements BackendService {

    @Autowired
    private MiniProgramBackendConf conf;

    @Autowired
    private UserService userService;

    @Autowired
    private ClockInService clockInService;

    @Autowired
    private CourseService courseService;

    /**
     * 获得会员 id
     * @return
     */
    @Override
    public List<Integer> getMemberIds() {
        return this.userService.getMemberIds();
    }

    /**
     * 获取打卡满 28 次会员数据
     * @param memberIds
     * @return
     */
    @Override
    public Integer getFinishedMemberCount(List<Integer> memberIds) {
        Map<Integer, List<ClockInDomain>> idClockInMap = this.clockInService.getClockIn(memberIds, true);
        if(idClockInMap == null) {
            return 0;
        }
        return idClockInMap.values().stream().filter(clockInDomains ->
                clockInDomains.size() >= this.conf.getMember_finished_clock_in_times()
        ).collect(Collectors.toList()).size();
    }

    /**
     * 获取所有用户
     * 除了 id、nickName、district、school、grade，还要成功打卡数据
     * @return
     */
    @Override
    public List<UserDomain> getUsersInfo() {
        List<UserDomain> userDomainList = this.userService.getUsers();
        Map<Integer, List<ClockInDomain>> idClockInMap = this.clockInService.getClockIn(
                userDomainList.stream().map(UserDomain::getId).collect(Collectors.toList()), true);
        if(idClockInMap != null) {
            for(UserDomain userDomain: userDomainList) {
                if(!idClockInMap.containsKey(userDomain.getId())) {
                    continue;
                }
                userDomain.setClockInDomainList(idClockInMap.get(userDomain.getId()));
            }
        }
        return userDomainList;
    }

    /**
     * 获取已打卡课程列表
     * @return
     */
    @Override
    public List<ClockInDomain> getFinishedClockInList(Integer userId) {
        // 获取打卡记录
        List<Integer> userIds = new ArrayList<>();
        userIds.add(userId);
        Map<Integer, List<ClockInDomain>> idClockInMap = this.clockInService.getClockIn(userIds, true);
        if(idClockInMap == null) {
            return null;
        }
        List<ClockInDomain> clockInDomainList = idClockInMap.get(userId);
        // 填充标题和内容
        Map<Integer, CourseDomain> courseDomainMap = this.courseService.getCourseList(
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
}
