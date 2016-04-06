package todo5.domain.service.common;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import todo5.domain.model.UserAccount;
import todo5.domain.repository.common.UserAccountRepository;

@Service
public class UserAccountSharedServiceImpl implements UserAccountSharedService {
	
	@Inject
	UserAccountRepository userAccountRepository;
	
	@Inject
	BCryptPasswordEncoder bCryptPasswordEncoder;
	/**
	 * Input:String ログインID
	 * Output:UserAccountオブジェクト
	 * document:ログインIDのみを入力値として、UserAcount情報を取得するメソッド
	 *　　　　　　　　Accountが取得できないとResourceNotFoundExceptionを返す。　
	 */
	@Transactional(readOnly=true)
	@Override
	public UserAccount findOne(String username) {
		UserAccount account = userAccountRepository.findOne(username);
		
		if (account == null) {
			throw new ResourceNotFoundException("The given account is not found! username=" + username);
		}
		return account;
	}
	
//	/**
//	 * Input:String ログインID, String ログインパスワード
//	 * Output:UserAccountオブジェクト
//	 * document:ログインID、ログインパスワードを入力値として、UserAcount情報を取得するメソッド
//	 *　　　　　　　　Accountが取得できないとResourceNotFoundExceptionを返す。　
//	 */
//	@Transactional(readOnly=true)
//	@Override
//	public UserAccount findOne(String username, String password) {
//	
//		UserAccount loginAccount = new UserAccount();
//		loginAccount.setUsername(username);
//		loginAccount.setPassword(password);
//		
//		UserAccount account = userAccountRepository.findOne(loginAccount);
//		
//		if (account == null) {
//			throw new ResourceNotFoundException("The given account is not found! username=" + username);
//		}
//		
//		return account;
//	}

}
