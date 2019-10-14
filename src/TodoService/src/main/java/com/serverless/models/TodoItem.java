package com.serverless.models;

import java.time.LocalDate;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.serverless.utils.DateUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Todoアイテムクラス
 */
@Data
@EqualsAndHashCode(callSuper = false)
@DynamoDBTable(tableName = "TodoItem")
public class TodoItem extends ModelBase {

    /**
     * ID
     */
    @DynamoDBHashKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey
    private String id;

    /**
     * タイトル
     */
    @DynamoDBAttribute(attributeName = "Title")
    private String title;

    /**
     * 内容
     */
    @DynamoDBAttribute(attributeName = "Contents")
    private String contents;


    /**
     * 有効期限(UnixTime)
     */
    @DynamoDBAttribute(attributeName = "Time")
    private long time;

    /**
     * 有効期限を取得
     * @return 
     */
    @DynamoDBIgnore
    public LocalDate getExpiredDate(){
        return DateUtils.toLocalDatetime(time).toLocalDate();
    }

    /**
     * 有効期限を保存
     */
    @DynamoDBIgnore
    public void setExpiredDate(LocalDate value){
        time = DateUtils.toTimeInMillis(value);
    }

    /**
     * 完了フラグ
     */
     private boolean isCompleted;

}