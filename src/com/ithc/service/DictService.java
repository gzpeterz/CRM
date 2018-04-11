package com.ithc.service;

import java.util.List;

import com.ithc.bean.Dict;

public interface DictService {
	//查询客户级别与信息来源
	List<Dict> findByCode(String code);

}
