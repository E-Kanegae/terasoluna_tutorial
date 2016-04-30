package todo5.app.todo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class FileUploadForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private MultipartFile file;
    
    @NotNull
    private String todoId;

    @NotNull
    @Size(min = 0, max = 100)
    private String description;

}
