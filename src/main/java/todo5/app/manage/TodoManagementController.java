package todo5.app.manage;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import todo5.app.todo.TodoForm;
import todo5.domain.model.Todo;
import todo5.domain.service.manage.TodoManagementService;

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
	
	/*
     * 検索・一覧画面表示処理
     */
	@RequestMapping(value = "init")
	public String init(Model model) {
		return "manage/search"; 
		}
	
	/*
     * 検索処理
     */	
	@RequestMapping(value = "search")
	public String search(TodoManageForm todoManageForm,Pageable pageable,Model model) {
		
		Todo todo = beanMapper.map(todoManageForm, Todo.class);
		Page<Todo> todos = todoManagementService.search(todo, pageable);
		model.addAttribute("todos", todos); 
		
		return "manage/search"; 
		}
	
	/*
     * メール送信処理
     */	
	@RequestMapping(value = "mail")
	public String mail() {

		try{
			todoManagementService.send();
		}catch(MailException e){
			logger.error("MailS-Sending Error!", e);
			return "manage/search";
		}
		
		return "manage/search"; 
	}
}
