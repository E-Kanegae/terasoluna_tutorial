package todo5.app.dynamic;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 */
@Component
public class DynamicScreenItemValidator {

    public static final String VALIDATION_TYPE_NOTNULL = "NotNull";
    public static final String VALIDATION_TYPE_DATE_FORMAT = "DateFormat"; 
    /**
     * @param dynamicItemMap
     * @param validationInfoMap 入力項目名、入力項目への入力チェックリスト
     */
    public void validate(Map<String, Object> dynamicItemMap,
            Map<String, List<String>> validationInfoMap) {

        // 各項目について入力チェックを行う。
        for (Map.Entry<String, List<String>> entry : validationInfoMap.entrySet()) {
            // 入力チェックがある項目の場合のみ処理を継続する。
            if (!entry.getValue().equals(null) || entry.getValue().size() > 0) {
                // NotNullチェックがある場合、一番最初にチェックする。
                if (entry.getValue().contains(VALIDATION_TYPE_NOTNULL)) {
                    if (!isNotNull(dynamicItemMap.get(entry.getKey()))) {
                        //TODO エラー時の挙動を考える。
                    }
                }
                // それ以外のチェックを実施する。
                for(String validationCheckType :entry.getValue()){
                    if (!handleValidationCheck(dynamicItemMap.get(dynamicItemMap.get(entry.getKey())), validationCheckType)){
                        //TODO エラー時の挙動を考える。
                    }                    
                }
                
                
            }

        }
    }

    /**
     * 引数の値に応じて、第一引数への入力チェックの種類を変更する。
     * 
     * @param obj バリデーションチェック対象
     * @param validationCheckType　入力チェック種別
     * @return boolean
     */
    private boolean handleValidationCheck(Object obj, String validationCheckType){
        switch (validationCheckType){
        //日付フォーマットチェック
        case VALIDATION_TYPE_DATE_FORMAT:
            return isDateFormat(obj);
        //必須チェック
        case VALIDATION_TYPE_NOTNULL:
            return isNotNull(obj);

        }
        return false;
    }
    
    /**
     * NotNullチェック
     * @param obj　オブジェクト
     * @return boolean
     */
    private boolean isNotNull(Object obj) {
        return (!obj.equals("") || !obj.equals(null));
    }
    

    /**
     * DateFormatチェック
     * @param obj　オブジェクト
     * @return boolean
     */
    private boolean isDateFormat(Object obj){
        //TODO 未実装
        return true;
    }

}
