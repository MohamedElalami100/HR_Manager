$('document').ready(function(){
	
	$('table #editButton').on('click', function(event){
		event.preventDefault();
		
		const href = $(this).attr('href');
		//send a get req to href an execute a function after that:
		$.get(href, function(user, status){
			console.log(user);
			$('#idEdit').val(user.id);
			$('#usernameEdit').val(user.username);
			$('#firstnameEdit').val(user.firstname);
			$('#lastnameEdit').val(user.lastname);
			$('#passwordEdit').val(user.password);
		});
		
		$('#editModal').modal('show');
			
	});	
	
	$('table #deleteButton').on('click', function(event){
		event.preventDefault();
		
		const href = $(this).attr('href');

		$('#confirmDeleteButton').attr("href", href);
		
		$('#deleteModal').modal('show');		

	});
	
});
