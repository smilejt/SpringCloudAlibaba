package com.abandon.jt.auth.entity;

/**
 * @createData: 2019-06-24 17:04
 * @author: LongJunTao
 * @Description:
 */
public class UserInfo {
    /**
     * 自增长主键
     */
    private Long id;
    /**
     * 登录的账号
     */
    private String account;
    /**
     * 注册的昵称
     */
    private String userName;
    /**
     * 登录时的密码，不持久化到数据库
     */
    private String plainPassword;
    /**
     * 加密后的密码
     */
    private String password;
    /**
     * 用于加密的盐
     */
    private String salt;
    /**
     * 手机号
     */
    private String iphone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户来自的平台
     */
    private String platform;
    /**
     * 用户注册时间
     */
    private String createdDate;
    /**
     * 用户最后一次登录时间
     */
    private String updatedDate;
    /**
     * 版本
     */
    private String version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", userName='" + userName + '\'' +
                ", plainPassword='" + plainPassword + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", iphone='" + iphone + '\'' +
                ", email='" + email + '\'' +
                ", platform='" + platform + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
