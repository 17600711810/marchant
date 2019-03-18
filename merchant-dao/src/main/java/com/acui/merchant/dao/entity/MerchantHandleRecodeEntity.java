package com.acui.merchant.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "merchant_handle_recode", catalog = "")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class MerchantHandleRecodeEntity {

    private String id;
    private String merchantId;
    private String handleType;
    private String msg;
    private Integer balance;
    private Integer number;

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
    @Column(name = "merchant_id")
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Basic
    @Column(name = "handle_type")
    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
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
    @Column(name = "balance")
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MerchantHandleRecodeEntity that = (MerchantHandleRecodeEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(merchantId, that.merchantId) &&
                Objects.equals(handleType, that.handleType) &&
                Objects.equals(msg, that.msg) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, merchantId, handleType, msg, balance, number);
    }
}
