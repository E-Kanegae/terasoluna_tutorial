package todo5.domain.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private String todoId;
	private String fileNm;
	private int fileNo;
	private byte[] file;
	private String description;
	
}
