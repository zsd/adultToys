package com.zsd.function.service;

import com.zsd.function.domain.Function;
import com.zsd.function.domain.TreeNode;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 功能服务.
 * @author huangfx
 */
public interface FunctionService {

    /**
     * 根据项目名获取字典值列表.
     */
    List<Function> getByParentId(String parentId);

    /**
     * 保存.
     */
    void save(Function function);

    /**
     * 更新.
     */
    void update(Function function);

    /**
     * 根据ID删除.
     */
    void delete(String id);

    /**
     * 获取所有节点信息.
     */
    List<TreeNode> getAllNode();

    /**
     * 根据岗位ID获取列表.
     * 根菜单，需要传入"root"作为parentId
     */
    List<Function> getByUserId(String userId,String parentId);

    /**
     * 根据用户获取权限.
     */
    Map<String, Object> getMapByUserId(String userId, List<Function> list);

    /**
     * 编号判断.
     */
    long checkedCode(String id,String code);

    /**
     * 根据用户获取按钮权限.
     */
    Set<String> getButtonByUserId(String userId);
}
