$('document').ready(function(){
	
	$('table #editButton').on('click', function(event){
		event.preventDefault();
		
		const href = $(this).attr('href');
		//send a get req to href an execute a function after that:
		$.get(href, function(jobApplication, status){
			$('#idEdit').val(jobApplication.id);
			$('#candidateEdit').val(jobApplication.candidateid);
			$('#vacancyEdit').val(jobApplication.vacancyid);
			$('#dateEdit').val(jobApplication.date?.substring(0,10));
			$('#statusEdit').val(jobApplication.status);			
		});
		
		$('#editModal').modal('show');
			
	});
	
	$('table #deleteButton').on('click', function(event){
		event.preventDefault();
		
		const href = $(this).attr('href');
		$('#confirmDeleteButton').attr("href", href);
		
		$('#deleteModal').modal('show');		

	});
	
	$('table #detailsCandidateButton').on('click', function(event){
		event.preventDefault();
		const href = $(this).attr('href');
		$.get(href, function(candidate, status){
			$('#fullnameDetails').text(candidate.firstname + " " + candidate.lastname);
			$('#emailDetails').text(candidate.email);
			$('#phoneDetails').text(candidate.phone);
			const resume = document.getElementById("resumeDetails");
			resume.href = "/assets/pdf/" + candidate.resume;
		});
		
		$('#detailsCandidateModal').modal('show');
			
	});
	
	$('table #editCandidateButton').on('click', function(event){
		event.preventDefault();
		
		const href = $(this).attr('href');

		//send a get req to href an execute a function after that:
		$.get(href, function(candidate, status){
			$('#idCandidateEdit').val(candidate.id);
			$('#firstnameEdit').val(candidate.firstname);
			$('#lastnameEdit').val(candidate.lastname);
			$('#emailEdit').val(candidate.email);		
			$('#phoneEdit').val(candidate.phone);			
			$('#resumeEdit').val(candidate.resume);							
		});
		
		$('#editCandidateModal').modal('show');
			
	});

});
