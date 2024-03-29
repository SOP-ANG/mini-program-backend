package com.sop.miniprogrambackend.model;

import com.sop.miniprogrambackend.model.data.ClockInDO;

import java.util.List;

public interface ClockInDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_clock_in
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    int deleteByUserId(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_clock_in
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    int insert(ClockInDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_clock_in
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    int insertSelective(ClockInDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_clock_in
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    ClockInDO selectByPrimaryKey(Integer id);

    List<ClockInDO> selectDoneByUserIds(List<Integer> userIds);

    List<ClockInDO> selectByUserIds(List<Integer> userIds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_clock_in
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    int updateByPrimaryKeySelective(ClockInDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_clock_in
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    int updateByPrimaryKey(ClockInDO record);

    int updateDoneByUserIdAndCourseIdSelective(ClockInDO record);

    int updateRecordPathByUserIdAndCourseIdSelective(ClockInDO record);
}