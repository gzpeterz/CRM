package com.ithc.dao;

import java.util.List;

import com.ithc.bean.Dict;

public interface DictDao {

	List<Dict> findByCode(String code);

}
