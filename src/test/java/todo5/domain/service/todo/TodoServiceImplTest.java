/**
 * @(#)TodoServiceImplTtest.java
 * 
 * Copyright (c) 2017 E-Kanegae.
 */
package todo5.domain.service.todo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.terasoluna.gfw.common.codelist.CodeList;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import todo5.domain.model.Todo;
import todo5.domain.model.stub.TodoStub;
import todo5.domain.repository.todo.TodoRepository;

/**
 * TodoServiceImplのテストクラス
 * @author E-Kanegae
 * @version $Revision$
 */
public class TodoServiceImplTest {

    TodoServiceImpl todoService;
    TodoRepository todoRepository;
    CodeList testTodoPriorityCodeList;
    Todo todo;
    
    /**
     * 1.テスト対象クラスのインスタンスにRepositoryクラスのMockインタスタンスを作成
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        todoService = new TodoServiceImpl();
        todoRepository = mock(TodoRepository.class);
        todoService.todoRepository = todoRepository;     
    }

    /**
     * {@link todo5.domain.service.todo.TodoServiceImpl#countTodoFile(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testCountTodoFileTodoIdIsNull() {
        when(todoRepository.countFile(null)).thenReturn(0);
        Integer result = todoService.countTodoFile(null);
        assertThat(result, is(0));
    }
    
    /**
     * {@link todo5.domain.service.todo.TodoServiceImpl#countTodoFile(java.lang.String)} のためのテスト・メソッド。
     */   
    @Test
    public final void testCountTodoFileTodoIdHasSimpleValue() {
        when(todoRepository.countFile("1111111")).thenReturn(1);
        Integer result = todoService.countTodoFile("1111111");
        assertThat(result, is(1));
    }
    
    /**
     * {@link todo5.domain.service.todo.TodoServiceImpl#countTodoFile(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testCountTodoFileTodoIdHasDoubleValue() {
        when(todoRepository.countFile("2222222")).thenReturn(2);
        Integer result = todoService.countTodoFile("2222222");
        assertThat(result, is(2));
    }

    /**
     * {@link todo5.domain.service.todo.TodoServiceImpl#edit(todo5.domain.model.Todo)} のためのテスト・メソッド。
     */
    @Test
    public final void testEdit() {
//        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link todo5.domain.service.todo.TodoServiceImpl#findOne(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testFindOneNormalCase() {
        TodoStub todoStub = new TodoStub();
        todo = todoStub.getTodoFindOneData();
        
        when(todoRepository.findOne("1111111")).thenReturn(todo);
        Todo result = new Todo();
        result = todoService.findOne("1111111");

        assertThat(result, is(todo));
    }

    /**
     * {@link todo5.domain.service.todo.TodoServiceImpl#findOne(java.lang.String)} のためのテスト・メソッド。
     */
    @Test(expected = ResourceNotFoundException.class)
    public final void testFindOneExceptionCase() throws Exception{
        todo = null;
        when(todoRepository.findOne("9999999")).thenReturn(todo);
        todoService.findOne("9999999");

    }
    /**
     * {@link todo5.domain.service.todo.TodoServiceImpl#findAll(org.springframework.data.domain.Pageable)} のためのテスト・メソッド。
     */
    @Test
    public final void testFindAll() {
//        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link todo5.domain.service.todo.TodoServiceImpl#create(todo5.domain.model.Todo)} のためのテスト・メソッド。
     */
    @Test
    public final void testCreate() {
//        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link todo5.domain.service.todo.TodoServiceImpl#finish(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testFinish() {
//        fail("まだ実装されていません"); // TODO
    }

    /**
     * {@link todo5.domain.service.todo.TodoServiceImpl#delete(java.lang.String)} のためのテスト・メソッド。
     */
    @Test
    public final void testDelete() {
//        fail("まだ実装されていません"); // TODO
    }



}
