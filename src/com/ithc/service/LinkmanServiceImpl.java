package com.ithc.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.ithc.bean.Linkman;
import com.ithc.dao.Linkmandao;
import com.ithc.util.PageBean;

@Transactional
public class LinkmanServiceImpl implements LinkmanService{

	private Linkmandao linkmanDao;

	public void setLinkmanDao(Linkmandao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}

	//添加
	public void save(Linkman linkman) {
		linkmanDao.save(linkman);
	}

	//分页查询
	public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		return linkmanDao.findByPage(pageCode,pageSize,criteria);
	}
	//用id查询
	public Linkman findById(Long id) {
		
		return linkmanDao.findById(id);
	}

	//删除
	public void delete(Linkman linkman) {
		linkmanDao.delete(linkman);
	}

	//修改
	public void update(Linkman linkman) {
		linkmanDao.update(linkman);
	}
	
	
	
}
