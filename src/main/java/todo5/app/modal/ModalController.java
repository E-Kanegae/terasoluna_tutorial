/**
 * @(#)ModalController.java
 * 
 * Copyright (c) 2017 E-Kanegae.
 */
package todo5.app.modal;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import todo5.domain.model.Todo;
import todo5.domain.service.manage.TodoManagementService;

/**
 * [このクラスの説明を書きましょう]
 * @author E-Kanegae
 * @version $Revision$
 */
@RequestMapping("modal")
@Controller
public class ModalController {

    @ModelAttribute
    public ModalForm setUpForm() {
        ModalForm form = new ModalForm();
        return form;
    }

    @Inject
    Mapper beanMapper;
    
    @Inject
    TodoManagementService todoManagementService;
    
    /**
     * 検索処理用ハンドラメソッド
     * 
     * @param ModalForm
     * @param Pageable
     * @param　Model
     * @return manage/search.jsp
     * @throws なし
     */
    @RequestMapping(value = "search")
    public String search(ModalForm modalForm, Pageable pageable, Model model) {

//        // Pageableオブジェクトの中身をセッションにつめる。
//        sessionPage.setPage(pageable.getPageNumber());
//        sessionPage.setSize(pageable.getPageSize());

        Todo todo = beanMapper.map(modalForm, Todo.class);
        Page<Todo> todos = todoManagementService.search(todo, pageable);
        model.addAttribute("todos", todos);

        return "account/view";
    }

}
