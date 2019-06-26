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
    public Map<Integer, CourseDomain> getCourseList(List<Integer> courseIds);
}
