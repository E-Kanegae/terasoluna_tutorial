package todo5.domain.service.manage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import todo5.domain.model.Todo;

public interface TodoManagementService {

	public Page<Todo> search(Todo todo, Pageable pageable);
	
	public void send();

}
