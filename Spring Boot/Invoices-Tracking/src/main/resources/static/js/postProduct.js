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
    
    	$(document).on("click", ".add1", function(){
	
		var empty = false;
		var input = $(this).parents("tr").find('input[type="text"]');
		var input2 = $(this).parents("tr").find('input[type="number"]');
        input.each(function(){
			if(!$(this).val()){
				$(this).addClass("error");
				empty = true;
			} else{
                $(this).removeClass("error");
            }
		});
		
		
		$(this).parents("tr").find(".error").first().focus();
		if(!empty){
		
                   var employeeModel = {
                  id :$("#Id").val() ,
                  name : $("#Name").val(),
                  discription : $("#discription").val(),
                  ammount: $("#ammo").val() ,
                  quantity:0,
                  isDeleated :$("#isDeleted").val()
               };
               
               console.log("ijijijiji "+employeeModel.ammount);
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
	 
	 alert("Product is already added");
                  }
               });
			 	
			 
		}	
			
    });