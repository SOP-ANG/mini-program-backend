package com.sop.miniprogrambackend.model.data;

public class CourseDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_course.id
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_course.title
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_course.grade
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    private String grade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_course.content
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_course.id
     *
     * @return the value of mp_course.id
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_course.id
     *
     * @param id the value for mp_course.id
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_course.title
     *
     * @return the value of mp_course.title
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_course.title
     *
     * @param title the value for mp_course.title
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_course.grade
     *
     * @return the value of mp_course.grade
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    public String getGrade() {
        return grade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_course.grade
     *
     * @param grade the value for mp_course.grade
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_course.content
     *
     * @return the value of mp_course.content
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_course.content
     *
     * @param content the value for mp_course.content
     *
     * @mbg.generated Tue Jun 25 14:00:02 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}