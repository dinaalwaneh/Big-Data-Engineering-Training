$(document).on("click", ".page-item", function(){
	
	 
	var caretId = []; var i =0;
        $(this).parents("ul").find("li").each(function(){
	 
			$(this).removeClass( "active" );
		});
		 
	 $(this).addClass( "active" );
	 
	 if($(this).text()==1){
		 $(this).parents("ul").find("li:nth-child(1)").addClass( "disabled" );
	}else{
		 $(this).parents("ul").find("li:nth-child(1)").removeClass( "disabled" );
	}
	if($(this).text()==5){
		 $(this).parents("ul").find("li:last-child").addClass( "disabled" );
	}else{
		 $(this).parents("ul").find("li:last-child").removeClass( "disabled" );
	}
	
					// PREPARE FORM DATA
			    $.ajax({	 
					
               type : "GET",
               contentType : "application/json",
               url : "http://localhost:8082/paginationAndSort/"+($(this).text()-1)+"/2/dateOfCreate",
               dataType : 'json',
               headers: {
               			Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
    		    },
    		   async: true,  
               success : function(result) {
	          
 			
				
								
								
								 
								
								
							$('#table-content tbody').empty();	
                      
                        
 				var items = $("table td:last-child").html();
                  for (var i=0; i<result.length; i++) {
	 var d = ' <td  >'+' <a href="#editEmployeeModal'  + result[i].id + '" class="edit" data-toggle="modal"><i class="material-icons" style="color:black;">&#xE03B;</i></a>'
                       
                        +'<a class="edit1" title="Edit1" data-toggle="tooltip"><i class="material-icons" style="color:#f9d306;">&#xE254;</i></a>'
                        +'<a href="invoiceHistory" class="showInvoiceHistory" title="invoiceHistory" data-toggle="tooltip"><i class="fa fa-history" aria-hidden="true"></i></a>'

                       +'</td>';
	var h='   <td >'+
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
								        +'<button type="button" class="btn btn-info add-new' + result[i].id + '"><i class="fa fa-plus"></i> Add New</button>'
								                     +'  </div>'             
								           +' </div>'    
								         +' </div>'  
								             
								      +'  <table id="table-content" class="table table-bordered itemTable' + result[i].id + '">'+'<thead>'     
								                 +'<tr>'   
								                       
								                   +' <th>ItemName</th>'    
								                     +'   <th>Quantity</th>'
								                      +' <th>Total Amount</th>' 
								                       +'<th>Actions</th>'  
								               +'  </tr>'   
								               +'</thead>' 
								              +"<tbody><tr><td hidden=true><a class='add11'"+"title='Add11' data-toggle='tooltip'>"+"<i class='material-icons' style='color:black;'>&#xE03B;</i></a>" 
								               +' <a class="Edititem" title="Edititem" data-toggle="tooltip"><i class="material-icons" style="color:#f9d306;">&#xE254;</i></a>'+'</td>'       
								                        
															+'</td> </tr></tbody> </table><div class="table-title"><div id="employeesJson"></div>'
								                   
								                +'<div class="row"><div class="col-sm-4" style="float: right;"><button type="button" class="btn btn-info save">Save Changed</button>'
								           
								              +' </div> </div> </div></div></div><div class="modal-footer"><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">'
								            
								        
								
								 +'<input type="submit" class="btn btn-info hhh" value="Save"></div></div></div></div> </td> </tr>'
								
								
								
                     var row = $('<tr>' + 
                       '<td name="Idd" id="Idd">' + result[i].id +'</td>'+
                       '</td>'+'<td name="numbern" id="numbern">' + result[i].number +'</td>'
                       +
                       '<td name="userName" id="userName">' + result[i].userName +  '</td>'+
                       '<td name="customerName" id="customerName">' + result[i].customerName +  '</td>'
                        + '<td id="dateOfCreate">' +result[i].dateOfCreate
                     + '</td><td name="dateOfUpdate" id="dateOfUpdate">' + result[i].dateOfUpdate 
                       +'<td name="totalAmount" id="totalAmount">' + result[i].totalAmount +  '</td>' +
                       '<td name="totalPaid" id="totalPaid">' + result[i].totalPaid +
                       '<td name="remainingAmount" id="remainingAmount">' + result[i].remainingAmount +  '</td>' +
                      '<td name="status" id="status">' + result[i].status +  '</td>' +
                       '<td name="isDeleated" id="isDeleated">' + result[i].isDeleated +  '</td>'   +
                       
                           d + '<td>' + h + '</td>'
                         
                         
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