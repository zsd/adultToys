package com.zsd.frontset.service;

import bussiness.comm.MD5Encryption;
import com.zsd.comm.context.SecurityContextHolder;
import com.zsd.comm.utils.Constants;
import com.zsd.function.service.FunctionService;
import com.zsd.user.domain.User;
import com.zsd.user.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 用户服务实现类.
 * @author zhousd
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private FunctionService functionService;

    @Override
    public String login(User user) {
        try {
            User realUser = userService.getByLoginName(user.getLoginName());
            if (ObjectUtils.equals(realUser, null)) {
                return Constants.NOT_EXIST_LOGINNAME;
            }
            if (!realUser.getLoginName().equals(user.getLoginName())
                    || !MD5Encryption.checkpassword(user.getPassword(),realUser.getPassword())) {
                return Constants.LOGINNAME_OR_PASSWORD_ERROR;
            }

            Set<String> buttonSet =  functionService.getButtonByUserId(realUser.getId());
            SecurityContextHolder.setUser(realUser);
            SecurityContextHolder.setAuthoritySet(buttonSet);

        } catch (Exception e) {
            e.printStackTrace();
            return Constants.SYSTEM_ERROR;
        }
        return null;
    }

    @Override
    public void logout() {
        SecurityContextHolder.destroy();
    }

}
