$("#list-user-by-admin").ready(function () {
    listUserByAdmin();
});
function listUserByAdmin() {
    const lisUserByAdminCommand=
        {"status":"WAITING_FOR_CONFIRM",
        "searchAttribute":["status"]};
    jQuery.ajax({
        url:"http://localhost:9001/admin/list-user-by-admin",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(lisUserByAdminCommand),
        success:function (data) {
            console.table(data.userDTOS);
            prepareTable(data.userDTOS);
        },
        error:function (errorMessage) {
            alert(errorMessage)
        }
    })
}
function prepareTable(data) {
    if(data!==undefined) {
        let content = '';

        for (let i = 0; i < data.length; i++) {
            content += "<tr>";
            content += "<th scope='row'>" + data[i].userId + "</th>";
            content += "<td >" + data[i].userName + "</td>";
            content += "<td >"  +
            "<input list='roles' name='role' id='"+"role"+data[i].userId+"' value='"+data[i].role+"'>"
            + "</td>";
            content += "<td >" + data[i].createDate + "</td>";
            content += "<td >" + data[i].status + "</td>";
            content += "<td >" + data[i].isActive + "</td>";
            content += "<td >" +
                "<button type='submit' class='btn btn-outline-primary btn-sm' onclick='verify(" + data[i].userId + ")'>verify</button>" +
                "</td>";
            content += "</tr>";
        }
        $('#table-body').html(content);
    }
    $('#details').html("number of records : "+data.length);
}
function verify(id) {

    const role=$("#role"+id).val();
    alert("id="+id+"\n role = "+role);
    const verifyUserByAdminCommand=
        {
            "userId":id,
            "role":role
        };
    jQuery.ajax({
        url:"http://localhost:9001/admin/verify-user-by-admin",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(verifyUserByAdminCommand),
        success:function (data) {
            if(data.message===null){
                alert("User confirmed");
                listUserByAdmin();
            }else {
                alert(data.message)
            }
        },
        error:function (errorMessage) {
            alert(errorMessage)
        }
    })
}
function showEditUserByAdminModal(id){
    $("#edit-user-by-Admin").load("features/admin-management/edit-users-by-admin/edit-user-by-admin.html");
}