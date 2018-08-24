package com.zsd.dic.service;

import com.zsd.comm.exception.ServiceException;
import com.zsd.comm.utils.Identities;
import com.zsd.dic.DicModule;
import com.zsd.dic.dao.DicDao;
import com.zsd.dic.domain.Dic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 字典服务管理实现类.
 * @author zhousd
 */
@Service
@Transactional
public class DicServiceImpl implements DicService {

    private static Logger logger = LoggerFactory.getLogger(DicServiceImpl.class);

    @Autowired
    private DicDao dicDao;

    @Override
    public Dic getById(String id) {
        try {
            return dicDao.getById(id);
        } catch (Exception e) {
            logger.debug("Get dic by id error", e);
            throw new ServiceException(DicModule.ERR_SEV_GETBYID, e);
        }
    }

    @Override
    public void save(Dic dic) {
        try {
            if (StringUtils.isEmpty(dic.getId())) {
                dic.setId(Identities.uuid());
            }
            dicDao.save(dic);
        } catch (Exception e) {
            logger.debug("Save dic error", e);
            throw new ServiceException(DicModule.ERR_SEV_SAVE, e);
        }

    }

    @Override
    public void update(Dic dic) {
        try {
            dicDao.update(dic);
        } catch (Exception e) {
            logger.debug("Update dic error", e);
            throw new ServiceException(DicModule.ERR_SEV_UPDATE, e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            dicDao.delete(id);
        } catch (Exception e) {
            logger.debug("Delete dic error!", e);
            throw new ServiceException(DicModule.ERR_DAO_DEL, e);
        }
    }

    /**
     * 根据父节点id查找所有子节点
     * @param parentId 父节点ID
     * @return
     */
    public List<Dic> getByParentId(String parentId){
        List<Dic> list = dicDao.getByParentId(parentId);
        this.getChrildren(list);
        return list;
    }

    /**
     * 递归设置子节点
     * @param list
     */
    public void getChrildren( List<Dic> list){
        for(Dic f : list){
            List<Dic> c = dicDao.getByParentId(f.getId());
            this.getChrildren(c);
            f.setChildren(c);
        }
    }

    /**
     * 根据字典名称查找字典
     * @param item 字典名
     * @return
     */
    public List<Dic> getByItem(String item){
        List<Dic> list = dicDao.getByItem(item);
        this.getChrildren(list);
        return list;
    }
}
