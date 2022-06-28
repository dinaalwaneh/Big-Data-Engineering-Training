$(document).ready(function(){

				// PREPARE FORM DATA
			    $.ajax({	 
					
               type : "GET",
               contentType : "application/json",
               url : "http://localhost:8082/get/invoices",
               dataType : 'json',
               headers: {
               			Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
    		    },
    		   async: true,  
               success : function(result) {
	          
 				var actions = $("table td:last-child").html();
                  for (var i=0; i<result.length; i++) {
                     var row = $('<tr><td id="Id">' + result[i].id +
                      '</td><td id="dateOfCreate">' +result[i].dateOfCreate
                     + '</td><td name="dateOfUpdate" id="dateOfUpdate">' + result[i].dateOfUpdate +
                       '</td>'+'<td name="number" id="number">' + result[i].number +
                       '</td>'+'<td name="totalAmount" id="totalAmount">' + result[i].totalAmount +  '</td>' +
                       '<td name="totalPaid" id="totalPaid">' + result[i].totalPaid +
                       '<td name="remainingAmount" id="remainingAmount">' + result[i].remainingAmount +  '</td>' +
                      '<td name="status" id="status">' + result[i].status +  '</td>' +
                       '<td name="isDeleated" id="isDeleated">' + result[i].isDeleated +  '</td>' +
                       '<td name="customerName" id="customerName">' + result[i].customerName +  '</td>' +
                       '<td name="userName" id="userName">' + result[i].userName +  '</td>' +
                        
                         '</td>'+'<td>' + actions + '</td>' +'</tr>');
                
                    $('#table-content').append(row);
                }
                                    
                     console.log("Success: ", result);
                                       			      		 var currentdate = new Date(); 
    var datetime = "Now: " + currentdate.getFullYear() + "-"
                + (currentdate.getMonth()+1)  + " " 
              
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() + ":" 
                + currentdate.getSeconds()

console.log(datetime.toString());

               },
               error : function(e) {
                   console.log("jjj: ", e);
               }
            });

			
}
		)