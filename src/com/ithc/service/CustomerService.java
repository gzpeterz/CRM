package com.ithc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ithc.bean.Customer;
import com.ithc.util.PageBean;

public interface CustomerService {
	//添加
	void save(Customer customer);
	
	//分页查询
	PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
	//删除
	void delete(Customer customer);
	//id查询
	Customer findById(Long cust_id);
	//修改
	void update(Customer customer);
	//查询所有
	List<Customer> findByAll();
	//客户级别统计
	List<Object[]> findByLevel();
	//客户来源统计
	List<Object[]> findBySource();
	//客户行业统计
	List<Object[]> findByindustry();

}
