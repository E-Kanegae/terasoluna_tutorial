
package todo5.app.todo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoFinishRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String todoId;
    @NotNull
    private String todoTitle;
}
