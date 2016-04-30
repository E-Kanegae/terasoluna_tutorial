package todo5.domain.repository.todo;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import todo5.domain.model.Todo;

public interface TodoRepository {
	
	Todo findOne(String todoId);
	
	long countTodo();
	
	List<Todo> findAll(RowBounds rowBounds);
	
    void create(Todo todo);

    boolean update(Todo todo);
	
	void delete(Todo todo);
	
	long countByFinished(boolean finished);
	
	void edit(Todo todo);

	Integer countFile(String todoId);
}
