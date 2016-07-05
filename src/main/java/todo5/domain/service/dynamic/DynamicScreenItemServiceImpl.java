/**
 * @(#)DynamicScreenItemServiceImpl.java
 * 
 */
package todo5.domain.service.dynamic;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todo5.app.dynamic.DynamicScreenItemValidationCheckEnum;
import todo5.domain.model.Importance;
import todo5.domain.model.dynamic.DynamicDropDownBean;
import todo5.domain.model.dynamic.DynamicScreenItemOutputBean;
import todo5.domain.model.dynamic.FormFieldBean;
import todo5.domain.repository.todo.ImportanceRepository;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 */
@Service
@Transactional
public class DynamicScreenItemServiceImpl implements DynamicScreenItemService {

    /*
     * ドロップダウン用ストアドプロシージャ呼び出し
     */
    @Inject
    ImportanceRepository importanceRepository;
    
    /**
     * 
     * @see todo5.domain.service.dynamic.DynamicScreenItemService#init()
     */
    @Override
    public DynamicScreenItemOutputBean init() {
        
        //まずはロジックはべた書き
        
        //formFiled1個目　テキスト
        FormFieldBean formFieldBean01 = new FormFieldBean();
        formFieldBean01.setFormFieldId("formField01");
        formFieldBean01.setFormFieldName("Comment");
        formFieldBean01.setInputType("text");
        formFieldBean01.setElements(null);
        formFieldBean01.setDataType("1");
        formFieldBean01.setSortOrder(1);
        List<String> valicheList01 = new ArrayList<String>();
        valicheList01.add(DynamicScreenItemValidationCheckEnum.NotNullCheck.toString());
        formFieldBean01.setValidationCheck(valicheList01);
        formFieldBean01.setInitialValue("初期値");
        
        //formFiled2個目　ラジオ
        FormFieldBean formFieldBean02 = new FormFieldBean(); 
        formFieldBean02.setFormFieldId("formField02");
        formFieldBean02.setFormFieldName("Importanceラジオボタン");
        formFieldBean02.setInputType("radio");
        List<String> elementsList02 = new ArrayList<String>();
        elementsList02.add("大");
        elementsList02.add("中");
        elementsList02.add("小");
        formFieldBean02.setElements(elementsList02);
        formFieldBean02.setDataType("1");
        formFieldBean02.setSortOrder(2);
        formFieldBean02.setInitialValue("");
        
        //formFiled3個目　ドロップダウン
        FormFieldBean formFieldBean03 = new FormFieldBean(); 
        formFieldBean03.setFormFieldId("formField03");
        formFieldBean03.setFormFieldName("Importanceドロップダウン");
        formFieldBean03.setInputType("dropdown");
        
        List<DynamicDropDownBean> keyValueList03 = new ArrayList<DynamicDropDownBean>();
        DynamicDropDownBean dd03;
        List<Importance> importanceList = importanceRepository.selectImportanceList(false);
        for(int i=0;i<importanceList.size();i++){
            dd03 = new DynamicDropDownBean();
            dd03.setDropdownKey(importanceList.get(i).getImportanceId());
            dd03.setDropdownVal(importanceList.get(i).getImportanceNm());
            keyValueList03.add(dd03);
        }
        formFieldBean03.setKeyValueList(keyValueList03);
        formFieldBean03.setDataType("1");
        formFieldBean03.setSortOrder(3);
        List<String> valicheList03 = new ArrayList<String>();
        valicheList03.add(DynamicScreenItemValidationCheckEnum.NotNullCheck.toString());
        formFieldBean01.setValidationCheck(valicheList03);
        formFieldBean03.setInitialValue("");
        
        
        List<FormFieldBean> formFiledList = new ArrayList<FormFieldBean>();
        formFiledList.add(formFieldBean01);
        formFiledList.add(formFieldBean02);
        formFiledList.add(formFieldBean03);
        
        DynamicScreenItemOutputBean output = new DynamicScreenItemOutputBean();
        output.setFormId("01");
        output.setFormName("DynamicScreen01");
        output.setFormFiledList(formFiledList);
        
        return output;
    }

}
