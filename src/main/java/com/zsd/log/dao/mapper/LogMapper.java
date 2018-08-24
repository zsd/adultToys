package com.zsd.log.dao.mapper;

import com.zsd.log.domain.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 业务日志数据库映射接口.
 * @author zhousd
 */
@Repository
public interface LogMapper {

    /**
     * 保存业务实体.
     * @param log 业务日志实体
     */
    void save(Log log);

    /**
     * 根据ID获取业务日志实体.
     * @param id 业务日志ID
     * @return 业务日志
     */
    Log getById(String id);

    /**
     * 分页查询.
     */
    List<Log> search(@Param("type") String type, @Param("description") String description,
                     @Param("skip") int skip, @Param("limit") int limit);



    /**
     * 根据类型\描述获取数量.
     * @param type 类型
     * @param description 描述
     * @return 数量
     */
    long count(@Param("type") String type, @Param("description") String description);

}
