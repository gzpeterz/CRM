package com.ithc.service;

import java.util.List;

import com.ithc.bean.Dict;
import com.ithc.dao.DictDao;

public class DictServiceImpl implements DictService{

	private DictDao dictDao;

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}

	@Override
	public List<Dict> findByCode(String code) {
		
		return dictDao.findByCode(code);
	}
	
}
