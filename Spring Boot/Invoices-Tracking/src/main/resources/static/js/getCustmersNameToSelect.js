
	$(document).on("click", ".add-new", function(){
	
         //  var businessNatureId= $("#customersName option:selected").val();

               $.ajax({
                  type : "GET",
                  url :"http://localhost:8082/get/items",
                  headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
                
                  success : function(result) {
	
	         $.each(result , function (key, value) {
                                $('#customersName').append($('<option>',
                                    {
                                    value: value.name,
                                    text: value.name
                                }));
                                });
                                
				 }
 			
			,
                  error : function(data) {
	 
	 alert("noooo");
                  }
               });
			 
		 		
			 
		
			
    });
