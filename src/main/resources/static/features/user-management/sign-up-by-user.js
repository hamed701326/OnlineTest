
function checkExistUserName() {
    var username=$("#username").val();
    var response=$("#pre");
    if(username.length<6){
        response.html("username is short ");
    }else {

        jQuery.ajax({
            url: "http://localhost:9001/check-username",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: username,
            success: function (data) {
                console.table(data);
                sessionStorage.setItem("validatedUser",data.validated)

                if (data.validated ) {
                    response.html(data.message);
                    $("#pre").css("color","green");

                } else {

                    response.html(data.message);
                    $("#pre").css("color","red");

                }

            },
            error: function (errorMessage) {
                alert(errorMessage)
            }
        });
    }
}
function checkSizeUserName() {
    var username=$("#username").val();
    var response=$("#pre");

    if(username.length<6){
        response.html("username is short ");

    }else {
        response.html("");
    }
}

// Function to check Whether both passwords
// is same or not.
function checkPassword(){
    password1 = $("#password").val();
    password2 = $("#password1").val();
    if(sessionStorage.getItem("validatedUser")){
    // If password not entered
    if (password1 === '') {
        $("#pre").html("Please enter Password");
        $("#pre").css("color","red")
        return false
    }
    // If confirm password not entered
    else if (password2 === '') {
        $("#pre").html("Please enter confirm password");
        $("#pre").css("color","red")
        return false
    }
    // If Not same return False.
    else if (password1 !== password2) {
        $("#pre").html("Password did not match: Please try again...");
        $("#pre").css("color","red");
        return false
    }

    // If same return True.
    else{

        $("#pre").css("color","green");
        $("#pre").html("Password Match");
        return true;

    }
    }
}

function register() {
    const signUpCommand = {
        "userName": $("#username").val(),
        "password": $("#password").val(),
        "role": $('#role option:selected').val()
    };
    if(checkPassword()) {
        $.ajax({
            url: "http://localhost:9001/sign-up-by-user",
            type: "POST",
            data: JSON.stringify(signUpCommand),
            contentType: "application/json; charset=utf-8",
            success: function (data) {

                if (data.validated) {
                    //Set response
                    $('#pre').css("color", "green");
                    $('#pre').html("<h5>your account successfully created with id:" + data.idUser + "</h5>");
                } else {
                    //Set error messages
                    $.each(res.errorMessages, function (key, value) {
                        $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                    });
                }
            },
            error: function (errorMessage) {
                alert('danger', errorMessage.responseJSON.message);
            }
        })
    }
}
