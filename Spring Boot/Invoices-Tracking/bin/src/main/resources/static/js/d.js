$(document).ready(
		function() {

			// SUBMIT FORM
			 $("button").click(function() {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {
 
				// PREPARE FORM DATA
				var formData = {
					username : $("#username").val(),
					password : $("#password").val()
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "authenticate",
					data : JSON.stringify(formData),
					dataType : 'json',
					   crossDomain: false,
                async:true,
					success : function(result) {
						
						  console.log(result);
               // $('body').load('/dashboard');	
             
             
                
					 sessionStorage.setItem("jwtToken", result.token);
					 sessionStorage.setItem("username", formData.username);
					 getUser();
					 
					 
					},
					error : function(e) {
						alert("Error!")
						;
					}
				});

			}
			
			 function getUser() {
    
            $.ajax({
               type : "GET",
               url : "user/"+sessionStorage.getItem("username"),
               success : function(result) {
             
                         if(result.roleName == "AUDITORUER"){
								window.location.href="/dashboard"
							}else{
								window.location.href="/home"
							}
    
                  
               },
               error : function(e) {
                  $("#getResultDiv").html("<strong>Error</strong>");
                  console.log("ERROR: ", e);
               }
            });
         }

		})