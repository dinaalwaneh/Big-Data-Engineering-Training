$(document).on("click", ".showInvoiceHistory", function(){
 
	
	var invoiceId = $(this).parents("tr").find('td:first-child').text();
	
	invoiceId=invoiceId.replace(/[A-Z]/g,'');
	invoiceId=invoiceId.replace(/[a-z]/g,'');
	invoiceId=invoiceId.replace(' ','');
	invoiceId=invoiceId.replace(' ','');

	
	  $.ajax({	 
					
               type : "GET",
               contentType : "application/json",
               url : "http://localhost:8082/get/invoicehistory/"+invoiceId,
               dataType : 'json',
               headers: {
               			Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
    		    },
    		   async: true,  
               success : function(result) {
	localStorage.setItem('invoiceId' , invoiceId);

	          window.location.href = "http://localhost:8082/sUInvoicesHistory";
 		 
                                    
                     console.log("Success: ", result);
                     
                      
               },
               error : function(e) {
                   console.log("jjj: ", e);
               }
            });
            
            
            

	});

