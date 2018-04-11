package com.ithc.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.ithc.bean.Customer;
import com.ithc.service.CustomerService;
import com.ithc.util.FastJsonUtil;
import com.ithc.util.PageBean;
import com.ithc.util.UUIDNAME;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	private static final String ActioNC = null;
	private Customer customer = new Customer();

	@Override
	public Customer getModel() {

		return customer;
	}

	private CustomerService customerService;

	// 点击新增客户跳转
	public String initAddUI() {

		return "initAddUI";
	}

	// 文件上传
	// 文件
	private File upload;
	// 文件名
	private String uploadFileName;
	// 文件类型
	private String uploadContentType;

	// 添加
	public String save() throws Exception {
		if (upload != null) {
			// 名字唯一
			String uuidname = UUIDNAME.getUUIDNAME(uploadFileName);
			// 文件保存本地，名字保存数据库
			String files = "F:/apache-tomcat-7.0.70/webapps/fileUpload/" + uuidname;
			File file = new File(files);
			FileUtils.copyFile(upload, file);

			customer.setFilePath(files);
		}

		customerService.save(customer);

		return "initAddUI";
	}

	// 分页查询
	// 利用属性驱动获取当前页
	private Integer pageCode = 1;

	public void setPageCode(Integer pageCode) {

		if (pageCode == null) {
			pageCode = 1;
		}
		// 判断当前页
		if (pageCode < 1) {
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}

	// 每页显示的数量
	private Integer pageSize = 2;

	public void setPageSize(Integer pageSize) {

		if (pageSize == null) {
			pageSize = 2;
		}
		this.pageSize = pageSize;
	}

	public String findByPage() {
		// 用离线条件查询对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// 筛选条件
		if (customer != null) {
			try {
				// 拼接客户名称
				String cust_name = customer.getCust_name();
				if (!cust_name.equals("")) {
					criteria.add(Restrictions.like("cust_name", "%" + cust_name + "%"));
				}

			} catch (Exception e) {
			}
			try {
				// 拼接客户级别
				String id = customer.getLevel().getDict_id();
				System.out.println(id);
				if (!id .equals("")) {
					criteria.add(Restrictions.eq("level.dict_id",id));
				}
			} catch (Exception e) {
			}
			try {
				// 拼接客户级别
				String id = customer.getSource().getDict_id();
				System.out.println(id);
				if (!id .equals("")) {
					criteria.add(Restrictions.eq("source.dict_id",id));
				}
			} catch (Exception e) {
			}

		}

		PageBean<Customer> page = customerService.findByPage(pageCode, pageSize, criteria);

		// 压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page", page);

		return "findByPage";
	}
	
	//删除
	public String delete(){
		//先查询
		Customer customer = customerService.findById(this.customer.getCust_id());
		if(customer != null){
			customerService.delete(customer);
			return findByPage();
		}
		return "login";
	}
	
	//修改跳转
	public String initUpdate(){
		
		customer = customerService.findById(this.customer.getCust_id());
		
		return "initUpdate";
	}
	
	//修改
	public String update() throws Exception{
		
		System.out.println(customer);
		//这里的上传是重新上传
		/*1.重新上传
		 	删除以前上传的文件，重新给filePath赋值
		 * */
		
		if(uploadFileName != null){
			//删除之前的文件（用路径删除）。求出这个路径？
			String filePath = customer.getFilePath();
			//删除之前的文件
			File file = new File(filePath);
			file.delete();
			
			//添加重新上传的文件
			String uuidname = UUIDNAME.getUUIDNAME(uploadFileName);
			String files = "F:/apache-tomcat-7.0.70/webapps/fileUpload/" + uuidname;
			File file2 = new File(files);
			FileUtils.copyFile(upload, file2);
			customer.setFilePath(files);
		}
		
		/*2.没有重新上传
			filePath这个属性值不变
		*/
		customerService.update(customer);
		return null;
	}
	
	//查询所有
	public String findByAll(){
		List<Customer> list = customerService.findByAll();
		if(list != null){
			String jsonString = FastJsonUtil.toJSONString(list);
			HttpServletResponse response = ServletActionContext.getResponse();
			FastJsonUtil.write_json(response, jsonString);
		}
		return null;
	}
	
	//客户级别
	public String findByLevel(){
		List<Object[]> list = customerService.findByLevel();
		if(list != null){
			//压栈
			ValueStack vs = ActionContext.getContext().getValueStack();
			vs.set("list", list);
			return "findByLevel";
		}
		return "login";
	}
	
	//客户来源统计
	public String findBySource(){
		
		List<Object[]> list = customerService.findBySource();
		if(list != null){
			//压栈
			ValueStack vs = ActionContext.getContext().getValueStack();
			vs.set("list", list);
			return "findBySource";
		}
		
		return "login";
	}
	
	//客户行业统计
	public String findByindustry(){
		
		List<Object[]> list = customerService.findByindustry();
		if(list != null){
			//压栈
			ValueStack vs = ActionContext.getContext().getValueStack();
			vs.set("list", list);
			return "findByindustry";
		}
		return "login";
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
