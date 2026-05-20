import java.util.Date;

/**
 * 图书漂流平台用户实体类
 * 包含账号、密码、信用积分、角色、注册时间、账号状态
 */
public class User {
    private Integer uid;
    private String userName;
    private String userPwd;
    private String phone;
    private Integer creditScore;
    private String role;
    private Date registerTime;
    private String accountStatus;

    public User() {}

    public User(Integer uid, String userName, String userPwd, String phone, Integer creditScore, String role, Date registerTime, String accountStatus) {
        this.uid = uid;
        this.userName = userName;
        this.userPwd = userPwd;
        this.phone = phone;
        this.creditScore = creditScore;
        this.role = role;
        this.registerTime = registerTime;
        this.accountStatus = accountStatus;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}
