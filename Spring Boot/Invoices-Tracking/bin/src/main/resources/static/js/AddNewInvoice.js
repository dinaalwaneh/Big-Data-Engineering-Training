	$(document).on("click", ".addNewInnoice", function(){
	
   
 
	
			 var employeeModel =  
				{
			       
			        number: $("#invoiceNumber").val(),
			        totalAmount: 0,
			        totalPaid: 0,
			        remainingAmount: 0,
			        status:  "not paid",
			        isDeleated:  "false",
			        customerName: $("#customersName").val(),
			        userName: sessionStorage.getItem("username"),
			        fileName:$("#fileupload").val()
			    } 
	employeeModel.fileName = employeeModel.fileName.substr(12);				 
 
			async function uploadFile() {
			 
			  let formData = new FormData(); 
			  formData.append("file", fileupload.files[0]);
			  let response = await fetch('/upload', {
			    method: "POST", 
			    body: formData
			  }); 
			
			}     
		    
		    uploadFile();	
		           
               var requestJSON = JSON.stringify(employeeModel);
               $.ajax({
                  type : "POST",
                  url : "http://localhost:8082/invoice",
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
	 
					 alert("no");
                  }
               });
			 
		 		
			 
		
			
    });