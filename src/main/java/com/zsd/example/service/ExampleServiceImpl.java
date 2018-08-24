package com.zsd.example.service;

import java.util.Map;

import com.zsd.comm.orm.Page;
import com.zsd.comm.utils.Identities;
import com.zsd.example.dao.ExampleDao;
import com.zsd.example.domain.Example;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 框架使用例子, 服务实现类.
 * @author zhousd
 */
@Service
@Transactional
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleDao dao;

    @Override
    public Boolean save(Example example) {
        if (StringUtils.isBlank(example.getId())) {
            example.setId(Identities.uuid());
            dao.insert(example);
            return true;
        } else {
            dao.update(example);
            return true;
        }
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Example get(String id) {
        return dao.get(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Example> search(Page<Example> page, Map<String, Object> param) {
        return dao.search(page, param);
    }

    @Override
    public int update(Example example) {
        return dao.update(example);
    }

}
