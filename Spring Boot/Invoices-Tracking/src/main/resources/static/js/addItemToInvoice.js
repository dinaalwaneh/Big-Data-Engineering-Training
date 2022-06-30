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
    
    	$(document).on("click", ".add11", function(){
  
   var employel =  
				{
					 id: $(this).parents("tr").find("td:first-child").text()[0],
			        dateOfCreate:$(this).parents("tr").find("td:nth-child(2)").text() ,
			        dateOfUpdate:$(this).parents("tr").find("td:nth-child(3)").text(),
			        number:$(this).parents("tr").find("td:nth-child(4)").text() ,
			        totalAmount: $(this).parents("tr").find("td:nth-child(5)").text() ,
			        totalPaid:$(this).parents("tr").find("td:nth-child(6)").text()  ,
			        remainingAmount:$(this).parents("tr").find("td:nth-child(7)").text() ,
			        status: $(this).parents("tr").find("td:nth-child(8)").text() ,
			        isDeleated: $(this).parents("tr").find("td:nth-child(9)").text(),
			        customerName:$(this).parents("tr").find("td:nth-child(10)").text() ,
			        userName: $(this).parents("tr").find("td:nth-child(11)").text() ,
			    } 
			var empl =  {
         invoiceId: $(this).parents("tr").find("td:first-child").text()[0],
         itemName: $("#ItemName").val(),
         quantity: $("#quan").val()
  }
  console.log("khhhhhhh "+empl.invoiceId);
               console.log(empl.itemName);
               console.log(empl.quantity);
               var requestJSON = JSON.stringify(empl);
               $.ajax({
                  type : "POST",
                  url : "http://localhost:8082/add/invoiceitem",
                  headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
                  data : requestJSON,
                  success : function(data) {
	
                  		console.log("invoicenumber "+empl.invoiceId);
                  		
                  	    totalAmount= getTotalAmmount(empl.invoiceId,employel);
                  	    console.log("totalAmmount == "+totalAmount);
                  	   
                  		 
                   },
                  error : function(data) {
	 
	 alert("noooo");
                  }
               });
			 
			
		
		
		 		function getTotalAmmount(o,employeeMode){
			console.log("employeeModeiiiiiiiiiiiiiiiiiiiiiiiii "+employeeMode.id);
					 $.ajax({	 
					
               type : "GET",
               contentType : "application/json",
               url : "http://localhost:8082/get/invoiceitem/"+o,
               dataType : 'json',
               headers: {
               			Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
    		    },
    		   async: true,  
               success : function(result) {
	           var totalAmount = 0; 
                  for (var i=0; i<result.length; i++) {
                
                 	totalAmount  = totalAmount+result[i].amount;
                    
                }
                      
                     console.log("Suchhhhhss: ", totalAmount,employeeMode);
                      UpdateInvoice(totalAmount,employeeMode);
                     

               },
               error : function(e) {
                   console.log("jjj: ", e);
               }
            });
			
		      }
			 
			 function UpdateInvoice(totalAmount,employeeMode){
	
	  
console.log("employeeMjjjjjjjjjjode == ",employeeMode.id);
 ajaxGet() ;

			// DO GET
			function ajaxGet() {
		console.log("employeeMode = "+employeeMode);
		  
               var requestJSON = JSON.stringify(employeeMode);
               console.log("requestJSON"+requestJSON);
				$.ajax({
					type : "PUT",
					url : "http://localhost:8082/put/invoice/"+employeeMode,
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
			}
		
			
    });