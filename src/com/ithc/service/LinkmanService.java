package com.ithc.service;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.bean.Linkman;
import com.ithc.util.PageBean;

public interface LinkmanService {
	//添加
	void save(Linkman linkman);
	//分页查询
	PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
	//用id查询
	Linkman findById(Long id);
	//删除
	void delete(Linkman linkman);
	//修改
	void update(Linkman linkman);

}
