package com.acui.merchant.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cash_recode", schema = "merchant", catalog = "")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CashRecodeEntity {
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String merchantId;
    private String recodeType;
    private Integer money;
    private String msg;
    private Timestamp createTime;
    private Byte state;
    private String unionId;
    private String openId;
    private String wxErrorCode;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "merchant_id")
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Basic
    @Column(name = "recode_type")
    public String getRecodeType() {
        return recodeType;
    }

    public void setRecodeType(String recodeType) {
        this.recodeType = recodeType;
    }

    @Basic
    @Column(name = "money")
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Basic
    @Column(name = "msg")
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
    @Column(name = "state")
    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
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
    @Column(name = "open_id")
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Basic
    @Column(name = "wx_error_code")
    public String getWxErrorCode() {
        return wxErrorCode;
    }

    public void setWxErrorCode(String wxErrorCode) {
        this.wxErrorCode = wxErrorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashRecodeEntity that = (CashRecodeEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(merchantId, that.merchantId) &&
                Objects.equals(recodeType, that.recodeType) &&
                Objects.equals(money, that.money) &&
                Objects.equals(msg, that.msg) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(state, that.state) &&
                Objects.equals(unionId, that.unionId) &&
                Objects.equals(openId, that.openId) &&
                Objects.equals(wxErrorCode, that.wxErrorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, merchantId, recodeType, money, msg, createTime, state, unionId, openId, wxErrorCode);
    }
}
