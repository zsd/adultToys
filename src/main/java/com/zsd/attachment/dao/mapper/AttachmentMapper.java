package com.zsd.attachment.dao.mapper;

import com.zsd.attachment.domain.Attachment;
import com.zsd.user.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xierh
 * 附件映射接口.
 */
@Repository
public interface AttachmentMapper {

    /**
     * 插入附件
     *
     * @param attachment 附件实体对象
     * @param user       用户对象
     */
    void insert(@Param("attachment") Attachment attachment, @Param("user") User user);

    /**
     * 更新附件
     *
     * @param attachment 附件实体对象
     * @param user       用户对象
     */
    void update(@Param("attachment") Attachment attachment, @Param("user") User user);

    /**
     * 更新附件新名称
     *
     * @param id         附件流水号
     * @param newName    新名称
     * @param updateTime 更新时间
     */
    void updateNewName(@Param("id") String id, @Param("newName") String newName, @Param("user") User user,
                       @Param("updateTime") Date updateTime);

    /**
     * 删除附件
     *
     * @param id 附件流水号
     */
    void delete(@Param("id") String id);

    /**
     * 获取附件
     *
     * @param id 附件流水号
     */
    Attachment find(@Param("id") String id);

    /**
     * 获取附件集合
     *
     * @param entityId 与附件关联的实体对象流水号
     */
    List<Attachment> finds(@Param("id") String entityId);

    /**
     * 根据业务对象ID和业务类型获取附件列表.
     */
    List<Attachment> getList(@Param("objectId") String objectId, @Param("type") int type);

    /**
     * 根据条件获取附件分页列表.
     */
    List<Attachment> search(Map<String, Object> param);

    /**
     * 分页列表数据量.
     */
    long count(Map<String, Object> param);
}
