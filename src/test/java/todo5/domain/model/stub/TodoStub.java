/**
 * @(#)TodoStub.java
 * 
 * Copyright (c) 2017 GABA CORPORATION.
 */
package todo5.domain.model.stub;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import todo5.domain.model.Todo;

/**
 * Todoクラスのデータパターンを定義するクラス
 * @author E-Kanegae
 * @version $Revision$
 */
public class TodoStub {

    Todo  todo;
    
    public Todo getFullNameBothNameHasValue(){
        todo = new Todo();
        todo.setFirstName("Etsu");
        todo.setLastName("Kanegae");
        return todo;
    }
    
    public Todo getFullNameBothNameIsNull(){
        todo = new Todo();
        todo.setFirstName(null);
        todo.setLastName(null);
        return todo;
    }
    
    public Todo getFullNameFirstNameIsNull(){
        todo = new Todo();
        todo.setFirstName(null);
        todo.setLastName("Kanegae");
        return todo;
    }
 
    public Todo getFullNameLastNameIsNull(){
        todo = new Todo();
        todo.setFirstName("Etsu");
        todo.setLastName(null);
        return todo;
    }
    
    public Todo getTodoIdIsNull(){
        todo = new Todo();
        todo.setTodoId(null);
        return todo;
    }
    
    public Todo getTodoIdHasValue(String str){
        todo = new Todo();
        todo.setTodoId(str);
        return todo;
    }

   public Todo getTodoFindOneData(){
       todo = new Todo();
       todo.setTodoId("1111111");
       todo.setTodoTitle("タスク");
       todo.setFinished(false);
       todo.setCreatedAt(DateTime.parse("2016-04-01", DateTimeFormat.forPattern("yyyy-MM-dd")));
       todo.setTodoCategory("1");
       todo.setTodoCategoryName("プロジェクトマネジメント");
       todo.setFirstName("Etsu");
       todo.setLastName("Kanegae");
       todo.setFullName("Etsu Kanegae");
       todo.setTodoPriority(1);
       todo.setTodoPriorityName("A");
       todo.setCmt("特になし");
       todo.setUpdatedAt(DateTime.parse("2016-05-01", DateTimeFormat.forPattern("yyyy-MM-dd")));
       todo.setExpiryDate(DateTime.parse("2016-06-01", DateTimeFormat.forPattern("yyyy-MM-dd")));
       
       return todo;
   }

}
