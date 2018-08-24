package com.zsd.authority.dao;

import com.zsd.authority.dao.mapper.RoleFunctionMapper;
import com.zsd.authority.domain.RoleFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by msx on 2016/6/24.
 * 角色权限管理数据访问类.
 */
@Repository
public class RoleFunctionDaoImpl implements RoleFunctionDao{

    @Autowired
    private RoleFunctionMapper roleFunctionMapper;
    /**
     * 获得用户全部权限.
     * @param roleId 角色ID
     */
    @Override
    public List<RoleFunction> getByRoleId(String roleId){
        return roleFunctionMapper.getByRoleId(roleId);
    }

    /**
     * 保存角色权限.
     * @param roleFunction 角色权限
     */
    @Override
    public void save(RoleFunction roleFunction){
        roleFunctionMapper.save(roleFunction);
    }

    /**
     * 删除一个权限
     * @param roleId 角色主键id
     * @param functionId 权限主键id
     */
    @Override
    public void delete(String roleId,String functionId){
        roleFunctionMapper.delete(roleId,functionId);
    }

    /**
     * 删除角色所有权限
     * @param roleId 角色主键id
     */
    @Override
    public void deleteByRoleId(String roleId){
        roleFunctionMapper.deleteByRoleId(roleId);
    }
}
