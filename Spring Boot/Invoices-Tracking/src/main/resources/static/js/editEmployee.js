$(document).on("click", ".editEmployee", function(){
	
	 var caretId = []; var i =0;
        $(this).parents("tr").find("td:not(:last-child)").each(function(){
	 
			 caretId[i] =  $(this).text();
	  
	
			$(this).html('<input id="d'  + i + '" name="d'  + i + '" type="text" value="'  +  $(this).text() + '" class="form-control"  >');
		 
			  i++;
		}
		);	
	
	        $(this).parents("tr").find("td:nth-child(4)").each(function(){
	
			  
	   
	
			$(this).html( ' <select id="d'  + 3 + '" name="d'  + 3 + '" style=" border: 1px solid black; border-radius: 4px; background-color: white;" id="job" name="user_job">'+
	            '<option value="AUDITORUSER">AUDITOR USER</option>'+
	         '   <option value="SUPERUSER">SUPER USER</option>'+
	              ' <option value="SUPPORTUSER">SUPPORT USER</option>'+
	         
	       '</select>');
		caretId[3] =  $(this).val();
			 
		}
		);
	 $(".save").click(function(event) {
				 
				event.preventDefault();
				ajaxGet();
			});

			// DO GET
			function ajaxGet() {
				 
				   var employeeModel =  
				{
					 id: $("#d0").val() ,
					 name:$("#d1").val() ,
					 userName: $("#d2").val() ,
					 roleName:  $("#d3").val() ,
					 address: $("#d4").val() ,
			         phone: $("#d5").val() ,
			         email: $("#d6").val() ,
			         enabled:$("#d7").val()  
			        
			       
			       
	 }
		  getUserById(employeeModel);
              
			}
			
	function getUserById(totalAmount){
		console.log(totalAmount.id);
		
    
            $.ajax({
               type : "GET",
               url : "http://localhost:8082/userr/"+totalAmount.id,
               headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
               success : function(result) {
            totalAmount.password =  result.password;
                    console.log("hhhhh "+totalAmount);
     
                            ajaxGett(totalAmount) ;       
                    
                  
               },
               error : function(e) {
                  $("#getResultDiv").html("<strong>Error</strong>");
                  console.log("ERROR: ", e);
               }
            });
         
	}
	
	function ajaxGett(employeeModl) {
				 
	 console.log("iiji "+employeeModl);
		  
               var requestJSON = JSON.stringify(employeeModl);
               console.log("requestJSON"+requestJSON);
				$.ajax({
					type : "PUT",
					url : "http://localhost:8082/user/"+employeeModl.id,
					headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
                  data : requestJSON,
					success : function(result) {
						
					location.reload();
						
					},
					error : function(e) {
						
						console.log("ERROR: ", e);
					}
				});
			}
	});