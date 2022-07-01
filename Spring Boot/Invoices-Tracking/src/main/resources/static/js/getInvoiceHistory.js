$(document).ready(function(){


var invoiceId = localStorage.getItem("invoiceId");

console.log("idddd = "+invoiceId);
				// PREPARE FORM DATA
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
	          
 				 
                  for (var i=0; i<result.length; i++) {
                     var row = $('<tr>'
                     +'<td id="Id">' + result[i].id + '</td>'
                     +'<td name="userName" id="userName">' + result[i].userName + '</td>'
                     +'<td name="invoiceId" id="invoiceId">' + result[i].invoiceId +'</td>'
                     +'<td name="updatedData" id="updatedData">' + result[i].updatedData +  '</td>'
                     +'<td name="dateOfUpdate" id="dateOfUpdate">' + result[i].dateOfUpdate +'</td>'
                     +'</tr>');
                
                    $('#table-content').append(row);
                }
                                    
                     console.log("Success: ", result);
                      

               },
               error : function(e) {
                   console.log("jjj: ", e);
               }
            })

			
}
		)