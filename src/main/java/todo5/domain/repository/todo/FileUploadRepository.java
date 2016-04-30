package todo5.domain.repository.todo;

import todo5.domain.model.TodoFile;

public interface FileUploadRepository {
	 
	void registerFile(TodoFile file);

	Integer getFileNumber(String todoId);

}
