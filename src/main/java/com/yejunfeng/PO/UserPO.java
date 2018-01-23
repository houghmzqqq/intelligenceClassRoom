package com.yejunfeng.PO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="user")
public class UserPO {
    @Id @Column(name="user_id")
    private String userId;
    @Column(name="user_name")
    private String userName;
    private String account;
    private String password;
    @Column(name="user_level")
    private String userLevel;
    @Column(name="create_date")
    private Timestamp createDate;

    public UserPO() {
    }

    public UserPO(String userId, String userName, String account, String password, String userLevel, Timestamp createDate) {
        this.userId = userId;
        this.userName = userName;
        this.account = account;
        this.password = password;
        this.userLevel = userLevel;
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserLevel() {
        return userLevel;
    }
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
    public Timestamp getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }


}
