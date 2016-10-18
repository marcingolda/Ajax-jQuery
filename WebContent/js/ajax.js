function sendToServlet() {
	clearForm();
	$.ajax({
		url : "validateservlet",
		data : {
			login : $("#login > input").val(),
			name : $("#name > input").val(),
			surname :  $("#surname > input").val(),
			email :  $("#email > input").val(),

		},
		dataType : "json",
		async: false, //Chrome problem fix. Nie wiem skad sie bierze :/
		success : updateMessages,
		error : function(result) {
			alert(result.statusText);
		}
	})
	return false;
}

function updateMessages(data) {
	if (data.isValid){
		alert("Udalo Ci sie poprawnie wypelnic formularz!");
	}
	else {
		if (typeof data.login != 'undefined'){
			$("#login > p").text(data.login);
			$("#login > input").addClass( "error");
		}
		if (typeof data.name != 'undefined'){
			$("#name > p").text(data.name);
			$("#name > input").addClass( "error");
		}
		if (typeof data.surname != 'undefined'){
			$("#surname > p").text(data.surname);
			$("#surname > input").addClass( "error");
		}
		if (typeof data.email != 'undefined'){
			$("#email > p").text(data.email);
			$("#email > input").addClass( "error");
		}
	}
}

function clearForm() {
	$("#login > p").text("");
	$("#login > input").removeClass( "error");
	$("#name > p").text("");
	$("#name > input").removeClass( "error");
	$("#surname > p").text("");
	$("#surname > input").removeClass( "error");
	$("#email > p").text("");
	$("#email > input").removeClass( "error");
}