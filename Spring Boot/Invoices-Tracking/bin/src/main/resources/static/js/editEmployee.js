$(document).on("click", ".editEmployee", function(){
	
	 var caretId = []; var i =0;
        $(this).parents("tr").find("td:not(:last-child)").each(function(){
	 
			 caretId[i] =  $(this).text();
	  
	
			$(this).html('<input id="d'  + i + '" name="d'  + i + '" type="text" value="'  +  $(this).text() + '" class="form-control"  >');
		 
			  i++;
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
		  
               var requestJSON = JSON.stringify(employeeModel);
               console.log("requestJSON"+requestJSON);
				$.ajax({
					type : "PUT",
					url : "http://localhost:8082/user/"+$("#d0").val(),
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