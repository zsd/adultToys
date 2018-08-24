package com.zsd.authority.dao;

import com.zsd.authority.AuthorityModule;
import com.zsd.authority.dao.mapper.RoleMapper;
import com.zsd.authority.domain.Role;
import com.zsd.comm.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色管理数据访问类.
 * @author zhousd
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    private static Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户ID获取角色.
     * @param userId 用户Id
     * @return 角色
     */
    public List<Role> getByUserId(String userId) throws DataAccessException {
        try {
            return roleMapper.getByUserId(userId);
        } catch (Exception e) {
            logger.error("Get list by user id error!", e);
            throw new DataAccessException(AuthorityModule.ERR_DAO_ROLE_GETBYUSERID, e);
        }
    }

    @Override
    public void save(Role role) throws DataAccessException {
        try {
            roleMapper.save(role);
        } catch (Exception e) {
            logger.error("Save role error!", e);
            throw new DataAccessException(AuthorityModule.ERR_DAO_ROLE_SAVE, e);
        }
    }

    /**
     * 更新角色
     * @param role 角色
     */
    @Override
    public void update(Role role) throws DataAccessException {
        try {
            roleMapper.update(role);
        } catch (Exception e) {
            logger.error("Update role error!", e);
            throw new DataAccessException(AuthorityModule.ERR_DAO_ROLE_UPDATE, e);
        }
    }

    /**
     * 删除角色
     * @param id 角色主键id
     */
    @Override
    public void delete(String id) throws DataAccessException {
        try {
            roleMapper.delete(id);
        } catch (Exception e) {
            logger.error("Delete role error!", e);
            throw new DataAccessException(AuthorityModule.ERR_DAO_ROLE_DELETE, e);
        }
    }

    /**
     * 根据条件分页查询角色.
     * @param param 参数
     */
    @Override
    public List<Role> search(Map<String, Object> param) throws DataAccessException {
        try {
            return roleMapper.search(param);
        } catch (Exception e) {
            logger.error("Search role list error!", e);
            throw new DataAccessException(AuthorityModule.ERR_DAO_ROLE_SEARCH, e);
        }
    }

    /**
     * 根据条件查询数量.
     * @param param 参数
     */
    @Override
    public long count(Map<String, Object> param) throws DataAccessException {
        try {
            return roleMapper.count(param);
        } catch (Exception e) {
            logger.error("Search role count error!", e);
            throw new DataAccessException(AuthorityModule.ERR_DAO_ROLE_SEARCHCOUNT, e);
        }
    }

    @Override
    public void saveUserRole(String userId, String roleId) throws DataAccessException {
        try {
            roleMapper.saveUserRole(userId, roleId);
        } catch (Exception e) {
            logger.error("Save user role error!", e);
            throw new DataAccessException(AuthorityModule.ERR_DAO_ROLE_SAVEUSERROLE, e);
        }
    }

    @Override
    public void deleteByUserId(String userId) throws DataAccessException {
        try {
            roleMapper.deleteByUserId(userId);
        } catch (Exception e) {
            logger.error("Delete role list by user id error!", e);
            throw new DataAccessException(AuthorityModule.ERR_DAO_ROLE_DELETEBYUSERID, e);
        }
    }

    @Override
    public void deleteByRole(String roleId) throws DataAccessException {
        try {
            roleMapper.deleteByRole(roleId);
        } catch (Exception e) {
            logger.error("Delete role by role id error!", e);
            throw new DataAccessException(AuthorityModule.ERR_DAO_ROLE_DELETEBYROLE, e);
        }
    }
}
