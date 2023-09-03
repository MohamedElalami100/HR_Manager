$('document').ready(function(){
	
	$('table #editButton').on('click', function(event){
		event.preventDefault();
		
		const href = $(this).attr('href');
		//send a get req to href an execute a function after that:
		$.get(href, function(jobTitle, status){
			$('#idEdit').val(jobTitle.id);
			$('#nameEdit').val(jobTitle.name);
			$('#descriptionEdit').val(jobTitle.description);
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
