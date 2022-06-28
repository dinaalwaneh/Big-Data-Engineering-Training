
	$(document).on("click", ".add-new1", function(){
	
         //  var businessNatureId= $("#customersName option:selected").val();

               $.ajax({
                  type : "GET",
                  url :"http://localhost:8082/get/customers",
                  headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
                
                  success : function(result) {
	
	         $.each(result , function (key, value) {
                                $('#customersName').append($('<option>',
                                    {
                                    value: value.customerName,
                                    text: value.customerName
                                }));
                                });
                                
				 }
 			
			,
                  error : function(data) {
	 
	 alert("noooo");
                  }
               });
			 
		 		
			 
		
			
    });
