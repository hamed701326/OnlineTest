$("#list-course-by-admin").ready(function () {
    listCourseByAdmin()
});
function listCourseByAdmin() {
    const listCourseByAdminCommand=
        {
            "searchAttribute":[],
            "pageNo":1,
            "pageSize":11
        };
    jQuery.ajax({
        url:"http://localhost:9001/course/list",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(listCourseByAdminCommand),
        success:function (data) {
            alert(1+". received");
            console.table(data.courseList);
            prepareTable(data.courseList);
        },
        error:function (errorMessage) {
            alert(2+". error");
            showAlert('danger', errorMessage.responseJSON.message);
        }
    })
}
function prepareTable(data) {
    alert(3+".received in prepareTable");
    if(data!==undefined) {
        let content = '';
        for (let i = 0; i < data.length; i++) {

            content += "<tr>";
            content += "<th scope='row'>" + data[i].id + "</th>";
            content += "<td >" + data[i].name + "</td>";
            content += "<td >" + data[i].startDate+ "</td>";
            content += "<td >" + data[i].expiredDate + "</td>";
            content += "<td >" +
                "<button type='submit' class='btn btn-outline-primary btn-sm' onclick='addMember(" + data[i].id+")'>add Member</button>" +
                "</td>";
            content += "<td >" +
                "<button type='submit' class='btn btn-outline-primary btn-sm' onclick='editCourse(" + data[i].id+")'>edit Course</button>" +
                "</td>";
            content += "</tr>";
        }
        $('#table-body').html(content);
    }
    $('#details').html("number of records : "+data.length);
}

function showAddCourseByAdminModal(){
    $("#add-course-by-admin-modal-box").load("features/course-management/add-course-by-admin/add-course-by-admin.html");
}
function addMember(courseId) {
    sessionStorage.setItem("courseId",courseId);
    $("#app-content-load").load("features/course-management/add-member-by-admin/add-member-by-admin.html")

}   