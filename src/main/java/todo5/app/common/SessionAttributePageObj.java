package todo5.app.common;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SessionAttributePageObj implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int page;
    private int size;

}
