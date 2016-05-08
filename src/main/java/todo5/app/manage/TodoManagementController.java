package todo5.app.manage;

import javax.inject.Inject;
import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public String search(@Valid TodoManageForm todoManageForm,Pageable pageable,Model model) {
		
		Todo todo = beanMapper.map(todoManageForm, Todo.class);
		Page<Todo> todos = todoManagementService.search(todo, pageable);
		model.addAttribute("todos", todos); 
		
		return "manage/search"; 
		}
}
