$(document).on("click", ".edit", function(){
            
 

var j = $(this).parents("tr").find("td:first-child").text()[0];
console.log("kkkkkk "+j);

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
 				var actions = $(".itemTable"+(j-1)+" td:last-child").html();
                  for (var i=0; i<result.length; i++) {
                     var row = $('<tr><td id="Id">' + result[i].itemName + '</td><td id="name">' +result[i].quantity
                     + '</td><td name="userName" id="userName">' + result[i].amount +  '</td>'+
                       '<td>' + actions + '</td>' +'</tr>');
                 
                 	totalAmount  = totalAmount+result[i].amount;
                 	console.log("ijijij");
                    $('.itemTable'+(j-1)).append(row);
                }
                                    
                     console.log("Success: ", totalAmount);
                      

               },
               error : function(e) {
                   console.log("jjj: ", e);
               }
            });

			
			 
});
 