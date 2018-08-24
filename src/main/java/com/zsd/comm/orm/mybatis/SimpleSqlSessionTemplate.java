package com.zsd.comm.orm.mybatis;

import java.util.Arrays;
import java.util.List;

import com.zsd.comm.orm.Page;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * 系统基础的DAO模板类
 */
public class SimpleSqlSessionTemplate extends SqlSessionTemplate {

	/**
	 * 构造函数, 向父类的构造函数中注入参数
	 * 
	 * @param sqlSessionfactory
	 * @param executorType
	 */
	public SimpleSqlSessionTemplate(SqlSessionFactory sqlSessionfactory, ExecutorType executorType) {
		super(sqlSessionfactory, executorType);
	}

	/**
	 * 根据通过主键删除记录
	 * 
	 * @param sqlId Mybatis的XML中的SQL定义id， 命名空间 + id
	 * @param id 主键
	 */
	public <PK> void deleteById(String sqlId, PK id) {
		try {
			delete(sqlId, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过主键集合删除记录
	 * 
	 * @param sqlId Mybatis的XML中的SQL定义id， 命名空间 + id
	 * @param ids 主键集合 直接把ids传入,后端XML语句里面用in操作,delete(sqlId, ids), XML文件中用foreach处理
	 */
	public <PK> void deleteByIds(String sqlId, List<PK> ids) {
		try {
			delete(sqlId, ids);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过主键数组删除记录
	 * 
	 * @param sqlId Mybatis的XML中的SQL定义id， 命名空间 + id
	 * @param ids 主键数组
	 */
	public <PK> void deleteByIds(String sqlId, PK[] ids) {
		List<PK> listIds = Arrays.asList(ids);
		deleteByIds(sqlId, listIds);
	}

	/**
	 * 通过主键获取一条记录
	 * 
	 * @param sqlId Mybatis的XML中的SQL定义id， 命名空间 + id
	 * @param id 主键
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T, PK> T getById(String sqlId, PK id) {
		try {
			return (T)selectOne(sqlId, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过查询条件获取一条记录
	 * 
	 * @param sqlId Mybatis的XML中的SQL定义id， 命名空间 + id
	 * @param param 封装条件
	 */
	@SuppressWarnings("unchecked")
	public <T> T getByParam(String sqlId, Object param) {
		try {
			return (T)selectOne(sqlId, param);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 分页时获取总数
	 * 
	 * @param sqlId Mybatis的XML中的SQL定义id， 命名空间 + id
	 * @param param 封装条件
	 */
	public long getCount(String sqlId, Object param) {
		try {
			return (Long)selectOne(sqlId, param);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取记录列表
	 * 
	 * @param sqlId Mybatis的XML中的SQL定义id， 命名空间 + id
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> query(String sqlId) {
		try {
			return (List<T>)selectList(sqlId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过条件获取记录列表
	 * 
	 * @param sqlId Mybatis的XML中的SQL定义id， 命名空间 + id
	 * @param param 封装条件
	 */
	public <T> List<T> query(String sqlId, Object param) {
		return query(sqlId, param, 0, 0);
	}

	/**
	 * 通过条件获取分页记录
	 * 
	 * @param sqlId Mybatis的XML中的SQL定义id， 命名空间 + id
	 * @param param 封装条件
	 * @param skip 第多少条
	 * @param limit 每页显示多少条数
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> query(String sqlId, Object param, int skip, int limit) {
		try {
			if (limit == 0) {
				return (List<T>)selectList(sqlId, param);
			}
			return (List<T>)selectList(sqlId, param, new RowBounds(skip, limit));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 通过分页条件获取Page
	 * 
	 * @param sqlListId 获取集合数据的的SQL语句
	 * @param sqlCountId 获取总数的SQL语句
	 * @param param 查询条件
	 * @param page 分页参数封装, 只用传递第几页和每页条数, 如第一页,则其pageNo为1, 第二页则为2
	 */
	public <T> Page<T> queryPage(String sqlListId, String sqlCountId, Object param, Page<T> page) {
		try {
			if (page == null) {
				return page;
			}
			int skip = (page.getPageNo() - 1) * page.getPageSize();
			int limit = page.getPageSize();
			List<T> results = query(sqlListId, param, skip, limit);
			long count = getCount(sqlCountId, param);
			page.setResult(results);
			page.setTotalCount(count);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return page;
	}

	/**
	 * 通过分页条件获取Page
	 * 
	 * @param sqlListId 获取集合数据的的SQL语句
	 * @param sqlCountId 获取总数的SQL语句
	 * @param param 查询条件
	 * @param pageNo 第几页, 如果是第一页, 则值为1, 如果是第二页, 则值为2
	 * @param pageSize 每页显示多少条数
	 */
	public <T> Page<T> queryPage(String sqlListId, String sqlCountId, Object param, int pageNo, int pageSize) {
		Page<T> page = new Page<T>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return queryPage(sqlListId, sqlCountId, param, page);
	}

}
