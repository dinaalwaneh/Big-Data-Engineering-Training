$(document).on("click", ".edit1", function(){
	
              
               
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
				 var currentdate = new Date(); 
    var datetime = currentdate.getFullYear() + "-"
                + (currentdate.getMonth()+1)  + " " 
              
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() + ":" 
                + currentdate.getSeconds()
				   var employeeModel =  
				{
					 id: $("#d0").val() ,
			        dateOfCreate: $("#d1").val() ,
			        dateOfUpdate:datetime.toString() ,
			        number:$("#d3").val() ,
			        totalAmount: $("#d4").val() ,
			        totalPaid:$("#d5").val()  ,
			        remainingAmount: $("#d4").val()-$("#d5").val() ,
			        status:  $("#d7").val() ,
			        isDeleated: $("#d8").val() ,
			        customerName: $("#d9").val() ,
			        userName: $("#d10").val() ,
			    } 
		  
		  if($("#d4").val()-$("#d5").val()==0){
			employeeModel.status="paid";
		  }else{
			employeeModel.status="not paid";
		  }
		  
               var requestJSON = JSON.stringify(employeeModel);
               console.log("requestJSON"+requestJSON);
				$.ajax({
					type : "PUT",
					url : "http://localhost:8082/put/invoice/"+$("#d0").val(),
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