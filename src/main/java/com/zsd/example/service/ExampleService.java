package com.zsd.example.service;

import com.zsd.comm.orm.Page;
import com.zsd.example.domain.Example;

import java.util.Map;

/**
 * 框架使用例子, 服务接口.
 * @author zhousd
 */
public interface ExampleService {

    /**
     * 插入例子.
     * @param example 实体
     */
    Boolean save(Example example);

    /**
     * 删除例子.
     * @param id 主键id
     */
    int delete(String id);

    /**
     * 获取例子.
     * @param id 实体主键
     * @return 返回实体对象
     */
    Example get(String id);

    /**
     * 分页查询例子.
     * @param page 分页对象
     * @param param  查询条件
     * @return 实例分页列表
     */
    Page<Example> search(Page<Example> page, Map<String, Object> param);

    /**
     * 更新实例.
     * @param example 实例
     * @return 返回更新条数
     */
    int update(Example example);

}
