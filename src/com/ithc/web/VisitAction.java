package com.ithc.web;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ithc.bean.Visit;
import com.ithc.service.VisitService;
import com.ithc.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class VisitAction extends ActionSupport implements ModelDriven<Visit>{

	private Visit visit = new Visit();
	public Visit getModel() {
		return visit;
	}
	
	private VisitService visitService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	
	//点击跳转添加客户拜访
	public String initAddUI(){
		
		return "initAddUI";
	}
	
	//保存
	
	public String save(){
		
		visitService.save(visit);
		
		return initAddUI();
	}
	
	//分页查询
	//当前页
	private Integer pageCode = 1;
	//每页显示的数量
	private Integer pageSize = 2;
	
	//筛选时间
	private String beginDate;
	private String endDate;
	
	
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		//拼接筛选条件
		//名称
		try {
			if(visit != null){
				String visit_interviewee = visit.getVisit_interviewee();
				if(visit_interviewee != null && !visit_interviewee.equals("")){
					criteria.add(Restrictions.like("visit_interviewee","%"+visit_interviewee+"%"));
				}
			}
			
		} catch (Exception e) {
		}
		
		try {
			
			if(beginDate != null && !beginDate.equals("")){
				criteria.add(Restrictions.ge("visit_time", beginDate));
			}
		} catch (Exception e) {
		}
		
		try {
			if(endDate != null && !endDate.equals("")){
				criteria.add(Restrictions.le("visit_time", endDate));
			}
		} catch (Exception e) {
		}
		
		PageBean<Visit> page = visitService.findByPage(pageCode,pageSize,criteria);
		//压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page", page);
		return "findByPage";
	}

	
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setPageCode(Integer pageCode) {
		if(pageCode == null){
			pageCode = 1;
		}
		
		this.pageCode = pageCode;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			pageSize = 2;
		}
		this.pageSize = pageSize;
	}
}