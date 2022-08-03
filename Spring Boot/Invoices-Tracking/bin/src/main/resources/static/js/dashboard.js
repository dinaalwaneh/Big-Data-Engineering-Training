$(document).ready(function(){
	

			

				// PREPARE FORM DATA
			    $.ajax({	 
					
               type : "GET",
               contentType : "application/json",
               url : "http://localhost:8082/customers",
               dataType : 'json',
                headers: {
        Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
    },  async: true,  
               success : function(result) {
	          
 
                   console.log(sessionStorage.getItem("username"));
 
 var actions = $("table td:last-child").html();
                  for (var i=0; i<result.length; i++) {
                     var row = $('<tr><td>' + result[i].bookId + '</td><td>' +result[i].bookName
                     + '</td><td>' + result[i].author +  '</td>'+'<td>' + actions + '</td>' +'</tr>');
                
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