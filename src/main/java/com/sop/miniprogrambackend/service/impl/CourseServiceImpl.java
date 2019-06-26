package com.sop.miniprogrambackend.service.impl;

import com.sop.miniprogrambackend.model.CourseDOMapper;
import com.sop.miniprogrambackend.model.data.CourseDO;
import com.sop.miniprogrambackend.service.CourseService;
import com.sop.miniprogrambackend.service.domain.CourseDomain;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDOMapper courseDOMapper;

    /**
     * 获取课文（其他条件请用方法重载）
     * @param courseIds
     * @return
     */
    @Override
    public Map<Integer, CourseDomain> getCourseListByIds(List<Integer> courseIds) {
        if(courseIds == null || courseIds.size() == 0) {
            return null;
        }
        List<CourseDO> courseDOList = this.courseDOMapper.selectByCourseIds(courseIds);
        List<CourseDomain> courseDomainList = courseDOList.stream().map(
                this::convertFromDataObject).collect(Collectors.toList());
        return courseDomainList.stream().collect(Collectors.toMap(CourseDomain::getId, Function.identity()));
    }

    /**
     * 根据年级获取课程
     * @param grade
     * @return
     */
    @Override
    public List<CourseDomain> getCourseListByGrade(String grade) {
        List<CourseDO> courseDOList = this.courseDOMapper.selectIdByGrade(grade);
        return courseDOList.stream().map(this::convertFromDataObject).collect(Collectors.toList());
    }

    public CourseDomain convertFromDataObject(CourseDO courseDO) {
        if(courseDO == null) {
            return null;
        }
        CourseDomain courseDomain = new CourseDomain();
        BeanUtils.copyProperties(courseDO, courseDomain);
        return courseDomain;
    }
}
