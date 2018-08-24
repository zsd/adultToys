package com.zsd.function.dao.mapper;

import com.zsd.function.domain.Function;
import com.zsd.function.domain.TreeNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 功能数据访问接口.
 * @author zhousd
 */
@Repository
public interface FunctionMapper {

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
     * 根据岗位获取列表.
     */
    List<Function> getByUserId(@Param("userId") String userId, @Param("parentId") String parentId);

    /**
     * 编号判断.
     */
    long checkedCode(@Param("id") String id, @Param("code") String code);

    /**
     * 根据用户获取按钮权限.
     */
    List<Function> getButtonByUserId(@Param("userId") String userId);
}
