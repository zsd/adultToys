package com.zsd.user.service;

import bussiness.comm.MD5Encryption;
import com.zsd.comm.orm.Page;
import com.zsd.comm.utils.Identities;
import com.zsd.user.dao.UserDao;
import com.zsd.user.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 用户服务实现类.
 * @author zhousd
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getByLoginName(String loginName) {
        try {
            return userDao.getByLoginName(loginName);
        } catch (Exception e) {
            logger.error("get user by loginname error!", e);
        }
        return null;
    }

    @Override
    public User getById(String id) {
        try {
            User user = userDao.getById(id);
            return user;
        } catch (Exception e) {
            logger.error("get user by id error!", e);
        }
        return null;
    }

    @Override
    public void save(User user) {
        try {
            if (user != null && StringUtils.isEmpty(user.getId())) {
                user.setId(Identities.uuid());
            }
            //对密码进行加密
            user.setPassword(MD5Encryption.EncoderByMd5(user.getPassword()));
            userDao.save(user);
        } catch (Exception e){
            logger.error("save user error!", e);
        }
    }

    @Override
    public void update(User user) {
        try {
            //对密码进行加密
            user.setPassword(MD5Encryption.EncoderByMd5(user.getPassword()));
            userDao.update(user);
        } catch (Exception e){
            logger.error("update user error!", e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            userDao.delete(id);
        } catch (Exception e) {
            logger.error("delete user by id error!", e);
        }
    }

    @Override
    public void freeze(String id) {
        try {
            userDao.freeze(id);
        } catch (Exception e) {
            logger.error("freeze user by id error!", e);
        }
    }

    @Override
    public void deleteIdStr(String idStr) {
        if (StringUtils.isBlank(idStr)) {
            return;
        }
        String[] ids = idStr.split(",");
        for (String id : ids) {
            this.delete(id);
        }
    }

    @Override
    public Page<User> search(Page<User> page, Map<String, Object> param) {
        try {
            return userDao.search(page, param);
        } catch (Exception e) {
            logger.error("search user error!", e);
        }
        return null;
    }

    @Override
    public long checkLoginName(String id,String loginName){
        try {
            return userDao.checkLoginName(id,loginName);
        } catch (Exception e) {
            logger.error("check user by loginname error!", e);
        }
        return 0;
    }
}
