package todo5.domain.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Todo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String todoId;
	
	private String todoTitle;
	
	private boolean finished;
	
	private Date createdAt;
	
	private String todoCategory;
	
	private String todoCategoryName;
	
	private String firstName;
	
	private String lastName;
	
	private String fullName;
	
	private int todoPriority;
	
	private String todoPriorityName;
	
	private String cmt;

}
