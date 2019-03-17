package com.acui.merchant.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "news", schema = "merchant", catalog = "")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class NewsEntity {
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String merchantId;
    private Timestamp createTime;
    private String msg;
    private String newsType;
    private String tradeId;

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
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
    @Column(name = "news_type")
    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    @Basic
    @Column(name = "trade_id")
    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsEntity that = (NewsEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(merchantId, that.merchantId) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(msg, that.msg) &&
                Objects.equals(newsType, that.newsType) &&
                Objects.equals(tradeId, that.tradeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, merchantId, createTime, msg, newsType, tradeId);
    }
}
