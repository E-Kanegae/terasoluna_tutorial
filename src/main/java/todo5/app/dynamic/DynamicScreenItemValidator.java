package todo5.app.dynamic;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 動的項目へのValidatorクラス
 * @author E-Kanegae
 */
@Component
public class DynamicScreenItemValidator implements Validator{

    @Value("${dynamicScreenItem.bean.property.name}")
    private static String VALIDATED_DYNAMIC_MEMBER_OBJECT;
    
    private static final String NETED_FIELD_PREFIX = "[";
    private static final String NETED_FIELD_POSTFIX = "]";
    
    /**
     * チェック対象クラス判別メソッド。本クラスでは何も実施しない。
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
        // do Nothing
        return true;
    }

    /**
     * 動的入力チェック
     * @param target　List<DynamicScreenItemDataBindingInputBean>　
     * @param errors　
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object target, Errors errors) { 
        for(DynamicScreenItemDataBindingInputBean input :(List<DynamicScreenItemDataBindingInputBean>) target){       
            if(!this.handleValidationCheck(input.getFieldValue(), input.getValidationTypeName())){
                errors.rejectValue(VALIDATED_DYNAMIC_MEMBER_OBJECT + NETED_FIELD_PREFIX + input.getFieldName() + NETED_FIELD_POSTFIX, 
                        input.getErrorCode());
            }
        }
    }

    /**
     * 第二引数の値に応じて、第一引数への入力チェックの判別し、実行する。
     * 
     * @param obj バリデーションチェック対象オブジェクト
     * @param validationCheckType　入力チェック種別
     * @return boolean
     */
    private boolean handleValidationCheck(Object obj, String validationCheckType){
        //必須チェック
        if(validationCheckType.equals(DynamicScreenItemValidationCheckEnum.NotNullCheck)){
            return isNotNull(obj);
        //文字列長チェック
        }else if(validationCheckType.equals(DynamicScreenItemValidationCheckEnum.StringLengthCheck)){
            return isCorrectLength(obj); //TODO 実装
        }
        //TODO 他、入力チェックある分だけ実装

        return false;
    }
    
    /**
     * NotNullチェック
     * @param obj　オブジェクト
     * @return boolean
     */
    private boolean isNotNull(Object obj) {
        return !(obj.equals("") || obj.equals(null));
    }
    

    /**
     * 文字列長チェック
     * @param obj　オブジェクト
     * @return boolean
     */
    private boolean isCorrectLength(Object obj){
        if(!isNotNull(obj)){
            return true;
        }
        //TODO 未実装
        return true;
    }
}
