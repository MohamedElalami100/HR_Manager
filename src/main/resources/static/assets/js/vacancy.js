$('document').ready(function(){
	
	$('table #editButton').on('click', function(event){
		event.preventDefault();
		
		const href = $(this).attr('href');
		//send a get req to href an execute a function after that:
		$.get(href, function(employee, status){
			$('#idEdit').val(employee.id);
			$('#nameEdit').val(employee.name);
			$('#jobTitleEdit').val(employee.jobtitleid);
			$('#employeeEdit').val(employee.hiringmanagerid);
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
