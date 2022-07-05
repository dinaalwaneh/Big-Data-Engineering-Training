
    
    $(document).on("click", ".add-new", function(){
	
	$('[data-toggle="tooltip"]').tooltip();
	   var actions = $("table td:last-child").html();
		var index = $("table tbody tr:last-child").index();
        var row = '<tr>' +
            '<td><input type="number" class="form-control" name="Id" id="Id"></td>' +
            '<td><input type="text" class="form-control" name="customer" id="customer"></td>' +
            '<td><input type="text" class="form-control" name="address_" id="address_"></td>' +
            '<td><input type="text" class="form-control" name="phone_" id="phone_"></td>' +
			'<td><input type="text" class="form-control" name="email_" id="email_"></td>' +
			'<td><input type="text" class="form-control" name="enabled_" id="enabled_"></td>' +
			'<td>' + actions + '</td>' +
        '</tr>';
    	$("table").append(row);	
	
 	 $(".add1").click(function(event) {
	
		var employ  =  {   
			        name: "customer",
			        customerName: $("#customer").val(),
			        address: $("#address_").val(),
			        phone: $("#phone_").val(),
			        email: $("#email_").val(),
			        enabled:  "true",
			    };
				 
				event.preventDefault();
				ajaxGet(employ);
			});


			// DO GET
			function ajaxGet(employ) {
				 
				 
		  
               var requestJSON = JSON.stringify(employ);
               console.log("requestJSONijjjjjjjjjjjjj"+requestJSON);
				$.ajax({
					type : "POST",
					url : "http://localhost:8082/customer",
					headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
                  data : requestJSON,
					success : function(result) {
						
					location.reload();
						
					},
					error : function(e) {
						
						console.log("ERROR: ", e);
					}
				});
			}
	});