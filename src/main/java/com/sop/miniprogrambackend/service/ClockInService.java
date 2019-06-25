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
}
