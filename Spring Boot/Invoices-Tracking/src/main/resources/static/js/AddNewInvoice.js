	$(document).on("click", ".addNewInnoice", function(){
	
    var currentdate = new Date(); 
    var datetime = currentdate.getFullYear() + "-"
                + (currentdate.getMonth()+1)  + " " 
              
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() + ":" 
                + currentdate.getSeconds()
 

		
			 var employeeModel =  
				{
			        dateOfCreate: datetime.toString(),
			        dateOfUpdate: datetime.toString(),
			        number: $("#number").val(),
			        totalAmount: 0,
			        totalPaid: 0,
			        remainingAmount: 0,
			        status:  $(".status:checked").val(),
			        isDeleated:  $(".isDeleted:checked").val(),
			        customerName: $("#customersName").val(),
			        userName: sessionStorage.getItem("username")
			    } 
						     
               
               var requestJSON = JSON.stringify(employeeModel);
               $.ajax({
                  type : "POST",
                  url : "http://localhost:8082/add/invoice",
                  headers : {
                     "Content-Type" : "application/json",
                      Authorization: 'Bearer ' + sessionStorage.getItem("jwtToken")
                  },
                  data : requestJSON,
                  success : function(data) {
	
                  		console.log("doneeeeeeeeeeee");
            
                  			location.reload();
 
                  },
                  error : function(data) {
	 
	 alert("noooo");
                  }
               });
			 
		 		
			 
		
			
    });