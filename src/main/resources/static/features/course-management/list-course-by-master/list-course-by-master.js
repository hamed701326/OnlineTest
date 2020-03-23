$("#list-course-by-master").ready(function () {
    listCourseByMaster();

});
function listCourseByMaster() {
    const listCourseByMasterCommand=
        {
            "searchAttribute":[],
            "pageNo":1,
            "pageSize":11,
        };
    jQuery.ajax({
        url:"http://localhost:9001/course/list-course-by-master",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(listCourseByMasterCommand),
        success:function (data) {
            if (data.validated) {
                prepareTable(data.courseList);
            } else {
                //Set error messages
                $.each(data.errorMessages, function (key, value) {
                    $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                });
            }
        },
        error:function (errorMessage) {
            alert(errorMessage.responseJSON.message);
        }
    })
}
function prepareTable(data) {
    if(data!==undefined) {
        let content = '';
        for (let i = 0; i < data.length; i++) {

            content += "<tr>";
            content += "<th scope='row'>" + data[i].id + "</th>";
            content += "<td >" + data[i].name + "</td>";
            content += "<td >" + data[i].startDate+ "</td>";
            content += "<td >" + data[i].expiredDate + "</td>";
            content += "<td >" +
                "<button type='submit' class='btn btn-outline-primary btn-sm' onclick='createTest(" + data[i].id+")'>Create Test</button>" +
                "</td>";
            content += "<td >" +
                "<button type='submit' class='btn btn-outline-primary btn-sm' onclick='listTest(" + data[i].id+")'>List Tests</button>" +
                "</td>";
            content += "</tr>";
        }
        $('#table-body').html(content);
    }
    $('#details').html("number of records : "+data.length);
}

function createTest(courseId) {
    sessionStorage.setItem("courseId",courseId);
    $("#app-content-load").load("features/exam-management/create-exam-by-master/create-exam-by-master.html")

}
function listTest(courseId) {
    sessionStorage.setItem("courseId", courseId);
    $("#app-content-load").load("features/exam-management/list-exam-by-master/list-exam-by-master.html");
}