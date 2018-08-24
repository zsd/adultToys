package com.zsd.comm.orm.mybatis;

import org.apache.commons.lang3.ArrayUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseDao extends SqlSessionDaoSupport {

	@Autowired
	public void setSimpleTemplate(SimpleSqlSessionTemplate sessionTemplate) {
		super.setSqlSessionTemplate(sessionTemplate);
	}

	public SimpleSqlSessionTemplate getSimpleTemplate() {
		return (SimpleSqlSessionTemplate) super.getSqlSession();
	}

	public String sqlId(String id) {
		return getNamespace().append(".").append(id).toString();
	}

	private StringBuffer getNamespace() {
		String prefix = null;
		Class<?>[] classes = this.getClass().getInterfaces();
		if (ArrayUtils.isNotEmpty(classes)) {
			prefix = classes[0].getName();
		} else {
			throw new IllegalArgumentException("请检查是否实现接口");
		}
		return new StringBuffer(prefix);
	}
}
