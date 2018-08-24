package com.zsd.example.dao;

import com.zsd.comm.orm.Page;
import com.zsd.example.domain.Example;

import java.util.Map;

/**
 * 框架使用例子, 数据访问接口.
 * @author zhousd
 */
public interface ExampleDao {

    /**
    * 插入例子.
    * @param example 实体
    */
    void insert(Example example);

    /**
    * 更新例子.
    * @param example 实体
    */
    int update(Example example);

    /**
    * 删除例子.
    * @param id 实体主键
    */
    int delete(String id);

    /**
    * 获取例子.
    * @param id  实体主键
    */
    Example get(String id);

    /**
    * 查询例子.
    * @param page 分页对象
    * @param param 查询条件
    */
    Page<Example> search(Page<Example> page, Map<String, Object> param);

}
