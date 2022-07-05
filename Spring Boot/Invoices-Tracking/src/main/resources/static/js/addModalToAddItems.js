$(document).on("click", ".edit", function(){
            
 	
	
		

var i = $(this).parents("tr").find("td:nth-child(1)").text().replace(' Swish WalletSwish Walletsanselk Walletnoia Walletmilk WalletSwish Walletsanselk Walletnoia Walletmilk Wallet','');
i=i.replace(/[A-Z]/g,'');
i=i.replace(/[a-z]/g,'');
i=i.replace(' ','');
i=i.replace(' ','');
f(i);

function f(j){ 
	$(document).on("click", ".add-new"+(j) , function(){
	$('[data-toggle="tooltip"]').tooltip();
	var actions = $(".itemTable"+(j)+" td:last-child").html();
	 	// alert( $(this).parents("tr").find("td:first-child").text()[0]);
	// Append table with add row form on add new button click
  
		$(this).attr("disabled", "disabled");
		var index = $(".itemTable tbody tr:last-child").index();
        var row = '<tr>' +
            '<td><select id="ItemName"  class="ItemName" name="ItemName">'+
	           
	       '</select>'+'</td>' +
            '<td><input type="text" class="form-control" name="quan" id="quan"></td>' +
             '<td><input type="text" class="form-control" name="Tamount" id="Tamount"></td>' +
			'<td>' + actions + '</td>' +
        '</tr>';
    	$(".itemTable"+(j)).append(row);		
  
	
	});
}

});