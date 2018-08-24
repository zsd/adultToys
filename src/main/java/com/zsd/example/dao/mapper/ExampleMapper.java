package com.zsd.example.dao.mapper;

import com.zsd.example.domain.Example;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 例子程序映射接口.
 * @author zhousd
 */
@Repository
public interface ExampleMapper {

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
     */
    List<Example> search(@Param("name") String name, @Param("gender") String gender, @Param("skip") int skip, @Param("limit") int limit);

    /**
     * 根据姓名\性别获取数量.
     * @param name 姓名
     * @param gender 性别
     * @return 数量
     */
    long count(@Param("name") String name, @Param("gender") String gender);
}
