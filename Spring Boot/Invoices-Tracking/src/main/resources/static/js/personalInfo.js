$(document).ready(
      function() {
    
            $.ajax({
               type : "GET",
               url : "user/"+sessionStorage.getItem("username"),
               success : function(result) {
             
                    
                   
	
	$("#username").append(result.name);
					 
                                   
                     console.log("Success: ", result);
                  
               },
               error : function(e) {
                  $("#getResultDiv").html("<strong>Error</strong>");
                  console.log("ERROR: ", e);
               }
            });
         }
      )