package com.sop.miniprogrambackend.service;

import com.sop.miniprogrambackend.functional.response.ResponseException;
import com.sop.miniprogrambackend.service.domain.ClockInDomain;

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
}
