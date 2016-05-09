
package todo5.domain.service.todo;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import todo5.domain.model.TodoFile;
import todo5.domain.repository.todo.FileUploadRepository;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Inject
    FileUploadRepository fileUploadRepositoy;

    @Override
    public void upload(TodoFile file) {

        Integer maxFileNo = 0;

        try {
            maxFileNo = fileUploadRepositoy.getFileNumber(file.getTodoId());
            if (maxFileNo == null) {
                maxFileNo = 0;
            }
        } catch (DataAccessException e) {
            // 例外ハンドリング処理を記述する
        }

        file.setFileNo(maxFileNo + 1);

        try {
            fileUploadRepositoy.registerFile(file);
        } catch (DuplicateKeyException e) {
            // 例外ハンドリング処理を記述する
        }

    }

}
