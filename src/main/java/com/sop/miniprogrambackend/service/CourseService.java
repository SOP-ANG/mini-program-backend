package com.sop.miniprogrambackend.service;

import com.sop.miniprogrambackend.service.domain.CourseDomain;

import java.util.List;
import java.util.Map;

/**
 * 定义课程服务规范
 */
public interface CourseService {
    /**
     * 获取课文（其他条件请用方法重载）
     * @param courseIds
     * @return
     */
    public Map<Integer, CourseDomain> getCourseListByIds(List<Integer> courseIds);

    /**
     * 根据年级获取课程
     * @param grade
     * @return
     */
    public List<CourseDomain> getCourseListByGrade(String grade);
}
