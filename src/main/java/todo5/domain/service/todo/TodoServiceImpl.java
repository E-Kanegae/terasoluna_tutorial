package todo5.domain.service.todo;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import todo5.domain.model.Todo;
import todo5.domain.repository.todo.TodoRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
	
	@Inject
	TodoRepository todoRepository;
	
    /*
     * Todoタスク詳細表示処理
     */
	@Transactional(readOnly = true)
	public Todo findOne(String todoId) {
		Todo todo = todoRepository.findOne(todoId);
		if (todo == null) {
		
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage
			.fromText("[E404] The requested Todo is not found. (id="
			+ todoId + ")"));
		
			throw new ResourceNotFoundException(messages);
		}
		return todo;
		}
	
    /*
     * Todoリスト検索処理
     */	
	@Override
	@Transactional(readOnly = true)
	public Page<Todo> findAll(Pageable pageable) {
		
		long total = todoRepository.countTodo();
		
        List<Todo> todos;
        
        if (0 < total) {
            RowBounds rowBounds = new RowBounds(pageable.getOffset(),
                pageable.getPageSize());
            todos = todoRepository.findAll(rowBounds);
        } else {
            todos = Collections.emptyList();
        }
        return new PageImpl<>(todos, pageable, total);
    }

	@Override
	public Todo create(Todo todo) {
		
		String todoId = UUID.randomUUID().toString();
		Date createdAt = new Date();
		todo.setTodoId(todoId);
		todo.setCreatedAt(createdAt);
		todo.setFinished(false);
		todo.setFullName(String.join(" ", todo.getFirstName(), todo.getLastName()));
		todoRepository.create(todo);
		return todo;
	}

	@Override
	public Todo finish(String todoId) {
		// TODO Auto-generated method stub
		Todo todo = findOne(todoId);
		if (todo.isFinished()) {
		ResultMessages messages = ResultMessages.error();
		messages.add(ResultMessage
		.fromText("[E002] The requested Todo is already finished. (id="
		+ todoId + ")"));
		throw new BusinessException(messages);
		}
		todo.setFinished(true);
		todoRepository.update(todo);
		return todo;
	}

	@Override
	public void delete(String todoId) {
		// TODO Auto-generated method stub
		Todo todo = findOne(todoId);
		todoRepository.delete(todo);

	}

}
