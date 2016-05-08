package todo5.domain.service.manage;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todo5.domain.model.Todo;
import todo5.domain.repository.todo.TodoRepository;

@Service
public class TodoManagementServiceImpl implements TodoManagementService {

	@Inject
	TodoRepository todoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Todo> search(Todo todo, Pageable pageable) {
		
		long total = todoRepository.countTodo(todo);
		
		List<Todo> todos;
		
		if (0 < total) {
            RowBounds rowBounds = new RowBounds(pageable.getOffset(),
                pageable.getPageSize());
            todos = todoRepository.findAll(todo, rowBounds);
        } else {
            todos = Collections.emptyList();
        }
        return new PageImpl<>(todos, pageable, total);
	}

}
