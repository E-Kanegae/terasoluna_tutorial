package todo5.app.common;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;

@Getter
@Setter
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionPageObj implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private int page;
	private int size;

}
