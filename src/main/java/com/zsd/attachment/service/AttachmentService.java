package com.zsd.attachment.service;

import com.zsd.attachment.domain.Attachment;
import com.zsd.comm.domain.Entity;
import com.zsd.comm.orm.Page;
import com.zsd.user.domain.User;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author xierh
 * 附件访问服务接口.
 */
public interface AttachmentService {

    /**
     * 插入附件
     *
     * @param attachment 附件实体对象
     * @param user       用户对象
     */
    void insert(Attachment attachment, User user);

    /**
     * 更新附件
     *
     * @param attachment 附件实体对象
     * @param user       用户对象
     */
    void update(Attachment attachment, User user);

    /**
     * 更新附件新名称
     *
     * @param id      附件流水号
     * @param newName 附件新名称
     * @param user    用户对象
     */
    void updateNewName(String id, String newName, User user);

    /**
     * 删除附件
     *
     * @param attachment 附件实体对象
     */
    void delete(Attachment attachment);

    /**
     * 删除附件
     *
     * @param id 附件流水号
     */
    void delete(String id);

    /**
     * 获取附件
     *
     * @param id 附件流水号
     */
    Attachment find(String id);

    /**
     * 获取附件集合
     *
     * @param entity 与附件关联的实体对
     */
    List<Attachment> finds(Entity entity);

    /**
     * 获取附件集合
     *
     * @param entityId 与附件关联的实体对象流水号
     */
    List<Attachment> finds(String entityId);

    /**
     * 将上传文件写入HDFS文件系统
     * @param in 文件输入流
     * @param fileName 保存到HADOOP的文件名称，格式：附件编号+文件后缀
     * @return 返回系统文件路径
     */
    String write2HDFS(InputStream in, String fileName);

    /**
     * 将上传文件写入HDFS文件系统，按年份存储
     * @param in 文件输入流
     * @param fileName 保存到HADOOP的文件名称，格式：附件编号+文件后缀
     * @return 返回系统文件路径
     */
    String write2HDFSByYear(InputStream in, String fileName);

    /**
     * 从HDFS文件系统读出文件
     * @param path 上传路径
     * @return
     */
    InputStream readFromDHFS(String path);

    /**
     * 将上传文件写入HDFS文件系统，按年份存储
     * @param in 文件输入流
     * @param fileName 保存到server的文件名称，格式：附件编号+文件后缀
     * @return 返回系统文件路径
     */
    String write2ServerByYear(InputStream in, String fileName);

    /**
     * 根据业务对象ID和业务类型获取附件
     * @param objectId
     * @param type
     * @return
     */
    List<Attachment> getList(String objectId, int type);

    /**
     * 根据条件获取附件分页列表
     * @return
     */
    Page<Attachment> search(Page<Attachment> page, Map<String, Object> param);

    /**
     * 从服务器系统读出文件
     * @param path 上传路径
     * @return
     */
    FileInputStream readFromServer(String path);
}
