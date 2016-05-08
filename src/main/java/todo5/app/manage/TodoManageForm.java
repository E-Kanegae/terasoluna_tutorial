package todo5.app.manage;

import java.io.Serializable;

import lombok.Data;

@Data
public class TodoManageForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String todoCategory;
	private String todoTitle;
	private String firstName;
	private String lastName;
	private String todoPriority;
	private String createDate;
	private String expiryDate;

}
