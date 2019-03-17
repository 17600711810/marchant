package com.acui.merchant.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "burse", schema = "merchant", catalog = "")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class BurseEntity {
    private String merchantId;
    private Integer totalTopUp;
    private Integer balance;
    private Integer totalRedPacket;

    @Id
    @Column(name = "merchant_id")
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Basic
    @Column(name = "total_top_up")
    public Integer getTotalTopUp() {
        return totalTopUp;
    }

    public void setTotalTopUp(Integer totalTopUp) {
        this.totalTopUp = totalTopUp;
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
    @Column(name = "total_red_packet")
    public Integer getTotalRedPacket() {
        return totalRedPacket;
    }

    public void setTotalRedPacket(Integer totalRedPacket) {
        this.totalRedPacket = totalRedPacket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BurseEntity that = (BurseEntity) o;
        return Objects.equals(merchantId, that.merchantId) &&
                Objects.equals(totalTopUp, that.totalTopUp) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(totalRedPacket, that.totalRedPacket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantId, totalTopUp, balance, totalRedPacket);
    }
}
