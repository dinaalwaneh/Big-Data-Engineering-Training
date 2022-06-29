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
	          
 			
				
								
								
								 
								
								
								
                      
                        
 				var items = $("table td:last-child").html();
                  for (var i=0; i<result.length; i++) {
	 var d = ' <td  >'+' <a href="#editEmployeeModal'  + i + '" class="edit" data-toggle="modal"><i class="material-icons" style="color:black;">&#xE03B;</i></a>'
                       
                        +'<a class="edit1" title="Edit1" data-toggle="tooltip"><i class="material-icons" style="color:#f9d306;">&#xE254;</i></a>'
                       +'</td>';
	var h='   <td >'+
                           '<!-- Edit Modal HTML -->'  +
							'<div   id=  "editEmployeeModal'  + i + '"  class="modal fade">'	
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
								        +'<button type="button" class="btn btn-info add-new112"><i class="fa fa-plus"></i> Add New</button>'
								                     +'  </div>'             
								           +' </div>'    
								         +' </div>'  
								             
								      +'  <table id="table-content" class="table table-bordered itemTable">'+'<thead>'     
								                 +'<tr>'   
								                       
								                   +' <th>ItemName</th>'    
								                     +'   <th>Quantity</th>'
								                      +' <th>Total Amount</th>' 
								                       +'<th>Actions</th>'  
								               +'  </tr>'   
								               +'</thead>' 
								              +' <tbody><tr><td hidden=true><a class="add11" title="Add11" data-toggle="tooltip"><i class="material-icons" style="color:black;">&#xE03B;</i></a>' 
								               +' <a class="edit1" title="Edit1" data-toggle="tooltip"><i class="material-icons" style="color:#f9d306;">&#xE254;</i></a>'+'</td>'       
								                        
															+'</td> </tr></tbody> </table><div class="table-title"><div id="employeesJson"></div>'
								                   
								                +'<div class="row"><div class="col-sm-4" style="float: right;"><button type="button" class="btn btn-info save">Save Changed</button>'
								           
								              +' </div> </div> </div></div></div><div class="modal-footer"><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">'
								            
								        
								
								 +'<input type="submit" class="btn btn-info hhh" value="Save"></div></div></div></div> </td> </tr>'
								
								
								
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
                       
                           d + '<td>' + h + '</td>'
                         
                         
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