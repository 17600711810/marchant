package com.acui.merchant.dao.entity;

import com.acui.merchant.common.utils.HashUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "merchant_info", schema = "merchant", catalog = "")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class MerchantInfoEntity {

    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String merchantName;
    private String password;
    private String phoneNumber;
    private Timestamp loginTime;
    private Timestamp createTime;
    private Timestamp purchaseTime;
    private Timestamp dueTime;
    private String openId;
    private String unionId;
    private String userName;
    private Byte state;
    private String payPassword;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "merchant_name")
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = HashUtils.sha1HashEncryPassword(password);
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "login_time")
    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "purchase_time")
    public Timestamp getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Timestamp purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    @Basic
    @Column(name = "due_time")
    public Timestamp getDueTime() {
        return dueTime;
    }

    public void setDueTime(Timestamp dueTime) {
        this.dueTime = dueTime;
    }

    @Basic
    @Column(name = "open_id")
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Basic
    @Column(name = "union_id")
    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Basic
    @Column(name = "pay_password")
    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MerchantInfoEntity that = (MerchantInfoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(merchantName, that.merchantName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(loginTime, that.loginTime) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(purchaseTime, that.purchaseTime) &&
                Objects.equals(dueTime, that.dueTime) &&
                Objects.equals(openId, that.openId) &&
                Objects.equals(unionId, that.unionId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(state, that.state) &&
                Objects.equals(payPassword, that.payPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, merchantName, password, phoneNumber, loginTime, createTime, purchaseTime, dueTime, openId, unionId, userName, state, payPassword);
    }
}
