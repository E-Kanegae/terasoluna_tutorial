
package todo5.app.manage;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import todo5.app.common.SessionAttributePageObj;
import todo5.app.todo.TodoForm;
import todo5.domain.model.Todo;
import todo5.domain.service.manage.TodoManagementService;

/**
 * TodoManagementController:検索・一覧表示・メール送信・ファイルダウンロード機能のControllerクラス
 * 
 * @author　E-Kanegae
 */
@SessionAttributes(types = {
        SessionAttributePageObj.class,
        TodoManageForm.class
})
@Controller
@RequestMapping("manage")
public class TodoManagementController {

    private static final Logger logger = LoggerFactory
            .getLogger(TodoManagementController.class);

    @Inject
    TodoManagementService todoManagementService;

    @ModelAttribute
    public TodoManageForm setUpForm() {
        TodoManageForm form = new TodoManageForm();
        return form;
    }

    @ModelAttribute
    public TodoForm setUpTodoForm() {
        TodoForm form = new TodoForm();
        return form;
    }

    @Inject
    Mapper beanMapper;

    /**
     * 検索・一覧画面表示処理用ハンドラメソッド
     * 
     * @param Model
     * @return manage/search.jsp
     * @throws なし
     */
    @RequestMapping(value = "init")
    public String init(Model model) {
        return "manage/search";
    }

    /**
     * 検索処理用ハンドラメソッド
     * 
     * @param TodoManageForm
     * @param Pageable
     * @param　Model
     * @return manage/search.jsp
     * @throws なし
     */
    @RequestMapping(value = "search")
    public String search(TodoManageForm todoManageForm, Pageable pageable, Model model,
            SessionAttributePageObj sessionPage) {

        // Pageableオブジェクトの中身をセッションにつめる。
        sessionPage.setPage(pageable.getPageNumber());
        sessionPage.setSize(pageable.getPageSize());

        Todo todo = beanMapper.map(todoManageForm, Todo.class);
        Page<Todo> todos = todoManagementService.search(todo, pageable);
        model.addAttribute("todos", todos);

        return "manage/search";
    }

    /**
     * Todo編集画面から遷移してきた場合の一時的ハンドラメソッド。 セッションからページ番号とページサイズを取得後、searchメソッドに遷移する。
     * 
     * @param TodoManageForm
     * @param SessionAttributePageObj
     * @return search()
     * @throws なし
     */
    @RequestMapping(value = "fromEdit")
    public String fromEdit(TodoManageForm todoManageForm, Model model,
            SessionAttributePageObj sessionPage) {
        return search(todoManageForm,
                new PageRequest(sessionPage.getPage(), sessionPage.getSize()), model, sessionPage);
    }

    /**
     * メール送信処理用ハンドラメソッド
     * 
     * @param なし
     * @return manage/search.jsp
     * @throws MailException
     */
    @RequestMapping(value = "mail")
    public String mail() {
        try {
            todoManagementService.send();
        } catch (MailException e) {
            logger.error("MailS-Sending Error!", e);
            return "manage/search";
        }

        return "manage/search";
    }
}
