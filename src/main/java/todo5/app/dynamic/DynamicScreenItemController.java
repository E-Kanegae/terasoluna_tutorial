/**
 * @(#)DynamicScreenItemController.java
 */
package todo5.app.dynamic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import todo5.domain.model.dynamic.DynamicScreenItemOutputBean;
import todo5.domain.service.dynamic.DynamicScreenItemService;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 */
@SessionAttributes(types = DynamicScreenItemValidationInfo.class)
@Controller
@RequestMapping("dynamic")
public class DynamicScreenItemController {

    @Inject
    DynamicScreenItemService dynamicScreenItemService;

    @Inject
    DynamicScreenItemHelper helper;

    @ModelAttribute
    public DynamicScreenItemForm setUpForm() {
        DynamicScreenItemForm form = new DynamicScreenItemForm();
        return form;
    }

    /**
     * 初期表示処理
     * @param Model
     * @return dynamicScreen/dynamicScreen01.jsp
     * @throws なし
     */
    @RequestMapping(value = "init")
    public String init(DynamicScreenItemForm form, Model model,
            DynamicScreenItemValidationInfo validationInfo) {

        //画面表示内容（動的項目含む）の取得
        DynamicScreenItemOutputBean output = dynamicScreenItemService.init();
        
        model.addAttribute("output", output);
        model.addAttribute("formFieldList", output.getFormFiledList());

        // 入力チェック情報をValidationInfoに登録する。
        Map<String, List<String>> validationMap = new HashMap<String, List<String>>();
        for (int i = 0; i < output.getFormFiledList().size(); i++) {
            validationMap.put(
                    output.getFormFiledList().get(i).getFormFieldId(),
                    output.getFormFiledList().get(i).getValidationCheck());
        }
        validationInfo.setItemValidationInfo(validationMap);

        return "dynamicScreen/dynamicScreen01";
    }

    /**
     * 入力パラメータを取得する処理
     * @param Model
     * @return dynamicScreen/dynamicScreen01.jsp
     * @throws なし
     */
    @RequestMapping(value = "sendValue")
    public String send(DynamicScreenItemForm form,  BindingResult result, Model model,
            DynamicScreenItemValidationInfo validationInfo) {

        // 入力チェック処理を呼び出す。
        new DynamicScreenItemValidator().validate(helper.setUpValidateInput(
                form.getDynamicItemMap(),
                validationInfo.getItemValidationInfo()), result);
        
        if(result.hasErrors()){
            DynamicScreenItemOutputBean output = dynamicScreenItemService.init();
            model.addAttribute("output", output);
            model.addAttribute("formFieldList", output.getFormFiledList());
            return "dynamicScreen/dynamicScreen01";
        }

        //TODO 仮に初期表示処理をそのまま書いているだけ。要・実装。
        DynamicScreenItemOutputBean output = dynamicScreenItemService.init();
        model.addAttribute("output", output);
        model.addAttribute("formFieldList", output.getFormFiledList());
        // 入力チェック情報をValidationInfoに登録する。
        Map<String, List<String>> validationMap = new HashMap<String, List<String>>();
        for (int i = 0; i < output.getFormFiledList().size(); i++) {
            validationMap.put(
                    output.getFormFiledList().get(i).getFormFieldId(),
                    output.getFormFiledList().get(i).getValidationCheck());
        }
        validationInfo.setItemValidationInfo(validationMap);
        
        return "dynamicScreen/dynamicScreen01";
    }
}
