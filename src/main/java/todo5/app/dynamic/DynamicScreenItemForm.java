package todo5.app.dynamic;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 */
@Data
public class DynamicScreenItemForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Object> dynamicItemMap;

}
