
package todo5.app.todo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoFinishResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean isFinished;
    private String todoId;
    private String todoTitle;
    private String errMsg;

}
