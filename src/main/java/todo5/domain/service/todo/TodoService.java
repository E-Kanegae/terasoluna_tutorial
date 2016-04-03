package todo5.domain.service.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import todo5.domain.model.Todo;

public interface TodoService {
	Page<Todo> findAll(Pageable pageable);
	
	Todo create(Todo todo);
	
	Todo finish(String todoId);
	
	void delete(String todoId);

}
