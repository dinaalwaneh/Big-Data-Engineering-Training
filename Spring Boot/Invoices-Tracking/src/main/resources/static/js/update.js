$(document).on("click", ".editProduct", function(){
	
              
               
	 var caretId = []; var i =0;
        $(this).parents("tr").find("td:not(:last-child)").each(function(){
	 
			 caretId[i] =  $(this).text();
	  
	
			$(this).html('<input id="d'  + i + '" name="d'  + i + '" type="text" value="'  +  $(this).text() + '" class="form-control"  >');
		 
			  i++;
		}
		);	
	  

   $(this).parents("tr").find("td:not(:last-child)").on('change',function() {
        var card = $(this).text();
        console.log(card);
    });
 
 
		 		// GET REQUEST
			 $(".save").click(function(event) {
				 
				event.preventDefault();
				ajaxGet();
			});

			// DO GET
			function ajaxGet() {
				
				
			
		       var employeeModel = {
                  id : $("#d0").val() ,
                  name :  $("#d1").val(),
                  ammount:  $("#d2").val() ,
                  discription :  $("#d3").val(),
                  quantity:0,
                  deleated : $("#d4").val()
               };
	 
               var requestJSON = JSON.stringify(employeeModel);
               console.log("requestJSON"+requestJSON);
				$.ajax({
					type : "PUT",
					url : "http://localhost:8082/item/"+$("#d0").val(),
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
		})