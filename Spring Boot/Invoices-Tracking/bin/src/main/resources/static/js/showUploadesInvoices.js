$(document).on("click", ".show-uplades", function(){
	
	
	  $.ajax({	 
					
               type : "GET",
               contentType : "application/json",
               url : "http://localhost:8082/UploadedInvicesPaginationAndSort/0/2/dateOfCreate",
               dataType : 'json',
               headers: {
               			Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
    		    },
    		   async: true,  
               success : function(result) {
 					
 					console.log("result = "+result);
 					$('#table-content').empty();
 					
 				
                   for (var i=0; i<result.length; i++) {     
	 var d = ' <td  >'+' <a href="#editEmployeeModal'  + result[i].id + '" class="edit" data-toggle="modal"><i class="material-icons" style="color:black;">&#xE03B;</i></a>'
                       
                        +'<a class="edit1" title="Edit1" data-toggle="tooltip"><i class="material-icons" style="color:#f9d306;">&#xE254;</i></a>'
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
							+'	<div class="form-group">'+
								
								     
								       
								'<div class="table-title"><div id="employeesJson"></div>'
								                   
								                +'<div class="row"><div class="col-sm-4" style="float: right;">'
								           
								              +' </div> </div> </div></div></div><div class="modal-footer"><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">'
								            
								        
								
								 +'</div></div></div></div> </td> </tr>'
							         
                     var row = $('<tr>' + 
                       '<td name="Idd" id="Idd">' + result[i].id +'</td>'+
                       '</td>'+'<td name="numbern" id="numbern">' + result[i].number +'</td>'
                       +
                       '<td name="userName" id="userName">' + result[i].userName +  '</td>'+
                       '<td name="customerName" id="customerName">' + result[i].customerName +  '</td>'
                       +
                       '<td name="fileName" id="fileName">' + result[i].fileName +  '</td>'
                        + '<td id="dateOfCreate">' +result[i].dateOfCreate
                         +d+h
                         
                          +'</tr>');
             
 
                    $('#table-content').append(row);}
                     
                      
               },
               error : function(e) {
                   console.log("jjj: ", e);
               }
            });
	
	});