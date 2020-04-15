$("#list-exam-by-student").ready(function () {
    listExamByStudent();

});
function listExamByStudent() {
    const listExamByStudentCommand=
        {
            "searchAttribute":[],
            "pageNo":1,
            "pageSize":11,
            "courseId":sessionStorage.getItem("courseId")
        };

    jQuery.ajax({
        url:"http://localhost:9001/test/list-test-by-student",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(listExamByStudentCommand),
        success:function (data) {
            if (data.valid) {
                prepareTable(data.examList);
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
            content += "<td id='title"+data[i].id+"'>" + data[i].title + "</td>";
            content += "<td id='details"+data[i].id+"'>" + data[i].details+ "</td>";
            content += "<td id='timeRequired"+data[i].id+"'>" + data[i].timeRequired + "</td>";
            content += "<td >" + data[i].createBy + "</td>";
            content += "<td >" + data[i].numberOfQuestion + "</td>";
            content += "<td >" +
                "<button type='submit' class='btn btn-outline-secondary btn-sm' onclick='takeTest(" + data[i].id+")'>Take Test</button>" +
                "</td>";
            content += "</tr>";
        }
        $('#table-body').html(content);
    }
    $('#details').html("number of records : "+data.length);
}
function takeTest(examId) {
    sessionStorage.setItem("examId",examId);
    var url="http://localhost:9001/test/test-page";
    $(location).attr('href',url);
}