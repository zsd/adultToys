package com.zsd.authority.service;

import com.zsd.authority.dao.RoleDao;
import com.zsd.authority.dao.RoleFunctionDao;
import com.zsd.authority.domain.Role;
import com.zsd.comm.exception.ServiceException;
import com.zsd.comm.orm.Page;
import com.zsd.comm.utils.Identities;
import com.zsd.user.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 角色服务实现类.
 * @author zhousd
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleFunctionDao roleFunctionDao;

    @Override
    public List<Role> getByUserId(String userId) throws ServiceException {
        try {
            return roleDao.getByUserId(userId);
        } catch (Exception e) {
            logger.error("Get list by user id error!", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Role role) throws ServiceException {
        try {
            if (role != null && StringUtils.isEmpty(role.getId())) {
                role.setId(Identities.uuid());
                roleDao.save(role);
            }else{
                roleDao.update(role);
            }
        } catch (Exception e) {
            logger.error("Save role error!", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Page<Role> search(Page<Role> page, Map<String, Object> param) {
        try {
            //分页查询条件
            int skip = (page.getPageNo() - 1) * page.getPageSize();
            int limit = page.getPageSize();
            param.put("skip", skip);
            param.put("limit", limit);
            //分页查询结果
            List<Role> povertyObjList = roleDao.search(param);
            //根据条件查询总数
            long count = roleDao.count(param);

            page.setResult(povertyObjList);
            page.setTotalCount(count);
            return page;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return page;

    }

    @Override
    public void saveUserRole(User user) {
        if (user == null || user.getRole() == null) {
            return;
        }
        roleDao.deleteByUserId(user.getId());
        roleDao.saveUserRole(user.getId(), user.getRole().getId());
    }

    @Override
    public void delete(String id) {
        //删除角色
        roleDao.delete(id);
        //删除角色权限表
        roleFunctionDao.deleteByRoleId(id);
        //删除岗位分配的角色
        roleDao.deleteByRole(id);
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
}
