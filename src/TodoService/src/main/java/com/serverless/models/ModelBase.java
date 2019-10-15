package com.serverless.models;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.serverless.utils.DateUtils;

import lombok.Data;

/**
 * 基底モデルクラス
 */
@Data
public abstract class ModelBase {

    /**
     * 作成時間
     */
    @JsonIgnore
    @DynamoDBAttribute
    private long createTime;

    /**
     * 更新時間
     */
    @JsonIgnore
    @DynamoDBAttribute
    private long updateTime;

    /**
     * 作成日時 取得
     */
    @DynamoDBIgnore
    public Date getCreateDatetime() {
        return DateUtils.toLocalDatetime(createTime);
    }

    /**
     * 作成日時 設定
     * 
     * @param value
     */
    public void setCreateDatetime(Date value) {
        createTime = DateUtils.toTimeInMillis(value);
    }

    /**
     * 更新日時 取得
     * 
     * @return
     */
    @DynamoDBIgnore
    public Date getUpdateDatetime() {
        return DateUtils.toLocalDatetime(updateTime);
    }

    /**
     * 更新日時 設定
     */
    public void setUpdateDatetime(Date value) {
        updateTime = DateUtils.toTimeInMillis(value);
    }
}