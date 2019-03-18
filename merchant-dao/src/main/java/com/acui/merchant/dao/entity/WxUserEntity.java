package com.acui.merchant.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "wx_user", catalog = "")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class WxUserEntity {

    private String id;
    private String userName;
    private String attentionTime;
    private String openId;
    private String unionId;
    private String phoneNumber;
    private String state;
    private Integer totalGetBanlance;

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
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "attention_time")
    public String getAttentionTime() {
        return attentionTime;
    }

    public void setAttentionTime(String attentionTime) {
        this.attentionTime = attentionTime;
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
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "total_get_banlance")
    public Integer getTotalGetBanlance() {
        return totalGetBanlance;
    }

    public void setTotalGetBanlance(Integer totalGetBanlance) {
        this.totalGetBanlance = totalGetBanlance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WxUserEntity that = (WxUserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(attentionTime, that.attentionTime) &&
                Objects.equals(openId, that.openId) &&
                Objects.equals(unionId, that.unionId) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(state, that.state) &&
                Objects.equals(totalGetBanlance, that.totalGetBanlance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, attentionTime, openId, unionId, phoneNumber, state, totalGetBanlance);
    }
}
