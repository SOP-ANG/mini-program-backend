package com.sop.miniprogrambackend.model.data;

public class UserDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_user.id
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_user.nick_name
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    private String nickName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_user.gender
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    private Byte gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_user.avatar_url
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    private String avatarUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_user.country
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    private String country;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_user.province
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    private String province;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_user.city
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_user.district
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    private String district;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_user.school
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    private String school;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_user.grade
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    private String grade;

    private String ignoreTs;

    private String hasGiveThumbUp;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_user.id
     *
     * @return the value of mp_user.id
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_user.id
     *
     * @param id the value for mp_user.id
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_user.nick_name
     *
     * @return the value of mp_user.nick_name
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_user.nick_name
     *
     * @param nickName the value for mp_user.nick_name
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_user.gender
     *
     * @return the value of mp_user.gender
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_user.gender
     *
     * @param gender the value for mp_user.gender
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_user.avatar_url
     *
     * @return the value of mp_user.avatar_url
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_user.avatar_url
     *
     * @param avatarUrl the value for mp_user.avatar_url
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_user.country
     *
     * @return the value of mp_user.country
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_user.country
     *
     * @param country the value for mp_user.country
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_user.province
     *
     * @return the value of mp_user.province
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_user.province
     *
     * @param province the value for mp_user.province
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_user.city
     *
     * @return the value of mp_user.city
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_user.city
     *
     * @param city the value for mp_user.city
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_user.district
     *
     * @return the value of mp_user.district
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public String getDistrict() {
        return district;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_user.district
     *
     * @param district the value for mp_user.district
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_user.school
     *
     * @return the value of mp_user.school
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public String getSchool() {
        return school;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_user.school
     *
     * @param school the value for mp_user.school
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_user.grade
     *
     * @return the value of mp_user.grade
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public String getGrade() {
        return grade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_user.grade
     *
     * @param grade the value for mp_user.grade
     *
     * @mbg.generated Sun Jun 23 20:28:27 CST 2019
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getIgnoreTs() {
        return ignoreTs;
    }

    public void setIgnoreTs(String ignoreTs) {
        this.ignoreTs = ignoreTs;
    }

    public String getHasGiveThumbUp() {
        return hasGiveThumbUp;
    }

    public void setHasGiveThumbUp(String hasGiveThumbUp) {
        this.hasGiveThumbUp = hasGiveThumbUp;
    }
}