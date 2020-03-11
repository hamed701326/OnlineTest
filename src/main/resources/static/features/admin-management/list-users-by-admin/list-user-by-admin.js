
function listUserByAdmin() {
    const lisUserByAdminCommand=
        {"status":"WAITING_FOR_CONFIRM"};
    jQuery.ajax({
        url:"http://localhost:9001/user/list-user-by-admin",
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
    let content = '';
    for (let i = 0; i < data.length; i++) {
        content +=  "<tr>";
        content +=  "<th scope='row'>" + data[i].userId + "</th>";
        content +=  "<td >" + data[i].userName+ "</td>";
        content +=  "<td >" + data[i].role + "</td>";
        content +=  "<td >" + data[i].createDate + "</td>";
        content +=  "<td >" + data[i].status + "</td>";
        content +=  "<td >" + data[i].isActive + "</td>";
        content +=  "<td >" +
            "<button type='button' class='btn btn-outline-primary btn-sm' onclick='showEditUserByAdminModal("+ data[i].id +")'>edit</button>" +
            "</td>";
        content +=  "<td >" +
            "<button type='submit' class='btn btn-outline-primary btn-sm' onclick='verify("+ data[i].id +")'>verify</button>" +
            "</td>";
        content +=  "</tr>";
    }
    $('#table-body').html(content);
}
function verify(id) {
    const verifyUserByAdminCommand=
        {"userId":id};
    jQuery.ajax({
        url:"http://localhost:9001/user/verify-user-by-admin",
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