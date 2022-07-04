
    
    	$(document).on("click", ".add11", function(){
	
	
			    var i = $(this).parents("tr").find("td:nth-child(1)").text().replace(' Swish Walletsanselk Walletnoia Walletmilk WalletSwish Walletsanselk Walletnoia Walletmilk Wallet','');
 i=i.replace('Swish Walletsanselk Walletnoia Walletmilk WalletSwish Walletsanselk Walletnoia Walletmilk Wallet','');


i=i.replace(/[A-Z]/g,'');
i=i.replace(/[a-z]/g,'');
i=i.replace(' ','');
i=i.replace(' ','');
console.log("ffffffffffffffffffffffff ============== "+i );
			var empl =  {
         invoiceId: i,
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
                  		
                  	    totalAmount= getTotalAmmount(empl.invoiceId);
                  	    console.log("totalAmmount == "+totalAmount);
                  	   
                  		 
                   },
                  error : function(data) {
	 
	 alert("noooo");
                  }
               });
			 
			
		
		
		 		function getTotalAmmount(o){
			 
			 o = o.replace(" ",'');
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
                      
                     console.log("Suchhhhhss: ", totalAmount);
                      UpdateInvoice(totalAmount,o);
                     

               },
               error : function(e) {
                   console.log("jjj: ", e);
               }
            });
			
		      }
			 
			 function UpdateInvoice(totalAmount,o){
	
	  getInvoiceById(o,totalAmount);
	  
	  function getInvoiceById(o,totalAmount){
		
		
    
            $.ajax({
               type : "GET",
               url : "http://localhost:8082/get/invoice/"+o,
               headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
               success : function(result) {
             
                    
       console.log("Success: ", result);
                             ajaxGet(result,totalAmount) ;       
                    
                  
               },
               error : function(e) {
                  $("#getResultDiv").html("<strong>Error</strong>");
                  console.log("ERROR: ", e);
               }
            });
         
	}


			// DO GET
			function ajaxGet(oo,totalAmount) {
		console.log("get invoice by id = "+oo);
		
		   oo.totalAmount=totalAmount;
		   oo.remainingAmount=oo.totalAmount-oo.totalPaid;
		    if(oo.totalAmount-oo.totalPaid==0){
			oo.status="paid";
		  }else{
			oo.status="not paid";
		  }
		 
               var requestJSON = JSON.stringify(oo);
               console.log("requestJSON"+requestJSON);
				$.ajax({
					type : "PUT",
					url : "http://localhost:8082/put/invoice/"+oo.id,
					headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
                  data : requestJSON,
					success : function(result) {
						
						addUpdatesToHistory(result)
					 //location.reload();
						
					},
					error : function(e) {
						
						console.log("ERROR: ", e);
					}
				});
			}
			}
			
	 function addUpdatesToHistory(updatedData){
		
		var InvoiceHistory ={
			
			invoiceId : updatedData.id,
			userName : sessionStorage.getItem("username"),
			updatedData : "id = " + updatedData.id 
			+ ", customerName = "+ updatedData.customerName + ", userName = "+ updatedData.userName
			+ ", dateOfCreate = "+ updatedData.dateOfCreate
			+ ", fileName = "+updatedData.fileName + ", number = "+ updatedData.number
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
		
			
    });