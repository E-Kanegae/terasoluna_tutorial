
package todo5.app.todo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import todo5.common.validator.annotation.BotDetect;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoForm implements Serializable {

    public static interface TodoCreate {
    };

    public static interface TodoDelete {
    }

    public static interface TodoDetail {
    }

    public static interface TodoEdit {
    }

    private static final long serialVersionUID = 1L;

    @NotNull(groups = {
            TodoDelete.class, TodoDetail.class, TodoEdit.class
    })
    private String todoId;

    @NotNull(groups = {
            TodoCreate.class, TodoEdit.class
    })
    @Size(min = 1, max = 30, groups = {
            TodoCreate.class, TodoEdit.class
    })
    private String todoTitle;
    private String todoCategory;
    private String firstName;
    private String lastName;
    private String todoPriority;
    private String cmt;
    
    @BotDetect(groups = { TodoCreate.class})
    private String captchaCodeTextBox;
    
    private String createdAt;
    private String expiryDate;

}
