package todo5.app.todo;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import botdetect.web.Captcha;
import todo5.app.common.SessionPageObj;
import todo5.app.todo.TodoForm.TodoCreate;
import todo5.app.todo.TodoForm.TodoDelete;
import todo5.app.todo.TodoForm.TodoDetail;
import todo5.app.todo.TodoForm.TodoEdit;
import todo5.domain.model.Todo;
import todo5.domain.service.todo.TodoService;

@Controller
@RequestMapping("todo")
public class TodoController {
	
	@Inject
	TodoService todoService;
	
	@Inject
	Mapper beanMapper;
	
	//ページネーションのpage, size用セッションオブジェクト
	@Inject
	SessionPageObj sessionPageObj;
	
	@ModelAttribute
	public TodoForm setUpForm() {
		TodoForm form = new TodoForm();
		return form;
		}
	
    /*
     * Todoリスト検索処理
     */
	@RequestMapping(value = "list")
	public String list(@PageableDefault(
	                page = 0, 
	                size = 5,
	                direction = Direction.DESC,
	                sort = {
	                    "publishedDate",
	                    "articleId"
	                    }
	                ) Pageable pageable,
	                Model model) {
		
		Page<Todo> todos = todoService.findAll(pageable);
		model.addAttribute("todos", todos); 
		return "todo/list"; 
		}
	
    /*
     * タスク詳細画面表示処理
     */
	@RequestMapping(value = "detail")
	public String detail(@Valid TodoForm todoForm, BindingResult bindingResult,Pageable pageable,Model model) {
		
		Todo todo = todoService.findOne(todoForm.getTodoId());
		model.addAttribute(todo);
		
		PageableHandlerMethodArgumentResolver pageResolve = new PageableHandlerMethodArgumentResolver();

		//Pageableオブジェクトがデフォルト値でなければ
		if (!pageResolve.isFallbackPageable(pageable)){
			//SpringFrameworkのsessionスコープへのpage, sizeのセット
			sessionPageObj.setPage(pageable.getPageNumber());
			sessionPageObj.setSize(pageable.getPageSize());
		}
		
		return "todo/detail"; 
		}
	
    /*
     * タスク情報編集画面表示処理
     */
	@RequestMapping(value = "editPage")
	public String editPage(@Validated({ Default.class, TodoDetail.class }) TodoForm todoForm, Model model) {
		
		Todo todo = todoService.findOne(todoForm.getTodoId());
		model.addAttribute(todo);
		
		return "todo/edit"; 
		}
	
    /*
     * タスク情報編集処理
     */
	@RequestMapping(value = "edit")
	public String edit(@Validated({ Default.class, TodoEdit.class }) TodoForm todoForm, 
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
        	return "todo/list";
		}
		
		Todo todo = beanMapper.map(todoForm, Todo.class);
		
		try{
			todo = todoService.edit(todo);
		}catch(BusinessException e){
			//Save失敗メッセージをセットする。
			model.addAttribute(e.getResultMessages());
			return "todo/list";
		}		
		model.addAttribute(todo);
		return "todo/detail"; 
	}

    /*
     * Todoタスク作成処理
     */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Validated({ Default.class, TodoCreate.class }) TodoForm todoForm, BindingResult bindingResult,
			Model model, RedirectAttributes attributes, HttpServletRequest  req) {
		
		if (bindingResult.hasErrors()) {
        	return "todo/list";
		}
		
		//BotDetect実装
		Captcha captcha = Captcha.load(req, "basicExampleCaptcha");
		boolean isHuman = captcha.validate(req, todoForm.getCaptchaCodeTextBox());
		if (!isHuman) {
			return "todo/list";
		} 

		Todo todo = beanMapper.map(todoForm, Todo.class);
		
		try {
			todoService.create(todo);
		} catch (BusinessException e) {
			
			model.addAttribute(e.getResultMessages());
			
			return "todo/list";
		}
			
			attributes.addFlashAttribute(ResultMessages.success().add(
			ResultMessage.fromText("Created successfully!")));
			return "redirect:/todo/list";
	}
	
    /*
     * タスククローズ処理(非同期)
     */
	@RequestMapping(value = "finish", method = RequestMethod.GET)
	@ResponseBody
	public TodoFinishResult finish(@Validated TodoFinishRequest req, BindingResult bindingResult) {
	
		TodoFinishResult result = new TodoFinishResult();
		
		if (bindingResult.hasErrors()) {
			//エラーメッセージを設定する。
			result.setFinished(false);
			result.setErrMsg("TodoId has not sent to AP server.");


			return result;
		}
		
		try {
			todoService.finish(req.getTodoId());
		} catch (BusinessException e) {
			result.setFinished(false);
			result.setErrMsg(e.getResultMessages().toString());
			return result;
		}
		result.setTodoId(req.getTodoId());
		result.setTodoTitle(req.getTodoTitle());
		result.setFinished(true);
		
		return result;
	}
	
    /*
     * タスク削除処理
     */
	@RequestMapping(value = "delete", method = RequestMethod.POST) // (22)
	public String delete(@Validated({ Default.class, TodoDelete.class }) TodoForm form,
			BindingResult bindingResult, Model model,RedirectAttributes attributes) {
	if (bindingResult.hasErrors()) {
		return "todo/list";
	}
	try {
		todoService.delete(form.getTodoId());
	} catch (BusinessException e) {
		model.addAttribute(e.getResultMessages());
		return "todo/list";
	}
	attributes.addFlashAttribute(ResultMessages.success().add(
	ResultMessage.fromText("Deleted successfully!")));
	
	return "redirect:/todo/list";
	}
}
