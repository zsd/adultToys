package com.zsd.example.dao;

import java.util.List;
import java.util.Map;

import com.zsd.comm.orm.Page;
import com.zsd.comm.orm.mybatis.BaseDao;
import com.zsd.example.dao.mapper.ExampleMapper;
import com.zsd.example.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 框架使用例子, 数据访问实现类.
 * @author zhousd
 */
@Repository
public class ExampleDaoImpl extends BaseDao implements ExampleDao {

    @Autowired
    private ExampleMapper exampleMapper;

    public void insert(Example example) {
        exampleMapper.insert(example);
    }

    public int update(Example example) {
        return exampleMapper.update(example);
    }

    public int delete(String id) {
        return exampleMapper.delete(id);
    }

    public Example get(String id) {
        return exampleMapper.get(id);
    }

    public Page<Example> search(Page<Example> page, Map<String, Object> param) {
        int skip = (page.getPageNo() - 1) * page.getPageSize();
        int limit = page.getPageSize();
        String name = param.get("name") == null ? null: param.get("name").toString();
        String gender = param.get("gender") == null ? null : param.get("name").toString();
        List<Example> lists = exampleMapper.search(name, gender, skip, limit);
        long count = exampleMapper.count(name, gender);
        page.setResult(lists);
        page.setTotalCount(count);
        return page;
    }

}
