package com.acui.merchant.dao.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "merchant_activity", catalog = "")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class MerchantInfActivityEntity {

    private String id;
    private String merchantId;
    private String activityTitle;//活动标题
    private String handlerTitle;//页面标题
    private String assistantHandlerTitle;//副标题
    private Timestamp activityStartTime;//活动开始时间
    private Timestamp activityEndTime;//活动结束时间
    private Long tradeLifeTime;//订单有效时间
    private String activityBackgroundType;//背景图类型 0 为使用上传图片，1 2 3 默认背景类型
    private String backgroundImgUrl;//上传图片的url
    private String activityRuleDescription;//活动规则说明
    private String redPacketMoney;//红包金额 固定红包直接使用值，随机红包使用“-”分割起始金额-最高金额
    private String redPacketType = "1";//红包类型
    private String accessDescription;//返现成功说明
    //限制规则
    private Boolean isEnterOrderNumber = true;//是否必须输入订单号
    private Boolean isEnterWangwangNumber = false;//是否必须输入旺旺号
    private Integer imgNumber = 1;//图片数量
    private Boolean isShowGzhTwoDimensionCode = true;//是否展示公众号二维码
    private Boolean isAutomaticReview = false;//是否自动审核
    private Boolean useState = true;//是否使用




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
    @Basic
    @Column(name = "activity_title")
    public String getActivityTitle() {
        return activityTitle;
    }
    @Basic
    @Column(name = "handler_title")
    public String getHandlerTitle() {
        return handlerTitle;
    }
    @Basic
    @Column(name = "assistant_handler_title")
    public String getAssistantHandlerTitle() {
        return assistantHandlerTitle;
    }
    @Basic
    @Column(name = "activity_start_time")
    public Timestamp getActivityStartTime() {
        return activityStartTime;
    }
    @Basic
    @Column(name = "activity_end_time")
    public Timestamp getActivityEndTime() {
        return activityEndTime;
    }
    @Basic
    @Column(name = "trade_life_time")
    public Long getTradeLifeTime() {
        return tradeLifeTime;
    }
    @Basic
    @Column(name = "activity_background_type")
    public String getActivityBackgroundType() {
        return activityBackgroundType;
    }
    @Basic
    @Column(name = "background_img_url")
    public String getBackgroundImgUrl() {
        return backgroundImgUrl;
    }
    @Basic
    @Column(name = "activity_rule_description")
    public String getActivityRuleDescription() {
        return activityRuleDescription;
    }
    @Basic
    @Column(name = "red_packet_money")
    public String getRedPacketMoney() {
        return redPacketMoney;
    }
    @Basic
    @Column(name = "red_packet_type")
    public String getRedPacketType() {
        return redPacketType;
    }
    @Basic
    @Column(name = "access_description")
    public String getAccessDescription() {
        return accessDescription;
    }
    @Basic
    @Column(name = "enter_order_number")
    public Boolean getEnterOrderNumber() {
        return isEnterOrderNumber;
    }
    @Basic
    @Column(name = "enter_Wangwang_number")
    public Boolean getEnterWangwangNumber() {
        return isEnterWangwangNumber;
    }
    @Basic
    @Column(name = "img_number")
    public Integer getImgNumber() {
        return imgNumber;
    }
    @Basic
    @Column(name = "show_gzh_two_dimension_code")
    public Boolean getShowGzhTwoDimensionCode() {
        return isShowGzhTwoDimensionCode;
    }
    @Basic
    @Column(name = "automatic_review")
    public Boolean getAutomaticReview() {
        return isAutomaticReview;
    }
    @Basic
    @Column(name = "use_state")
    public Boolean getUseState() {
        return useState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MerchantInfActivityEntity that = (MerchantInfActivityEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(merchantId, that.merchantId) &&
                Objects.equals(activityTitle, that.activityTitle) &&
                Objects.equals(handlerTitle, that.handlerTitle) &&
                Objects.equals(assistantHandlerTitle, that.assistantHandlerTitle) &&
                Objects.equals(activityStartTime, that.activityStartTime) &&
                Objects.equals(activityEndTime, that.activityEndTime) &&
                Objects.equals(tradeLifeTime, that.tradeLifeTime) &&
                Objects.equals(activityBackgroundType, that.activityBackgroundType) &&
                Objects.equals(backgroundImgUrl, that.backgroundImgUrl) &&
                Objects.equals(activityRuleDescription, that.activityRuleDescription) &&
                Objects.equals(redPacketMoney, that.redPacketMoney) &&
                Objects.equals(redPacketType, that.redPacketType) &&
                Objects.equals(accessDescription, that.accessDescription) &&
                Objects.equals(isEnterOrderNumber, that.isEnterOrderNumber) &&
                Objects.equals(isEnterWangwangNumber, that.isEnterWangwangNumber) &&
                Objects.equals(imgNumber, that.imgNumber) &&
                Objects.equals(isShowGzhTwoDimensionCode, that.isShowGzhTwoDimensionCode) &&
                Objects.equals(isAutomaticReview, that.isAutomaticReview) &&
                Objects.equals(useState, that.useState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, merchantId, activityTitle, handlerTitle, assistantHandlerTitle, activityStartTime, activityEndTime, tradeLifeTime, activityBackgroundType, backgroundImgUrl, activityRuleDescription, redPacketMoney, redPacketType, accessDescription, isEnterOrderNumber, isEnterWangwangNumber, imgNumber, isShowGzhTwoDimensionCode, isAutomaticReview, useState);
    }


    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public void setHandlerTitle(String handlerTitle) {
        this.handlerTitle = handlerTitle;
    }

    public void setAssistantHandlerTitle(String assistantHandlerTitle) {
        this.assistantHandlerTitle = assistantHandlerTitle;
    }

    public void setActivityStartTime(Timestamp activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public void setActivityEndTime(Timestamp activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public void setTradeLifeTime(Long tradeLifeTime) {
        this.tradeLifeTime = tradeLifeTime;
    }

    public void setActivityBackgroundType(String activityBackgroundType) {
        this.activityBackgroundType = activityBackgroundType;
    }

    public void setBackgroundImgUrl(String backgroundImgUrl) {
        this.backgroundImgUrl = backgroundImgUrl;
    }

    public void setActivityRuleDescription(String activityRuleDescription) {
        this.activityRuleDescription = activityRuleDescription;
    }

    public void setRedPacketMoney(String redPacketMoney) {
        this.redPacketMoney = redPacketMoney;
    }

    public void setRedPacketType(String redPacketType) {
        this.redPacketType = redPacketType;
    }

    public void setAccessDescription(String accessDescription) {
        this.accessDescription = accessDescription;
    }

    public void setEnterOrderNumber(Boolean enterOrderNumber) {
        isEnterOrderNumber = enterOrderNumber;
    }

    public void setEnterWangwangNumber(Boolean enterWangwangNumber) {
        isEnterWangwangNumber = enterWangwangNumber;
    }

    public void setImgNumber(Integer imgNumber) {
        this.imgNumber = imgNumber;
    }

    public void setShowGzhTwoDimensionCode(Boolean showGzhTwoDimensionCode) {
        isShowGzhTwoDimensionCode = showGzhTwoDimensionCode;
    }

    public void setAutomaticReview(Boolean automaticReview) {
        isAutomaticReview = automaticReview;
    }
    public void setUseState(Boolean useState) {
        this.useState = useState;
    }

}
