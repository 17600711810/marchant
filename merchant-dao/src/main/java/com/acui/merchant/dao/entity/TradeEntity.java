package com.acui.merchant.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "trade", catalog = "")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class TradeEntity {

    private String id;
    private String merchant;
    private Integer balance;
    private String tradeState;
    private String tradeType;
    private String userId;
    private String userName;
    private Timestamp createTime;
    private Integer wxErrorCode;
    private Timestamp responseTime;
    private String openId;
    private String unionId;

    @Id
    @Column(name = "id", length = 32)
    @GeneratedValue(generator = "jpa-uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "merchant")
    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    @Basic
    @Column(name = "balance")
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "trade_state")
    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    @Basic
    @Column(name = "trade_type")
    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "wx_error_code")
    public Integer getWxErrorCode() {
        return wxErrorCode;
    }

    public void setWxErrorCode(Integer wxErrorCode) {
        this.wxErrorCode = wxErrorCode;
    }

    @Basic
    @Column(name = "response_time")
    public Timestamp getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Timestamp responseTime) {
        this.responseTime = responseTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeEntity that = (TradeEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(merchant, that.merchant) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(tradeState, that.tradeState) &&
                Objects.equals(tradeType, that.tradeType) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(wxErrorCode, that.wxErrorCode) &&
                Objects.equals(responseTime, that.responseTime) &&
                Objects.equals(openId, that.openId) &&
                Objects.equals(unionId, that.unionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, merchant, balance, tradeState, tradeType, userId, userName, createTime, wxErrorCode, responseTime, openId, unionId);
    }
}
