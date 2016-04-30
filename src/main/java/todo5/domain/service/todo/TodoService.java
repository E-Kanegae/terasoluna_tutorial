package todo5.domain.service.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import todo5.domain.model.Todo;

public interface TodoService {
	Page<Todo> findAll(Pageable pageable);
	
	void create(Todo todo);
	
	void finish(String todoId);
	
	void delete(String todoId);
	
	Todo findOne(String todoId);

	Todo edit(Todo todo);

	Integer countTodoFile(String todoId);

}
