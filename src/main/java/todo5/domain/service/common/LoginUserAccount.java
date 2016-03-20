package todo5.domain.service.common;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import todo5.domain.model.UserAccount;

public class LoginUserAccount extends User {
	
	private static final long serialVersionUID = 1L;
	private final UserAccount userAccount;
	
	public LoginUserAccount(UserAccount userAccount) {
		super(userAccount.getUsername(), userAccount.getPassword(), 
				AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.userAccount = userAccount;
	}
	
	public UserAccount getUserAccount() { 
			return userAccount;
	}

}
