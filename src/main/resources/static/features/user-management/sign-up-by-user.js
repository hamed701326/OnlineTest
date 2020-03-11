

function checkUserName(username) {
    jQuery.ajax({
        url: "http://localhost:9001/check-username",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(username),
        success: function(data, textStatus, jQxhr) {
            console.table(data);
            if (data===""){
                $("#suggest").innerText="this username is okay";
            }else {
                alert(data)
            }
        },
        error: function (errorMessage) {
            alert(errorMessage)
        }
    });
}

// Function to check Whether both passwords
// is same or not.
function checkPassword(form){
    password1 = $("#password").val();
    password2 = $("#password1").val();

    // If password not entered
    if (password1 === '')
        alert ("Please enter Password");

    // If confirm password not entered
    else if (password2 === '')
        alert ("Please enter confirm password");

    // If Not same return False.
    else if (password1 !== password2) {
        alert ("\nPassword did not match: Please try again...");
        return false;
    }

    // If same return True.
    else{
        alert("Password Match: Welcome to TestOnline Website!");
        register()
    }
}

function register() {
    const signUpCommand = {
        "userName": $("#username").val(),
        "password": $("#password").val(),
        "role": $('#role option:selected').val()
    };
    $.ajax({
        url: "http://localhost:9001/sign-up-by-user",
        type: "POST",
        data: JSON.stringify(signUpCommand),
        contentType: "application/json; charset=utf-8",
        success: function (data) {

            if(data.validated){
                //Set response
                $('#app-content-load').text("your account successfully created");
                alert("your account successfully created with id:"+data.idUser);
            }else{
                //Set error messages
                $.each(res.errorMessages,function(key,value){
                    $('input[name='+key+']').after('<span class="error">'+value+'</span>');
                });
            }
        },
        error: function (errorMessage) {
            showAlert('danger', errorMessage.responseJSON.message);
        }
    })
}
