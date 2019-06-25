package com.sop.miniprogrambackend.service;

import com.sop.miniprogrambackend.service.domain.UserDomain;

import java.util.List;

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
}
