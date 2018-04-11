package com.ithc.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ithc.bean.Dict;
import com.ithc.service.DictService;
import com.ithc.util.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DictAction extends ActionSupport implements ModelDriven<Dict> {
	private Dict dict = new Dict();
	@Override
	public Dict getModel() {
		return dict;
	}
	
	private DictService dictService;
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	
	public String findByCode(){
		String code = dict.getDict_type_code();
		List<Dict> list = dictService.findByCode(code);
		if(list != null){
			HttpServletResponse response = ServletActionContext.getResponse();
			String jsonString = FastJsonUtil.toJSONString(list);
			FastJsonUtil.write_json(response, jsonString);
		}
		return null;
	}

}
