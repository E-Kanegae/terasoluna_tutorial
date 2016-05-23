/**
 * @(#)TodoRepositoryFindAllTest.java
 * 
 * Copyright (c) 2017 E-Kanegae.
 */
package todo5.domain.repository.todo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.RowBounds;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.hamcrest.beans.SamePropertyValuesAs;

import todo5.domain.constants.Todo5FunctionEnum;
import todo5.domain.constants.Todo5TableEnum;
import todo5.domain.constants.Todo5TestConstants;
import todo5.domain.model.Todo;
import todo5.testutil.Todo5DataSourceBasedDBTestCase;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 * @version $Revision$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@Rollback(false)
public class TodoRepositoryFindAllTest extends Todo5DataSourceBasedDBTestCase {

    @Inject
    TodoRepository todoRepository;

    @Inject
    TransactionAwareDataSourceProxy dataSourceTest;

    @Inject
    NamedParameterJdbcTemplate jdbcTemplate;
    
    String dataFileName = "TodoRepositoryTest.xls";
    
    //DBMSのCaseSesitive設定に合わせて設定する。（Postgres初期設定がtrueなので今はtrue）
    boolean isCaseSensitiveTableNm = true;

    /**
     * テスト実行前処理 テストデータの投入を行う。
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        
        super.setUp();
    }

    /**
     * テスト実行後処理 テストデータの削除を行う。
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void findAllPage1Test() throws Exception{

        //Repository実行
        Todo todo = new Todo();
        RowBounds rowBounds = new RowBounds(0, 3);
        List<Todo> todos = todoRepository.findAll(todo, rowBounds);
        
        //期待値の取得
        ITable it = new XlsDataSet(new File(Todo5TestConstants.TEST_FILE_DIR
                + Todo5FunctionEnum.TODO.getFuncName()
                + "/" + dataFileName)).getTable(Todo5TableEnum.TODO.getTableName());

        List<Todo> answer = new ArrayList<Todo>();
        for(int j=0;j<3;j++){
            todo.setTodoId((it.getValue(j+2, "todo_id")).toString());
            todo.setTodoTitle((String)it.getValue(j+2, "todo_title"));
            todo.setFinished((boolean)it.getValue(j+2, "finished"));
//          LocalDateTime dt = LocalDateTime.now();
//          todo.setCreatedAt(dt);
            todo.setFirstName((String)it.getValue(j+2, "firstname"));
            todo.setLastName((String)it.getValue(j+2, "lastname"));
            todo.setFullName((String)it.getValue(j+2, "fullname"));
            todo.setTodoPriority(Integer.parseInt((it.getValue(j+2, "priority")).toString()));
            todo.setCmt((String)it.getValue(j+2, "remarks"));
            todo.setTodoCategory((String)it.getValue(j+2, "category_id"));
//          todo.setExpiryDate((LocalDateTime)dt);
            answer.add(todo);
        }
        
        for(int i=0;i<todos.size();i++){
            assertThat(todos.get(i).getClass(), is(SamePropertyValuesAs.samePropertyValuesAs(answer.get(i).getClass())));
        }
    }

    /**
     * [どのようにオーバーライド、実装したか書きましょう]
     * @see org.dbunit.DataSourceBasedDBTestCase#getDataSource()
     */
    @Override
    protected DataSource getDataSource() {
        
        return dataSourceTest;
    }

    /**
     * [どのようにオーバーライド、実装したか書きましょう]
     * @see org.dbunit.DatabaseTestCase#getDataSet()
     */
    protected String  getFilePath() {
        return "classpath:xls/" + Todo5FunctionEnum.TODO.getFuncName()
                + "/" + dataFileName;
    }

    /**
     * [どのようにオーバーライド、実装したか書きましょう]
     * @see todo5.testutil.Todo5DataSourceBasedDBTestCase#getCaseSensitiveTableNms()
     */
    @Override
    protected boolean getCaseSensitiveTableNm() {
        return isCaseSensitiveTableNm;
    }

}
