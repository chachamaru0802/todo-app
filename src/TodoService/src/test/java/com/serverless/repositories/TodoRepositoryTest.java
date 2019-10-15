package com.serverless.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.serverless.models.TodoItem;
import com.serverless.utils.DateUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lombok.val;

@RunWith(JUnit4.class)
public class TodoRepositoryTest {

    private final static String DYNAMODB_ENDPOINT = "http://localhost:8000";

    private final static String TEST_ID = "c179f5aa-57a0-4cda-b3bc-75e76ea39264";

    private final static String TEST_UPDATE_ID = "c179f5aa-57a0-4cda-b3bc-111111111111";

    private final static String TEST_DELETE_ID = "c179f5aa-57a0-4cda-b3bc-222222222222";

    private final static String TEST_TITLE = "テストタイトル";

    private final static String TEST_CONTENTS = "テスト内容";

    private final static Date TEST_EXPIRED_DATE = DateUtils.toDate(LocalDate.of(2019, 9, 30));

    private final static boolean TEST_COMPLETED = false;

    private final static Date TEST_CREATE_DATETIME = DateUtils.toDate(LocalDateTime.of(2019, 9, 28, 10, 20, 30));

    private final static Date TEST_UPDATE_DATETIME = DateUtils.toDate(LocalDateTime.of(2019, 9, 29, 13, 40, 50));

    /**
     * DynamoDB Clinet
     */
    private AmazonDynamoDB _client;

    /**
     * DynamoDB Mapper
     */
    private DynamoDBMapper _mapper;

    /**
     * テストデータ
     */
    private List<TodoItem> _todos;

    /**
     * TodoRepository
     */
    private TodoRepository _todoRepository;

    @Before
    public void before() {
        // Local DynamoDB 初期化
        val endpoint = new AwsClientBuilder.EndpointConfiguration(DYNAMODB_ENDPOINT, "local");
        _client = AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(endpoint).build();
        _mapper = new DynamoDBMapper(_client);

        // Local DynamoDB 初期化
        initLocalDynamoDB();

        // テストデータ 作成
        createTestData();

        _todoRepository = new TodoRepository(_client);
    }

    /**
     * Local DynamoDB 初期化
     */
    private void initLocalDynamoDB() {
        val listTableResult = _client.listTables();
        val tableNames = listTableResult.getTableNames();
        val hasTodoTable = tableNames.stream().anyMatch(x -> x.equals("TodoItem"));

        if (!hasTodoTable) {
            val request = _mapper.generateCreateTableRequest(TodoItem.class);
            request.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
            _client.createTable(request);
        }
    }

    /**
     * テストデータ 作成
     */
    private void createTestData() {
        val localDatetime = LocalDateTime.now();
        val datetime = DateUtils.toDate(localDatetime);

        _todos = new ArrayList<TodoItem>();

        // データ取得用 テストデータ作成
        val testData = new TodoItem() {
            {
                setId(TEST_ID);
                setTitle(TEST_TITLE);
                setContents(TEST_CONTENTS);
                setExpiredDate(TEST_EXPIRED_DATE);
                setCompleted(TEST_COMPLETED);
                setCreateDatetime(TEST_CREATE_DATETIME);
                setUpdateDatetime(TEST_UPDATE_DATETIME);
            }
        };

        _mapper.save(testData);
        _todos.add(testData);

        // 更新用 テストデータ作成
        val updateData = new TodoItem() {
            {
                setId(TEST_UPDATE_ID);
                setTitle(TEST_TITLE);
                setContents(TEST_CONTENTS);
                setExpiredDate(TEST_EXPIRED_DATE);
                setCompleted(TEST_COMPLETED);
                setCreateDatetime(TEST_CREATE_DATETIME);
                setUpdateDatetime(TEST_UPDATE_DATETIME);
            }
        };

        _mapper.save(updateData);
        _todos.add(updateData);

        // 削除用 テストデータ 作成
        val deleteData = new TodoItem() {
            {
                setId(TEST_DELETE_ID);
                setTitle(TEST_TITLE);
                setContents(TEST_CONTENTS);
                setExpiredDate(TEST_EXPIRED_DATE);
                setCompleted(TEST_COMPLETED);
                setCreateDatetime(TEST_CREATE_DATETIME);
                setUpdateDatetime(TEST_UPDATE_DATETIME);
            }
        };

        _mapper.save(deleteData);
        _todos.add(updateData);

        for (int i = 0; i < 20; i++) {
            val todo = new TodoItem() {
                {
                    setTitle("ダミータイトル");
                    setContents("ダミー");
                    setExpiredDate(TEST_EXPIRED_DATE);
                    setCompleted(false);
                    setCreateDatetime(datetime);
                    setUpdateDatetime(datetime);
                }
            };

            _mapper.save(todo);
            _todos.add(todo);
        }
    }

