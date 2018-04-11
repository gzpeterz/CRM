<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/frameset.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
.codeId{
	color : red;
}
</STYLE>

<META content="MSHTML 6.00.6000.16809" name=GENERATOR>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.0.min.js"></script>

<script type="text/javascript">
	<!-- 用js判断此框的值不能为空 ，并且唯一(ajax请求后台做判断) -->
	function checkCode(){
		var codeId = document.getElementById("codeId");
		var user_code = $("#user_code").val();
		if(user_code == ""){
			codeId.innerHTML = "登入名不能为空";
			codeId.style.color = '#ff0000';
		}else{
			var url = "${pageContext.request.contextPath}/user_registerName.action";
			var param = {"user_code":user_code}
			$.post(url,param,function(data){
				if(data == 0){
					codeId.innerHTML = "此登入名已存在";
					codeId.style.color = '#ff0000';
				}
			});
		}
	}
	
	//密码不能为空
	function checkPassword(){
		var user_password = $("#user_password").val();
		var u_password = document.getElementById("RequiredFieldValidator4");
		var u = trim(user_password);
		if(u == ""){
			u_password.innerHTML = "密码不能为空";
			u_password.style.color = '#ff0000';
		}
	}
	
	//去除空格
	function trim(str){ //删除左右两端的空格
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	
	//用户名不能为空
	function checkUserName(){
		var user_name = $("#user_name").val();
		var suname = document.getElementById("suname");
		//把获取的表单值去空格
		var name = trim(user_name);
		if(name == ""){
			suname.innerHTML = "用户名不能为空";
			suname.style.color = '#ff0000';
		}
	}
	
	//防止表单提交
	function checkForm(){
		//登入名
		checkCode();
		var codeId = $("#codeId").html();
		//密码
		checkPassword();
		var user_password = $("#RequiredFieldValidator4").html();
		// 用户名
		checkUserName();
		var suname = $("#suname").html();
		/* 判断是否有提示，有提示不能提交 */
		if(codeId != "" || user_password != "" || suanme != ""){
			return false;
		}
		return true;
	}
</script>


</HEAD>
<BODY>
<FORM id=form1 name=form1 action="${pageContext.request.contextPath }/user_register.action" onsubmit="return checkForm()" method=post>

<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>


<DIV>&nbsp;&nbsp; </DIV>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>
    <TD style="HEIGHT: 105px"><IMG src="images/login_1.gif" 
  border=0></TD></TR>
  <TR>
    <TD background=images/login_2.jpg height=300>
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <TR>
                <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                <TD style="HEIGHT: 28px" width=150>
                <!-- 用js判断此框的值不能为空 ，并且唯一(ajax请求后台做判断) -->
                <INPUT onblur="checkCode()" id="user_code" style="WIDTH: 130px" name="user_code"></TD>
                <TD style="HEIGHT: 28px" width=370>
                <SPAN id="codeId" style="FONT-WEIGHT: bold;"></SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px">登录密码：</TD>
                <TD style="HEIGHT: 28px"><INPUT id="user_password" onblur="checkPassword()" style="WIDTH: 130px" type="password" name="user_password"></TD>
                <TD style="HEIGHT: 28px"><SPAN id="RequiredFieldValidator4" 
                  style="FONT-WEIGHT: bold;"></SPAN></TD></TR>
              <TR>
                <TD style="HEIGHT: 28px">用 户 名：</TD>
                <TD style="HEIGHT: 28px">
                <INPUT id="user_name" style="WIDTH: 130px" name="user_name" onblur="checkUserName()"></TD>
                <TD style="HEIGHT: 28px">&nbsp;<span id="suname"></span></TD></TR>
              <TR>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD></TR>
              <TR>
                <TD></TD>
                <TD><!-- <INPUT id=btn 
                  style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
                  onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("btn", "", true, "", "", false, false))' 
                  type=image src="images/login_button.gif" name=btn> -->
                  <input type="submit" value="注册" /> 
              </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
  <TR>
    <TD><IMG src="images/login_3.jpg" 
border=0></TD></TR></TBODY></TABLE></DIV></DIV>


</FORM></BODY></HTML>
<script type="text/javascript">
	setTimeout(function(){ 
		$("#codeId").html("");
		$("#suname").html("");
		$("#RequiredFieldValidator4").html("");
	},3000)
</script>
</body>
</html>