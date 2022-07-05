$(document).on("click", ".show-history", function(){
	
	var invoiceId = $(this).parents("tr").find('td:first-child').text();
	
	invoiceId=invoiceId.replace(/[A-Z]/g,'');
	invoiceId=invoiceId.replace(/[a-z]/g,'');
	invoiceId=invoiceId.replace(' ','');
	invoiceId=invoiceId.replace(' ','');

	
	  $.ajax({	 
					
               type : "GET",
               contentType : "application/json",
               url : "http://localhost:8082/allinvoiceshistory",
               dataType : 'json',
               headers: {
               			Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
    		    },
    		   async: true,  
               success : function(result) {
 					
 					$('#table-content tbody').empty();	
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
            });
	
	});