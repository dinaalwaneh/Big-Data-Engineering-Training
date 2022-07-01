	$(document).on("click", ".addNewInnoice", function(){
	
    var currentdate = new Date(); 
    var datetime = currentdate.getFullYear() + "-"
                + (currentdate.getMonth()+1)  + "-" 
              +(currentdate.getDay()+26)+ " " 
                + currentdate.getHours() + ":"  
                + currentdate.getMinutes() + ":" 
                + currentdate.getSeconds()
 

		
			 var employeeModel =  
				{
			       
			        number: $("#invoiceNumber").val(),
			        totalAmount: 0,
			        totalPaid: 0,
			        remainingAmount: 0,
			        status:  "not paid",
			        isDeleated:  "false",
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
	 
	 alert("nooonooonooonooonooonooonooonooonooonooonooonooonooonooonooonooonooonooonooonooonooonooonooonooonoooo");
                  }
               });
			 
		 		
			 
		
			
    });