    /**
     * 全件取得 テスト
     */
    @Test
    public void testGetAllData() {
        val todos = _todoRepository.searchTodoItem(null);

        assertNotNull(todos);
        assertEquals(_todos.size(), todos.size());
    }

    /**
     * 検索テスト
     */
    @Test
    public void testSearchTodoItem() {
        val conditions = new TodoItem();
        conditions.setTitle("スト");

        val todos = _todoRepository.searchTodoItem(conditions);

        val todo = todos.stream().findFirst();

        assertNotNull(todo);

    }

    /**
     * データ１件取得テスト
     */
    @Test
    public void testGetTodoItem() {
        val todo = _todoRepository.getTodoItem(TEST_ID);

        assertNotNull(todo);
        assertEquals(TEST_TITLE, todo.getTitle());
        assertEquals(TEST_CONTENTS, todo.getContents());
        assertEquals(TEST_EXPIRED_DATE, todo.getExpiredDate());
        assertEquals(TEST_COMPLETED, todo.isCompleted());
        assertEquals(TEST_CREATE_DATETIME, todo.getCreateDatetime());
        assertEquals(TEST_UPDATE_DATETIME, todo.getUpdateDatetime());
    }

    /**
     * 指定データがない場合のテスト
     */
    @Test
    public void testGetTodoItemNotFound(){
        val todo = _todoRepository.getTodoItem("dummy");

        assertNull(todo);
    }

    /**
     * 新規登録テスト
     */
    @Test
    public void testCreateTodoItem() {
        val todoItem = new TodoItem() {
            {
                setTitle(TEST_TITLE);
                setContents(TEST_CONTENTS);
                setExpiredDate(TEST_EXPIRED_DATE);
                setCompleted(TEST_COMPLETED);
            }
        };

        val result = _todoRepository.setTodoItem(todoItem);

        assertTrue(result);

        val id = todoItem.getId();

        val todo = _mapper.load(TodoItem.class, id);

        assertEquals(TEST_TITLE, todo.getTitle());
        assertEquals(TEST_CONTENTS, todo.getContents());
        assertEquals(TEST_EXPIRED_DATE, todo.getExpiredDate());
        assertEquals(TEST_COMPLETED, todo.isCompleted());

        assertNotNull(todo.getId());
        assertNotNull(todo.getCreateDatetime());
        assertNotNull(todo.getUpdateDatetime());
    }

    /**
     * 更新テスト
     */
    @Test
    public void testUpdateTodoItem() {
        val todoItem = new TodoItem() {
            {
                setId(TEST_UPDATE_ID);
                setTitle(TEST_TITLE);
                setContents(TEST_CONTENTS);
                setExpiredDate(TEST_EXPIRED_DATE);
                setCompleted(TEST_COMPLETED);
                setCreateDatetime(TEST_CREATE_DATETIME);
                setUpdateDatetime(TEST_UPDATE_DATETIME);
            }
        };

        val result = _todoRepository.setTodoItem(todoItem);

        assertTrue(result);

        val id = todoItem.getId();

        val todo = _mapper.load(TodoItem.class, id);

        assertEquals(TEST_UPDATE_ID, todo.getId());
        assertEquals(TEST_TITLE, todo.getTitle());
        assertEquals(TEST_CONTENTS, todo.getContents());
        assertEquals(TEST_EXPIRED_DATE, todo.getExpiredDate());
        assertEquals(TEST_COMPLETED, todo.isCompleted());
        assertEquals(TEST_CREATE_DATETIME, todo.getCreateDatetime());

        assertTrue(TEST_UPDATE_DATETIME.before(todo.getUpdateDatetime()));
    }

    /**
     * 削除テスト
     */
    @Test
    public void testDelete(){
        val isDelete = _todoRepository.deleteTodoItem(TEST_DELETE_ID);

        assertTrue(isDelete);

        val todo = _todoRepository.getTodoItem(TEST_DELETE_ID);

        assertNull(todo);
    }

    @After
    public void after() {
        val scan = new DynamoDBScanExpression();
        val todos = _mapper.scan(TodoItem.class, scan);

        for (TodoItem item : todos) {
            _mapper.delete(item);
        }
    }

}