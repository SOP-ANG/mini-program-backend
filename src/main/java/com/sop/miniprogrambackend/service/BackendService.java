package com.sop.miniprogrambackend.service;

import com.sop.miniprogrambackend.service.domain.ClockInDomain;
import com.sop.miniprogrambackend.service.domain.UserDomain;

import java.util.List;

/**
 * 定义后台服务规范
 */
public interface BackendService {
    /**
     * 获取会员 id
     * @return
     */
    public List<Integer> getMemberIds();

    /**
     * 获取打卡满 28 次会员数
     * @param memberIds
     * @return
     */
    public Integer getFinishedMemberCount(List<Integer> memberIds);

    /**
     * 获取所有用户
     * 除了 id、nickName、district、school、grade，还要成功打卡数据
     * @return
     */
    public List<UserDomain> getUsersInfo();

    /**
     * 获取已打卡课程列表
     * @return
     */
    public List<ClockInDomain> getFinishedClockInList(Integer userId);
}
