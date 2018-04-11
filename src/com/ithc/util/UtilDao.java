package com.ithc.util;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface UtilDao<T> {
	
	//增
	void save(T t);
	//删
	void delete(T t);
	//改
	void update(T t);
	//查询单个
	T findById(Long id);
	//查询所有
	List<T> findByAll();
	//分页查询
	PageBean<T> findByPage(Integer pageCode,Integer pageSize,DetachedCriteria criteria);
}
