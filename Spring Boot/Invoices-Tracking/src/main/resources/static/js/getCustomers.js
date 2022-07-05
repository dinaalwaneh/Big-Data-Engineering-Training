$(document).ready(function(){

				// PREPARE FORM DATA
			    $.ajax({	 
					
               type : "GET",
               contentType : "application/json",
               url : "http://localhost:8082/customers",
               dataType : 'json',
               headers: {
               			Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
    		    },
    		   async: true,  
               success : function(result) {
	          
 				var actions = $("table td:last-child").html();
                  for (var i=0; i<result.length; i++) {
                     var row = $('<tr><td id="Id">' + result[i].id 
                     +' <td name="customerName" id="customerName">' + result[i].customerName +  '</td>'
                     +'<td name="address" id="address">' + result[i].address +
                       '</td>'+'<td name="phone" id="phone">' + result[i].phone +  '</td>' +
                       '<td name="email" id="email">' + result[i].email +
                       '<td name="enabled" id="enabled">' + result[i].enabled +  '</td>' +
                         '</td>'+'<td>' + actions + '</td>' +'</tr>');
                
                    $('#table-content').append(row);
                }
                                    
                     console.log("Success: ", result);
                      

               },
               error : function(e) {
                   console.log("jjj: ", e);
               }
            });

			
}
		)