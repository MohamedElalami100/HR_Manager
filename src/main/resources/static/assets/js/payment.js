$('document').ready(function(){
	
	$('table #editButton').on('click', function(event){
		event.preventDefault();
		
		const href = $(this).attr('href');
		//send a get req to href an execute a function after that:
		$.get(href, function(payment, status){
			$('#idEdit').val(payment.id);
			$('#employeeEdit').val(payment.employee.id);
			$('#dateEdit').val(payment.date?.substring(0,10));
			$('#amountEdit').val(payment.amount);
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
