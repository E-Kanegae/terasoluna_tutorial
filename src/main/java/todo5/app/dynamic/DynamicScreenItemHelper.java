/**
 * @(#)DynamicScreenItemHelper.java
 * 
 */
package todo5.app.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 * @version $Revision$
 */
@Component
public class DynamicScreenItemHelper {

    /**
     * リクエスト値とセッションに持ったバリデーション情報から、データバインド用オブジェクトへ値を詰めなおす処理
     * 
     * @param dynamicItemMap String:入力項目名、Object:ユーザ入力値
     * @param validationInfoMap String:入力項目名、List<String>入力項目への入力チェックリスト
     * @return List<DynamicScreenItemDataBindingInputBean>
     */
    protected List<DynamicScreenItemDataBindingInputBean> setUpValidateInput(
            Map<String, Object> dynamicItemMap,
            Map<String, List<String>> validationInfoMap) {

        List<DynamicScreenItemDataBindingInputBean> returnList = new ArrayList<DynamicScreenItemDataBindingInputBean>();

        //リクエスト値
        for (Map.Entry<String, Object> formEntry : dynamicItemMap.entrySet()) {
            // 入力チェック情報
            for (Map.Entry<String, List<String>> validationInfoEntry : validationInfoMap.entrySet()) {
                
                // 入力チェックが必要、且つ、対象入力項目に対する入力チェックの場合
                if (validationInfoEntry.getValue() != null
                        && formEntry.getKey().equals(
                                validationInfoEntry.getKey())) {
                    
                    DynamicScreenItemDataBindingInputBean input = new DynamicScreenItemDataBindingInputBean();
                    for (int i = 0; i < validationInfoEntry.getValue().size(); i++) {
                        input.setFieldName(formEntry.getKey());
                        input.setFieldValue(formEntry.getValue().toString());
                        input.setValidationTypeName(validationInfoEntry.getValue().get(i));
                        returnList.add(input);
                    }
                }
            }
        }
        return returnList;
    }
}
