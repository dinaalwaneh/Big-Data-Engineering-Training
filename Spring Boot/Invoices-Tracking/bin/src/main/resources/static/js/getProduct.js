$(document).ready(function(){
	

			

				// PREPARE FORM DATA
			    $.ajax({	 
					
               type : "GET",
               contentType : "application/json",
               url : "http://localhost:8082/items",
               dataType : 'json',
                headers: {
        Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
    },  async: true,  
               success : function(result) {
	          
 				var actions = $("table td:last-child").html();
                  for (var i=0; i<result.length; i++) {
                     var row = $('<tr><td id="Id">' + result[i].id + '</td><td id="name">' +result[i].name
                     + '</td><td name="ammount" id="ammount">' + result[i].ammount +  '</td>'+'<td name="description" id="description">' + result[i].discription +  '</td>'+'<td id="isDeleated">' + result[i].deleated +  '</td>'+'<td>' + actions + '</td>' +'</tr>');
                
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