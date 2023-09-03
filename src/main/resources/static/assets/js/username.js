$('document').ready(function(){				
	const username = document.getElementById("yourUsername");
	const usernameError = document.getElementById("usernameError");

	fetch('/users/list')
    .then(response => response.json())
    .then(data => {
        // Assign the received user data to the usersList variable
        const usersList = data;
        console.log(data);
		function validateUsername(){
			const userNameToCheck = username.value;
	        if (usersList.some(user => user.username === userNameToCheck)) {
	            console.log(userNameToCheck + ' is in the users list.');
	            username.setCustomValidity("username already used");
	            usernameError.textContent = "username already used";
	        } else {
	            console.log(userNameToCheck + ' is not in the users list.');
	            username.setCustomValidity('');
	            usernameError.textContent = "";
	        }
		}
		username.onchange = validateUsername;			

    })
    .catch(error => {
        console.log('Error:', error);
    });	
});