package com.zsd.authority.service;

import com.zsd.authority.dao.RoleFunctionDao;
import com.zsd.authority.domain.RoleFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by msx on 2016/6/24.
 * 权限服务实现类.
 */
@Service
@Transactional
public class RoleFunctionServiceImpl implements RoleFunctionService{

    private static Logger logger = LoggerFactory.getLogger(RoleFunctionServiceImpl.class);

    @Autowired
    private RoleFunctionDao roleFunctionDao;
    /**
     * 获得用户全部权限.
     * @param roleId 角色ID
     */
    @Override
    public List<RoleFunction> getByRoleId(String roleId){
        return roleFunctionDao.getByRoleId(roleId);
    }

    /**
     * 保存角色权限.
     * @param roleFunction 角色权限
     */
    @Override
    public void save(RoleFunction roleFunction){
        roleFunctionDao.save(roleFunction);
    }

    /**
     * 删除一个权限
     * @param roleId 角色主键id
     * @param functionId 权限主键id
     */
    @Override
    public void delete(String roleId,String functionId){
        roleFunctionDao.delete(roleId,functionId);
    }

    /**
     * 删除角色所有权限
     * @param roleId 角色主键id
     */
    @Override
    public void deleteByRoleId(String roleId){
        roleFunctionDao.deleteByRoleId(roleId);
    }
}
