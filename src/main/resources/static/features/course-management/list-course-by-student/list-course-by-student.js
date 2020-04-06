$("#list-course-by-student").ready(function () {
    listCourseByStudent();

});
function listCourseByStudent() {
    const listCourseByStudentCommand=
        {
            "searchAttribute":[],
            "pageNo":1,
            "pageSize":11,
        };
    jQuery.ajax({
        url:"http://localhost:9001/course/list-course-by-student",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(listCourseByStudentCommand),
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
                "<button type='submit' class='btn btn-outline-primary btn-sm' onclick='coursePage(" + data[i].id+")'>Course Page</button>" +
                "</td>";
            content += "</tr>";
        }
        $('#table-body').html(content);
    }
    $('#details').html("number of records : "+data.length);
}

function coursePage(courseId) {
    sessionStorage.setItem("courseId", courseId);
    var url="http://localhost:9001/course/course-panel-for-student";
    $(location).attr('href',url);
}
