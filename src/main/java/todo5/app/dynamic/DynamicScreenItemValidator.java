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
                if (entry.getValue().contains(ValidationEnum.NotNull)) {
                    if (!isNotNull(dynamicItemMap.get(entry.getKey()))) {
                        String errorField = dynamicItemMap.get(entry.getKey()).toString();
                    }
                }
                // それ以外のチェックを実施する。
                // TODO 未実装

            }

        }
    }

    /**
     * NotNullチェック
     * @param obj　オブジェクト
     * @return boolean
     */
    private boolean isNotNull(Object obj) {
        return (!obj.equals("") || !obj.equals(null));
    }

}
