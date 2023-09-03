$('document').ready(function(){
	
	$('table #editButton').on('click', function(event){
		event.preventDefault();
		
		const href = $(this).attr('href');
		//send a get req to href an execute a function after that:
		$.get(href, function(employee, status){
			$('#idEdit').val(employee.id);
			$('#firstnameEdit').val(employee.firstname);
			$('#lastnameEdit').val(employee.lastname);
			$('#socialSecurityNumberEdit').val(employee.socialSecurityNumber);
			$('#genderEdit').val(employee.gender);
			$('#maritalStatusEdit').val(employee.maritalStatus);
			$('#dateOfBirthEdit').val(employee.dateOfBirth?.substring(0,10));
			$('#cityEdit').val(employee.city);
			$('#addressEdit').val(employee.address);
			$('#phoneEdit').val(employee.phone);
			$('#mobileEdit').val(employee.mobile);
			$('#emailEdit').val(employee.email);
			$('#hireDateEdit').val(employee.hireDate?.substring(0,10));
			$('#jobTitleEdit').val(employee.jobtitleid);
			$('#employeeTypeEdit').val(employee.employeetypeid);
			$('#photoEdit').val(employee.photo);
			$('#txtUsernameEdit').val(employee.username);

		});
		
		$('#editModal').modal('show');
			
	});
	
	$('table #detailsButton').on('click', function(event){
		event.preventDefault();
		
		const href = $(this).attr('href');
		$.get(href, function(employee, status){
			$('#fullnameDetails').text(employee.firstname + " " + employee.lastname);
			$('#fullnameDetailsHeader').text(employee.firstname + " " + employee.lastname);
			$('#socialSecurityNumberDetails').text(employee.socialSecurityNumber);
			$('#genderDetails').text(employee.gender);
			$('#maritalStatusDetails').text(employee.maritalStatus);
			$('#dateOfBirthDetails').text(employee.dateOfBirth?.substring(0,10));
			$('#cityDetails').text(employee.city);
			$('#addressDetails').text(employee.address);
			$('#phoneDetails').text(employee.phone);
			$('#mobileDetails').text(employee.mobile);
			$('#emailDetails').text(employee.email);
			$('#hireDateDetails').text(employee.hireDate?.substring(0,10));
			$('#jobTitleDetails').text(employee.jobTitle.name);
			$('#jobTitleDetailsHeader').text(employee.jobTitle.name);
			$('#employeeTypeDetails').text(employee.employeeType.name);
			$('#photoDetails').text(employee.photo);
		});
		
		$('#detailsModal').modal('show');
			
	});
	
	$('table #deleteButton').on('click', function(event){
		event.preventDefault();
		
		const href = $(this).attr('href');

		$('#confirmDeleteButton').attr("href", href);
		
		$('#deleteModal').modal('show');		

	});
	
	$('table #photoButton').on('click',function(event) {
	   event.preventDefault();

	   const href = $(this).attr('href');
	   
	   $('#photoModal #employeePhoto').attr('src', href);
	   
	   $('#photoModal').modal('show');		
	});
	
});
