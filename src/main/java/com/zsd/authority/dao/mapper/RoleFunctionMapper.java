package com.zsd.authority.dao.mapper;

import com.zsd.authority.domain.RoleFunction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色关联权限的Mapper类.
 * @author zhousd
 */
@Repository
public interface RoleFunctionMapper {

    /**
     * 获得用户全部权限.
     * @param roleId 角色ID
     */
    List<RoleFunction> getByRoleId(String roleId);

    /**
     * 保存角色权限.
     * @param roleFunction 角色权限
     */
    void save(RoleFunction roleFunction);

    /**
     * 删除一个权限
     * @param roleId 角色主键id
     * @param functionId 权限主键id
     */
    void delete(String roleId,String functionId);

    /**
     * 删除角色所有权限
     * @param roleId 角色主键id
     */
    void deleteByRoleId(String roleId);
}
