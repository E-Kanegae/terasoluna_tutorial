package todo5.domain.service.todo;

import java.util.Collection;

import todo5.domain.model.Todo;

public interface TodoService {
	Collection<Todo> findAll();
	
	Todo create(Todo todo);
	
	Todo finish(String todoId);
	
	void delete(String todoId);
}
