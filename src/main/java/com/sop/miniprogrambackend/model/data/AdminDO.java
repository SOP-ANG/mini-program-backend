package com.sop.miniprogrambackend.model.data;

public class AdminDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_admin.id
     *
     * @mbg.generated Thu Jul 18 00:18:55 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_admin.username
     *
     * @mbg.generated Thu Jul 18 00:18:55 CST 2019
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mp_admin.password
     *
     * @mbg.generated Thu Jul 18 00:18:55 CST 2019
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_admin.id
     *
     * @return the value of mp_admin.id
     *
     * @mbg.generated Thu Jul 18 00:18:55 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_admin.id
     *
     * @param id the value for mp_admin.id
     *
     * @mbg.generated Thu Jul 18 00:18:55 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_admin.username
     *
     * @return the value of mp_admin.username
     *
     * @mbg.generated Thu Jul 18 00:18:55 CST 2019
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_admin.username
     *
     * @param username the value for mp_admin.username
     *
     * @mbg.generated Thu Jul 18 00:18:55 CST 2019
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mp_admin.password
     *
     * @return the value of mp_admin.password
     *
     * @mbg.generated Thu Jul 18 00:18:55 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mp_admin.password
     *
     * @param password the value for mp_admin.password
     *
     * @mbg.generated Thu Jul 18 00:18:55 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}