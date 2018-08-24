package com.zsd.authority.service;

import com.zsd.authority.domain.Role;
import com.zsd.comm.exception.ServiceException;
import com.zsd.comm.orm.Page;
import com.zsd.user.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 角色服务接口.
 * @author zhousd
 */
public interface RoleService {

    /**
     * 根据用户ID获取角色.
     * @param userId 用户ID
     * @return 角色实体
     * @throws ServiceException 服务异常
     */
    List<Role> getByUserId(String userId) throws ServiceException;

    /**
     * 保存角色.
     * @param role 角色
     * @throws ServiceException 服务异常
     */
    void save(Role role) throws ServiceException;

    /**
     * 删除角色.
     * @param id 角色主键id
     */
    void delete(String id);

    /**
     * 根据ID字符串删除相应记录.
     * @param idStr ID字符串
     */
    void deleteIdStr(String idStr);

    /**
     * 根据条件分页查询角色.
     * @param param 参数
     */
    Page<Role> search(Page<Role> page, Map<String, Object> param);

    /**
     * 保存用户角色关联.
     * @param user 用户
     */
    void saveUserRole(User user);
}
