package com.zsd.authority.dao.mapper;

import com.zsd.authority.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色Mapper类.
 * @author zhousd
 */
@Repository
public interface RoleMapper {

    /**
     * 根据用户ID获取角色.<p/>
     * @param userId 岗位ID
     * @return 角色
     */
    List<Role> getByUserId(String userId);

    /**
     * 保存角色.
     * @param role 角色
     */
    void save(Role role);

    /**
     * 更新角色
     * @param role 角色
     */
    void update(Role role);

    /**
     * 删除角色
     * @param id 角色主键id
     */
    void delete(String id);

    /**
     * 根据条件分页查询角色
     * @param param 参数
     * @return
     */
    List<Role> search(Map<String, Object> param);

    /**
     * 根据条件查询角色总数
     * @param param 参数
     * @return
     */
    long count(Map<String, Object> param);

    /**
     * 保存组织角色关联
     * @param userId 用户ID
     * @param roleId 角色ID
     */
    void saveUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 根据用户ID删除组织角色关联
     * @param userId 用户ID
     */
    void deleteByUserId(String userId);

    /**
     * 根据角色ID删除组织角色关联
     * @param roleId 角色ID
     */
    void deleteByRole(String roleId);
}
