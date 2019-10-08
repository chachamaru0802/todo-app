package com.serverless.models;

import lombok.Data;

/**
 * Todoアイテムクラス
 */
@Data
public class TodoItem {

    /**
     * タイトル
     */
    private String title;

    /**
     * 内容
     */
    private String Contents;
}