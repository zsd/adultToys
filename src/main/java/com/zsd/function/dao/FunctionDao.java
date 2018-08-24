package com.zsd.function.dao;

import com.zsd.function.domain.Function;
import com.zsd.function.domain.TreeNode;

import java.util.List;

/**
 * Created by 黄辅湘 on 2016/6/22.
 */
public interface FunctionDao {

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
     * 根据父节点删除功能列表.
     */
    void deleteByParent(List<String> list);

    /**
     * 获取所有节点信息.
     */
    List<TreeNode> getAllNode();

    /**
     * 根据用户获取列表.
     */
    List<Function> getByUserId(String userId, String parentId);

    /**
     * 编号判断.
     */
    long checkedCode(String id, String code);

    /**
     * 根据用户获取按钮权限.
     */
    List<Function> getButtonByUserId(String userId);
}
