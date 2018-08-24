package com.zsd.function.service;

import com.zsd.comm.utils.Identities;
import com.zsd.function.dao.FunctionDao;
import com.zsd.function.domain.Function;
import com.zsd.function.domain.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 功能管理服务类实现.
 * @author zhousd
 */
@Service
@Transactional
public class FunctionServiceImpl implements  FunctionService{

    private static Logger logger = LoggerFactory.getLogger(FunctionServiceImpl.class);

    @Autowired
    private FunctionDao functionDao;

    @Override
    public List<Function> getByParentId(String parentId) {
        List<Function> list = functionDao.getByParentId(parentId);
        this.getChrildren(list);
        return list;
    }

    public void getChrildren( List<Function> list){
        for(Function f : list){
            List<Function> c = functionDao.getByParentId(f.getId());
            this.getChrildren(c);
            f.setChildren(c);
        }
    }

    @Override
    public void save(Function function) {
        if (StringUtils.isEmpty(function.getId())) {
            function.setId(Identities.uuid());
        }
        functionDao.save(function);
    }

    @Override
    public void update(Function function) {
        functionDao.update(function);
    }

    @Override
    public void delete(String id) {
        List<String> allList = new ArrayList<String>();
        allList.add(id);
        getAllFunctionByID(id, allList);
        if(allList.size()>0){
            functionDao.deleteByParent(allList);
        }
    }

    public void getAllFunctionByID(String id,List<String> list) {
        List<Function> chilrens = functionDao.getByParentId(id);
        for(int i=0;i<chilrens.size();i++){
            Function d =   chilrens.get(i);
            list.add(d.getId());
            getAllFunctionByID(d.getId(),list);
        }
    }

    @Override
    public List<TreeNode> getAllNode(){
        return functionDao.getAllNode();
    }

    @Override
    public List<Function> getByUserId(String userId, String parentId) {
        return functionDao.getByUserId(userId, parentId);
    }

    @Override
    public Map<String, Object> getMapByUserId(String userId, List<Function> list) {
        Map<String, Object> map1 = new HashMap<>() ;
        for (int i = (list.size() - 1); i >= 0; i--) {
            String id  = list.get(i).getId() ;
            List<Function> f = functionDao.getByUserId(userId, id);
            getByUserId2(userId, f);
            map1.put(id,f);
        }
        return map1;
    }

    private void getByUserId2(String userId, List<Function> list){
        for(Function f : list){
            List<Function> c = functionDao.getByUserId(userId, f.getId());
            this.getByUserId2(userId, c);
            f.setChildren(c);
        }
    }

    @Override
    public long checkedCode(String id, String code) {
        return functionDao.checkedCode(id,code);
    }

    @Override
    public Set<String> getButtonByUserId( String userId) {
        Set<String> s = new HashSet<>();
        List<Function> list = functionDao.getButtonByUserId(userId);
        for(int i=0;i<list.size();i++){
            s.add(list.get(i).getCode());
        }
        return s;
    }
}
