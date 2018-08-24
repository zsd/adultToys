package com.zsd.authority.dao;

import com.zsd.authority.domain.Role;
import com.zsd.comm.exception.DataAccessException;

import java.util.List;
import java.util.Map;

/**
 * 角色管理数据访问接口.
 * @author zhousd
 */
public interface RoleDao {

    /**
     * 根据用户ID获取角色.
     * @param userId 用户Id
     * @return 角色
     * @throws DataAccessException 数据访问异常
     */
    List<Role> getByUserId(String userId) throws DataAccessException;

    /**
     * 保存角色.
     * @param role 角色
     * @throws DataAccessException 服务异常
     */
    void save(Role role) throws DataAccessException;

    /**
     * 更新角色
     * @param role 角色
     */
    void update(Role role) throws DataAccessException;;

    /**
     * 删除角色
     * @param id 角色主键id
     */
    void delete(String id) throws DataAccessException;;

    /**
     * 根据条件分页查询角色
     * @param param 参数
     */
    List<Role> search(Map<String, Object> param) throws DataAccessException;;

    /**
     * 根据条件查询角色总数
     * @param param 参数
     */
    long count(Map<String, Object> param) throws DataAccessException;;

    /**
     * 保存组织角色关联
     * @param userId 用户ID
     * @param roleId 角色ID
     */
    void saveUserRole(String userId, String roleId) throws DataAccessException;;

    /**
     * 根据组织机构ID删除组织角色关联
     * @param userId 用户ID
     */
    void deleteByUserId(String userId) throws DataAccessException;;

    /**
     * 根据角色ID删除组织角色关联
     * @param roleId 组织机构ID
     */
    void deleteByRole(String roleId) throws DataAccessException;;

}
