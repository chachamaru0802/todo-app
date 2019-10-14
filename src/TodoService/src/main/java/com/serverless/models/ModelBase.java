package com.serverless.models;

import java.time.LocalDateTime;

import com.serverless.utils.DateUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 基底モデルクラス
 */
public class ModelBase {

    /**
     * 作成時間
     */
    @Getter
    @Setter
    private long createTime;

    /**
     * 更新時間
     */
    @Getter
    @Setter
    private long updateTime;

    /**
     * 作成日時 取得
     */
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