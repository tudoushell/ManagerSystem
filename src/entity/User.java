package entity;

public class User {
    private String userAccount;
    private String userPwd;
    private String empNo;
    private String roleId;
    private String createTime;

    public User() {
        super();
    }

    public User(String userAccount, String userPwd, String empNo, String roleId, String createTime) {
        this.userAccount = userAccount;
        this.userPwd = userPwd;
        this.empNo = empNo;
        this.roleId = roleId;
        this.createTime = createTime;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userAccount='" + userAccount + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", empNo='" + empNo + '\'' +
                ", roleId='" + roleId + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
