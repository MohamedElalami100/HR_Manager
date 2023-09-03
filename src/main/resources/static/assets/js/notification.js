$('document').ready(function(){
	const notification1 = document.getElementById('pendingLeavesNumber');
	const notification2 = document.getElementById('shortlistedNumber');
	const notification3 = document.getElementById('interviews');

	const notificationDiv1 = document.getElementById('pendingLeavesDiv');
	const notificationDiv2 = document.getElementById('shortlistedDiv');
	const notificationDiv3 = document.getElementById('interviewsDiv');
	
	const notificationNumber = document.getElementById('notificationNumber');
	const notificationNumber2 = document.getElementById('notificationNumber2');
	
	let nbr = 0;
	
	if( notification1.textContent == "0"){
		notificationDiv1.style.display = 'none';
	}
	else{
		notificationDiv1.style.display = 'block';
		nbr++;
	}
	
	if( notification2.textContent == "0"){
		notificationDiv2.style.display = 'none';
	}
	else{
		notificationDiv2.style.display = 'block';
		nbr++;
	}
	
	if( notification3.textContent == "0"){
		notificationDiv3.style.display = 'none';
	}
	else{
		notificationDiv3.style.display = 'block';
		nbr++;
	}
	
	notificationNumber.textContent = nbr.toString();
	notificationNumber2.textContent = nbr.toString();

});