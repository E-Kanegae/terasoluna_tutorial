package todo5.domain.repository.common;

import todo5.domain.model.UserAccount;

public interface UserAccountRepository {
	UserAccount findOne(String username);
//	UserAccount findOne(UserAccount account);
}
