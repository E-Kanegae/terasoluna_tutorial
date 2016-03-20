package todo5.domain.service.common;

import todo5.domain.model.UserAccount;

public interface UserAccountSharedService {
	UserAccount findOne(String username);
//	UserAccount findOne(String username, String password) ;
}
