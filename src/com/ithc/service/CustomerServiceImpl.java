package com.ithc.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ithc.bean.Customer;
import com.ithc.dao.CustomerDao;
import com.ithc.util.PageBean;
@Transactional
public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	//添加
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	//分页查询
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		return customerDao.findByPage(pageCode, pageSize,criteria);
	}
	//删除
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	//id查询
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	//修改
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	//查询所有
	public List<Customer> findByAll() {
		
		return customerDao.findByAll();
	}

	//客户级别统计
	public List<Object[]> findByLevel() {
		return customerDao.findByLevel();
	}

	//客户来源统计
	public List<Object[]> findBySource() {
		
		return customerDao.findBySource();
	}

	//客户行业统计
	public List<Object[]> findByindustry() {
		
		return customerDao.findByindustry();
	}
	
}
