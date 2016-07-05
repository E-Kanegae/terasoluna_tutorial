package todo5.domain.model.dynamic;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 */
@Data
public class DynamicScreenItemOutputBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    String formId;
    String formName;
    String ScheduleTypeId;
    String approveSp;
    List<FormFieldBean> formFiledList; 
    
}
