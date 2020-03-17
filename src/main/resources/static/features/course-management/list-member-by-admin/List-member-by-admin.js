$("#list-member-by-admin").ready(function () {
    listMemberByAdmin();
});
function listMemberByAdmin() {
    const listMemberByAdminCommand=
        {"courseId":sessionStorage.getItem("courseId")};
    alert("courseId: "+listMemberByAdminCommand.courseId);
    jQuery.ajax({
        url:"http://localhost:9001/course/list-member-by-admin",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(listMemberByAdminCommand),
        success:function (data) {
            prepareTable(data.userList);
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
            content += "<td >"  +data[i].role
                + "</td>";
            content += "<td >" + data[i].createDate + "</td>";
            content += "<td >" + data[i].status + "</td>";
            content += "<td >" + data[i].isActive + "</td>";
            content += "<td >" +
                "<button type='submit' class='btn btn-outline-danger btn-sm' onclick='remove(" + data[i].userId + ")'>remove</button>" +
                "</td>";
            content += "</tr>";
        }
        $('#table-body').html(content);
    }
    $('#details').html("number of records : "+data.length);
}
function remove(id) {

    const role=$("#role"+id).val();
    const removeMemberByAdminCommand=
        {
            "userId":id,
            "courseId":sessionStorage.getItem("courseId")
        };
    jQuery.ajax({
        url:"http://localhost:9001/course/remove-member-by-admin",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(removeMemberByAdminCommand),
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
