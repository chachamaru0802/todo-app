package com.serverless.models;

import java.time.LocalDateTime;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
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
    @DynamoDBAttribute(attributeName = "CreateTime")
    private long createTime;

    /**
     * 更新時間
     */
    @DynamoDBAttribute(attributeName = "UpdateTime")
    private long updateTime;

    /**
     * 作成日時 取得
     */
    @DynamoDBIgnore
    public LocalDateTime getCreateDatetime() {
        return DateUtils.toLocalDatetime(createTime);
    }

    /**
     * 作成日時 設定
     * 
     * @param value
     */
    public void setCreateDatetime(LocalDateTime value) {
        createTime = DateUtils.toTimeInMillis(value);
    }

    /**
     * 更新日時 取得
     * 
     * @return
     */
    @DynamoDBIgnore
    public LocalDateTime getUpdateDatetime() {
        return DateUtils.toLocalDatetime(updateTime);
    }

    /**
     * 更新日時 設定
     */
    public void setUpdateDatetime(LocalDateTime value) {
        updateTime = DateUtils.toTimeInMillis(value);
    }
}