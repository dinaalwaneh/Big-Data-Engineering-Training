	/*$(document).on("click", ".add", function(){
		var empty = false;
		var input = $(this).parents("tr").find('input[type="text"]');
		var input2 = $(this).parents("tr").find('input[type="number"]');
      
      
                   var employeeModel = {
                  id :$("#Id").val() ,
                  name : $("#Name").val(),
                  ammount: $("#ammount").val() ,
                  quantity : $("#quantity").val(),
                  isDeleated :$("#isDeleted").val()
               };
               var requestJSON = JSON.stringify(employeeModel);
               $.ajax({
                  type : "POST",
                  url : "http://localhost:8082/add/item",
                  headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
                  data : requestJSON,
                  success : function(data) {
                  		location.reload();
                  },
                  error : function(data) {
	 alert("noooo");
                  }
               });
		
		$(this).parents("tr").find(".error").first().focus();
		if(!empty){
			input.each(function(){
				$(this).parent("td").html($(this).val());
				
			});	
			input2.each(function(){
				$(this).parent("td").html($(this).val());
				
			});			
			$(this).parents("tr").find(".add, .edit").toggle();
			$(".add-new").removeAttr("disabled");
		}		
    });*/
    
    	$(document).on("click", "button", function(){
	
	 
		
		 
		
			 var employeeModel =  {   
			        name: $("#name").val(),
			        userName: $("#username").val(),
			        address: $("#address").val(),
			        phone: $("#phone").val(),
			        email: $("#email").val(),
			        password: $("#password").val(),
			        enabled:  "true",
			        roleName: $("#job").val()
			    };
               
               
               var requestJSON = JSON.stringify(employeeModel);
               $.ajax({
                  type : "POST",
                  url : "http://localhost:8082/add/user",
                  headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
                  data : requestJSON,
                  success : function(data) {
	
                  		console.log("doneeeeeeeeeeee");
                  		window.location.href="/authenticate"
                  },
                  error : function(data) {
	 
	 alert("noooo");
                  }
               });
			 
		 		
			 
		
			
    });