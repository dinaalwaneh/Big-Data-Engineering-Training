$(document).on("click", ".editEmployee", function(){
	
	 var caretId = []; var i =0;
        $(this).parents("tr").find("td:not(:last-child)").each(function(){
	 
			 caretId[i] =  $(this).text();
	  
	
			$(this).html('<input id="d'  + i + '" name="d'  + i + '" type="text" value="'  +  $(this).text() + '" class="form-control"  >');
		 
			  i++;
		}
		);	
	
	
	});