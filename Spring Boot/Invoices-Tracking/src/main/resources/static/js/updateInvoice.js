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
				 
				   var employeeModel =  
				{
					 id: $("#d0").val() ,
					 number:$("#d1").val() ,
					 userName: $("#d2").val() ,
					 customerName: $("#d3").val() ,
			         dateOfCreate: $("#d5").val() ,
			         totalAmount: $("#d6").val() ,
			         totalPaid:$("#d7").val()  ,
			         remainingAmount: $("#d6").val()-$("#d7").val() ,
			         status:  $("#d9").val() ,
			         isDeleated: $("#d10").val() ,
			       
			       
			    } 
		  
		  if($("#d6").val()-$("#d7").val()==0){
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
						
					addUpdatesToHistory(result)
						
					},
					error : function(e) {
						
						console.log("ERROR: ", e);
					}
				});
			}
			
			 function addUpdatesToHistory(updatedData){
		
		var InvoiceHistory ={
			
			invoiceId : updatedData.id,
			userName : sessionStorage.getItem("username"),
			updatedData : "id = " + updatedData.id 
			+ ", customerName = "+ updatedData.customerName + ", userName = "+ updatedData.userName
			+ ", dateOfCreate = "+ updatedData.dateOfCreate
			+ ", dateOfUpdate = "+updatedData.updatedData + ", number = "+ updatedData.number
			+ ", totalAmount = "+ updatedData.totalAmount + ", totalPaid = "+ updatedData.totalPaid
			+ ", remainingAmount = "+ updatedData.remainingAmount + ", status = "+ updatedData.status
			
		};
			 var requestJSON = JSON.stringify(InvoiceHistory);
    console.log("ddfffffffffff "+InvoiceHistory.updatedData);
            $.ajax({
               type : "POST",
               url : "http://localhost:8082/add/invoicehistory",
               headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
               data : requestJSON,
               success : function(result) {
             
                    
     			location.reload();    
                    
                  
               },
               error : function(e) {
                  $("#getResultDiv").html("<strong>Error</strong>");
                  console.log("ERROR: ", e);
               }
            });
         
	}
		})