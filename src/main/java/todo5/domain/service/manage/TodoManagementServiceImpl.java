package todo5.domain.service.manage;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import todo5.domain.model.Todo;
import todo5.domain.repository.todo.TodoRepository;

@Service
public class TodoManagementServiceImpl implements TodoManagementService {

	@Inject
	TodoRepository todoRepository;
	
	@Inject
	JavaMailSender mailSender;
    @Value("${mail.address.to}") 
    private String mailTo;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Todo> search(Todo todo, Pageable pageable) {
		
		long total = todoRepository.countTodo(todo);
		
		List<Todo> todos;
		
		if (0 < total) {
            RowBounds rowBounds = new RowBounds(pageable.getOffset(),
                pageable.getPageSize());
            todos = todoRepository.findAll(todo, rowBounds);
        } else {
            todos = Collections.emptyList();
        }
        return new PageImpl<>(todos, pageable, total);
	}
	
	public void send() throws MailException {
		
	    mailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				 MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
		                    StandardCharsets.UTF_8.name()); 
		            helper.setFrom("terasoluna Test<terasoluna_tutrial@terasoluna.com>");
		            helper.setTo(mailTo); 
		            helper.setSubject("Test"); 
		            String text = "Hi "
		                    + ", welcome to EXAMPLE.COM!\r\n"
		                    + "If you were not an intended recipient, Please notify the sender.";
		            helper.setText(text); 
			}
	    	
	    });
	}
	
}
