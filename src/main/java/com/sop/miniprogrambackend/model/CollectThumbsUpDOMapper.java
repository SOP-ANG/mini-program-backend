package com.sop.miniprogrambackend.model;

import com.sop.miniprogrambackend.model.data.CollectThumbsUpDO;

import java.util.List;

public interface CollectThumbsUpDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_collect_thumbs_up
     *
     * @mbg.generated Wed Jun 26 22:06:08 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_collect_thumbs_up
     *
     * @mbg.generated Wed Jun 26 22:06:08 CST 2019
     */
    int insert(CollectThumbsUpDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_collect_thumbs_up
     *
     * @mbg.generated Wed Jun 26 22:06:08 CST 2019
     */
    int insertSelective(CollectThumbsUpDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_collect_thumbs_up
     *
     * @mbg.generated Wed Jun 26 22:06:08 CST 2019
     */
    CollectThumbsUpDO selectByPrimaryKey(Integer id);

    CollectThumbsUpDO selectByUserId(Integer userId);

    List<CollectThumbsUpDO> selectOrderByThumbsUpCount();

    CollectThumbsUpDO getThumbsUpCountById(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_collect_thumbs_up
     *
     * @mbg.generated Wed Jun 26 22:06:08 CST 2019
     */
    int updateByPrimaryKeySelective(CollectThumbsUpDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_collect_thumbs_up
     *
     * @mbg.generated Wed Jun 26 22:06:08 CST 2019
     */
    int updateByPrimaryKeyWithBLOBs(CollectThumbsUpDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mp_collect_thumbs_up
     *
     * @mbg.generated Wed Jun 26 22:06:08 CST 2019
     */
    int updateByPrimaryKey(CollectThumbsUpDO record);

    int updateThumbsUpCountByPrimaryKeySelective(CollectThumbsUpDO record);
}