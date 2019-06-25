package com.sop.miniprogrambackend.service.impl;

import com.sop.miniprogrambackend.model.ClockInDOMapper;
import com.sop.miniprogrambackend.model.data.ClockInDO;
import com.sop.miniprogrambackend.service.ClockInService;
import com.sop.miniprogrambackend.service.domain.ClockInDomain;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClockInServiceImpl implements ClockInService {

    @Autowired
    private ClockInDOMapper clockInDOMapper;

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
        List<ClockInDO> clockInDOList = null;
        if(needFinished) {
            clockInDOList = this.clockInDOMapper.selectDoneByUserIds(userIds);
        } else {
            clockInDOList = this.clockInDOMapper.selectByUserIds(userIds);
        }
        List<ClockInDomain> clockInDomainList = clockInDOList.stream().map(this::convertFromDataObject).collect(Collectors.toList());
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

    public ClockInDomain convertFromDataObject(ClockInDO clockInDO) {
        if(clockInDO == null) {
            return null;
        }
        ClockInDomain clockInDomain = new ClockInDomain();
        BeanUtils.copyProperties(clockInDO, clockInDomain);
        clockInDomain.setDone(clockInDO.getDone().intValue() == 1 ? true : false);
        return clockInDomain;
    }
}
