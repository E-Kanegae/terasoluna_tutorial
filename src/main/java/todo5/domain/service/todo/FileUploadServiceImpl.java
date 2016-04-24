package todo5.domain.service.todo;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import todo5.domain.model.TodoFile;
import todo5.domain.repository.todo.FileUploadRepository;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Inject
	FileUploadRepository fileUploadRepositoy;
	
	@Override
	public void upload(TodoFile file) {
		
		String todoId = UUID.randomUUID().toString();

		file.setTodoId(todoId);
		fileUploadRepositoy.file_register(file);


	}

}
