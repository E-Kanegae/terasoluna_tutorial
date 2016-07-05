/**
 * @(#)FormFiledBean.java
 * 
 */
package todo5.domain.model.dynamic;

import java.util.List;

import lombok.Data;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 */
@Data
public class FormFieldBean {
    
    /*
     * フィールドID
     */
    String formFieldId;
    
    /*
     * フィールドラベル名
     */
    String formFieldName;

    /*
     * 入力項目種別
     */
    String InputType;
    
    /*
     * 選択肢(ラジオ、チェックボックス)
     */    
    List<String> elements;
    
    /*
     * 選択肢(ドロップボックス)
     */
    List<DynamicDropDownBean> keyValueList;
    
    /*
     * データ種別
     */
    String dataType;
    
    /*
     * 表示順
     */
    Integer sortOrder;
    
    /*
     * バリデーションチェック種別
     */
    List<String> validationCheck;

    /*
     * 初期値
     */
    String initialValue;
    
}
