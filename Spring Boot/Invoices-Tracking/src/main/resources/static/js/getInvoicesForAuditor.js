$(document).ready(function(){

				// PREPARE FORM DATA
			    $.ajax({	 
					
               type : "GET",
               contentType : "application/json",
               url : "http://localhost:8082/paginationAndSort/0/2/dateOfCreate",
               dataType : 'json',
               headers: {
               			Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
    		    },
    		   async: true,  
               success : function(result) { 
                        
 				var items = $("table td:last-child").html();
                  for (var i=0; i<result.length; i++) {
	 var d = ' <td  >'+' <a href="#editEmployeeModal'  + result[i].id + '" class="edit" data-toggle="modal"><i class="material-icons" style="color:black;">&#xE03B;</i></a>'
                       
                       
                     +' <a href="#showPic'  + result[i].id + '" class="showPic" data-toggle="modal"><i class="fa fa-eye" aria-hidden="true"></i></a>' +
                          
                          
                          
                           '<!-- Edit Modal HTML -->'  +
							'<div   id=  "editEmployeeModal'  + result[i].id + '"  class="modal fade">'	
								+'<div class="modal-dialog">'
								+'<div class="modal-content">'
								 
							+'	<div class="modal-header">'
							+'<h4 class="modal-title">INvoice Items</h4>'	
							+'	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
							+'</div>'
							+'<div class="modal-body">'	
							+'	<div class="form-group">'
								
								     
								       
								+'  <div class="table-title">'          
								  +'<div id="employeesJson"></div>'          
								   +'   <div class="row">'          
								     +' <div class="col-sm-8"><h2>Item<b>Details</b></h2></div> '              
								      +' <div class="col-sm-4">'             
								       
								                     +'  </div>'             
								           +' </div>'    
								         +' </div>'  
								             
								      +'  <table id="table-content" class="table table-bordered itemTable' + result[i].id + '">'+'<thead>'     
								                 +'<tr>'   
								                       
								                   +' <th>ItemName</th>'    
								                     +'   <th>Quantity</th>'
								                      +' <th>Total Amount</th>' 
								               +'  </tr>'   
								               +'</thead>' 
								              +'<tbody>'+'</tbody> </table><div class="table-title"><div id="employeesJson"></div>'
								                   
								                +'<div class="row"><div class="col-sm-4" style="float: right;">'
								           
								              +' </div> </div> </div></div></div><div class="modal-footer"><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">'
								            
								        
								
								 +' </div></div></div></div>'+
                           '<!-- Edit Modal HTML -->'  +
							'<div   id=  "showPic'  + result[i].id + '"  class="modal fade">'	
								+'<div class="modal-dialog">'
								+'<div class="modal-content">'
								 
							+'	<div class="modal-header">'
							+'<h4 class="modal-title">INvoice File</h4>'	
							
							+'	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
							+'</div>'
							+'<div class="modal-body">'	
							+'	<div class="form-group">'+
								'<img style="width: 400px ; height:400px;" src="images/'+result[i].fileName +'" alt="No file uploaded" width="80">'
								     
							+	'<div class="table-title">'
								                   
								                +'<div class="row"><div class="col-sm-4" style="float: right;">'
								           
								              +' </div> </div> </div></div></div><div class="modal-footer"><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">'
								            
								        
								
								 +'</div></div></div></div>' +'</td>';

								
                     var row = $('<tr>' + 
                       '<td name="Idd" id="Idd">' + result[i].id +'</td>'+
                       '</td>'+'<td name="numbern" id="numbern">' + result[i].number +'</td>'
                       +
                       '<td name="userName" id="userName">' + result[i].userName +  '</td>'+
                       '<td name="customerName" id="customerName">' + result[i].customerName +  '</td>'+
                      '<td name="fileName" id="fileName">' + result[i].fileName  +  '</td>'
                        + '<td id="dateOfCreate">' +result[i].dateOfCreate
                     + '</td>'
                       +'<td name="totalAmount" id="totalAmount">' + result[i].totalAmount +  '</td>' +
                       '<td name="totalPaid" id="totalPaid">' + result[i].totalPaid +
                       '<td name="remainingAmount" id="remainingAmount">' + result[i].remainingAmount +  '</td>' +
                      '<td name="status" id="status">' + result[i].status +  '</td>' +
                       '<td name="isDeleated" id="isDeleated">' + result[i].isDeleated +  '</td>'   +
                       
                           d 
                         
                         
                          +'</tr>');
             
 
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