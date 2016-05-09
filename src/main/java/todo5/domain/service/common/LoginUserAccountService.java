
package todo5.domain.service.common;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import todo5.domain.model.UserAccount;

@Service
public class LoginUserAccountService implements UserDetailsService {
    @Inject
    UserAccountSharedService userAccountSharedService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserAccount userAccount = userAccountSharedService.findOne(username);
            return new LoginUserAccount(userAccount);

        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException("user not found", e);
        }
    }

    // @Transactional(readOnly=true)
    // @Override
    // public UserDetails loadUserByUseraccount(UserAccount loginAccount) throws
    // UsernameNotFoundException {
    // try {
    // UserAccount userAccount =
    // userAccountSharedService.findOne(loginAccount.getUsername(),
    // loginAccount.getPassword());
    // return new LoginUserAccount(userAccount);
    //
    // } catch (ResourceNotFoundException e) {
    // throw new UsernameNotFoundException("user not found", e);
    // }
    // }

}
