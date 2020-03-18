function checkSizeUserName() {
    var username=$("#username").val();
    var response=$("#error_div");

    if(username.length<6){
        response.html("username is short ");

    }else {
        response.html("");
    }
}
function listUserByAdmin() {
    const lisUserByAdminCommand=
        {"status":"APPROVED_ACCOUNT",
         "userName":$("#userName_field").val(),
         "role":$("#role_field option:selected").val(),
            "searchAttribute":["status","userName","role"]
        };
    jQuery.ajax({
        url:"http://localhost:9001/admin/list-user-by-admin",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(lisUserByAdminCommand),
        success:function (data) {
            createTable(data.userDTOS);
        },
        error:function (errorMessage) {
            // alert(2)
            alert(errorMessage)
        }
    })
}

function checkExistUserinThisCourse(data) {
    let isexist=false;
    const courseId=parseInt(sessionStorage.getItem("courseId"));
    for(let i=0;i <data.length;i++){
        if(data[i].id===courseId) {
            isexist = true;
            break;

        }
    }
    return isexist;
}

function createTable(data) {
    if(data!==undefined) {
        let content = '';
        for (let i = 0; i < data.length; i++) {
            content += "<tr>";
            content += "<th scope='row'>" + data[i].userId + "</th>";
            content += "<td >" + data[i].userName + "</td>";
            content += "<td >"  + data[i].role+ "</td>";
            content += "<td >" + data[i].isActive + "</td>";

            if(data[i].courseList.length>0 && checkExistUserinThisCourse(data[i].courseList)) {
                content+="<td style='color: limegreen'> Added </td>"
            }else {
                content += "<td >" +
                    "<button type='submit' class='btn btn-outline-primary btn-sm' onclick='add(" + data[i].userId
                    + "," + ")'>add</button>" +
                    "</td>";
            }
            content += "</tr>";
        }
        $('#table-body2').html(content);
    }
    $('#details').html("Number of records : "+data.length);
}
function add(userId)  {
    alert("userId: "+userId);

    const addMemberByAdmin=
        {
         "userId":userId,
         "courseId":sessionStorage.getItem("courseId"),
        };
    jQuery.ajax({
        url:"http://localhost:9001/course/add-member-by-admin",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(addMemberByAdmin),
        success:function (data) {
            if(data.validated){
                //Set response
                $('#response_div').text("Member added successfully .");
                listUserByAdmin();
            }else {
                $.each(res.errorMessages,function(key,value){
                    $('input[name='+key+']').after('<span class="error">'+value+'</span>');
                });
            }
        },
        error:function (errorMessage) {
            alert(errorMessage)
        }
    })

}