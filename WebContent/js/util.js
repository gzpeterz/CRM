	function to_page(page){
		
		//获取输入跳转页面框的值
		var pageValue = $("#page").val();
		var pvalue = parseInt(pageValue);
		if(pvalue < 1){
			if(pageValue > 1){
				
			}
			$("#page").val(1);
			document.customerForm.submit();
			return;
		}
		var totalPages = $("#totals").val();
		var tpage = parseInt(totalPages);
		if(pvalue > tpage){
			$("#page").val(tpage);
			document.customerForm.submit();
			return;
		}
		
		if(page){
			$("#page").val(page);
		}
		document.customerForm.submit();
	}
	
	function checkPage(){
			//获取输入跳转页面框的值
			var pageValue = $("#page").val();
			var pvalue = parseInt(pageValue);
			if(pvalue < 1){
				$("#page").val(1);
			}
			var totalPages = $("#totals").val();
			var tpage = parseInt(totalPages);
			if(pvalue > tpage){
				$("#page").val(tpage);
			}
	} 
	
	
	function checkForm(){
		checkPage(); 
		return true;
	}
	