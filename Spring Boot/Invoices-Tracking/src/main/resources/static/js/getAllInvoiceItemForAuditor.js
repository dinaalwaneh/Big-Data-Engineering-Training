$(document).on("click", ".edit", function(){
            
 
    var j = $(this).parents("tr").find("td:nth-child(1)").text().replace(' Swish Walletsanselk Walletnoia Walletmilk WalletSwish Walletsanselk Walletnoia Walletmilk Wallet','');
 j=j.replace('Swish Walletsanselk Walletnoia Walletmilk WalletSwish Walletsanselk Walletnoia Walletmilk Wallet','');
j=j.replace(/[A-Z]/g,'');
j=j.replace(' ','');
j=j.replace(' ','');
console.log("kkkkkkget items to invoice "+j);

// PREPARE FORM DATA
			    $.ajax({	 
					
               type : "GET",
               contentType : "application/json",
               url : "http://localhost:8082/get/invoiceitem/"+j,
               dataType : 'json',
               headers: {
               			Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
    		    },
    		   async: true,  
               success : function(result) {
	           var totalAmount = 0;
 				 
                  for (var i=0; i<result.length; i++) {
                     var row = $('<tr><td id="Id">' + result[i].itemName + '</td><td id="name">' +result[i].quantity
                     + '</td><td name="userName" id="userName">' + result[i].amount +  '</td>'
                      +'</tr>');
                 
                 	totalAmount  = totalAmount+result[i].amount;
                 	console.log("ijijij");
                    $('.itemTable'+(j)).append(row);
                }
                                    
                     console.log("Success: ", totalAmount);
                      

               },
               error : function(e) {
                   console.log("jjj: ", e);
               }
            });

			
			 
});
 