package com.zsd.user.service;

import com.zsd.comm.orm.Page;
import com.zsd.user.domain.User;

import java.util.Map;

/**
 * 用户服务接口.
 * @author zhousd
 */
public interface UserService {

    /**
     * 根据登录名获取用户.
     */
    User getByLoginName(String loginName);

    /**
     * 根据id获取用户.
     */
    User getById(String id);

    /**
     * 保存用户.
     */
    void save(User user);

    /**
     * 更新用户.
     */
    void update(User user);

    /**
     * 删除用户.
     */
    void delete(String id);

    /**
     * 冻结用户.
     */
    void freeze(String id);

    /**
     * 批量删除用户.
     */
    void deleteIdStr(String idStr);

    /**
     * 分页列表.
     */
    Page<User> search(Page<User> page, Map<String, Object> param);

    /**
     * 添加用户登录名判断.
     */
    long checkLoginName(String id, String loginName);
}
