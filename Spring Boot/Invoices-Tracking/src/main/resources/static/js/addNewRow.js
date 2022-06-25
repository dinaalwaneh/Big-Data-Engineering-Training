$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
	var actions = $("table td:last-child").html();
	// Append table with add row form on add new button click
    $(".add-new").click(function(){
		$(this).attr("disabled", "disabled");
		var index = $("table tbody tr:last-child").index();
        var row = '<tr>' +
            '<td><input type="number" class="form-control" name="Id" id="Id"></td>' +
            '<td><input type="text" class="form-control" name="Name" id="Name"></td>' +
            '<td><input type="number" class="form-control" name="ammo" id="ammo"></td>' +
            '<td><input type="number" class="form-control" name="quan" id="quan"></td>' +
            '<td><input type="text" class="form-control" name="isDeleted" id="isDeleted"></td>' +
			'<td>' + actions + '</td>' +
        '</tr>';
    	$("table").append(row);		
         
     
    });
});