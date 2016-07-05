/**
 * @(#)DynamicScreenItemDataBindingInputBean.java
 * 
 */
package todo5.app.dynamic;

import lombok.Data;

/**
 * [このクラスの説明を書きましょう]
 * @author　E-Kanegae
 * @version $Revision$
 */
@Data
public class DynamicScreenItemDataBindingInputBean {
    
    //Validation対象のフィールド名
    String fieldName;

    //入力値
    String fieldValue;

    //入力チェック名
    String validationTypeName;
    
    //エラーコード
    String errorCode;
    
    public String getErrorCode(){
        //必須チェック
        if (this.validationTypeName.equals(DynamicScreenItemValidationCheckEnum.NotNullCheck)){
            return "";
        //文字列長チェック
        }else if (this.validationTypeName.equals(DynamicScreenItemValidationCheckEnum.StringLengthCheck)){
            return "";
        }else{
            return ""; //デフォルト
        }
    }
}
