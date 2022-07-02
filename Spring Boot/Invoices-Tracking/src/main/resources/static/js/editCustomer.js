$(document).on("click", ".edit1", function(){
	
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
			var employ  =  {  
				    id:$("#d0").val() ,
			        name: "customer",
			        customerName: $("#d1").val(),
			        address:$("#d2").val(),
			        phone: $("#d3").val(),
			        email:$("#d4").val(),
			        enabled:  "true",
			    };	 
				
		  
               var requestJSON = JSON.stringify(employ);
               console.log("requestJSON"+requestJSON);
				$.ajax({
					type : "PUT",
					url : "http://localhost:8082/update/customer/"+$("#d0").val(),
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