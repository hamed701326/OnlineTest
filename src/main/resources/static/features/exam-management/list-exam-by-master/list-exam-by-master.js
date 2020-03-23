$("#list-exam-by-master").ready(function () {
    listExamByMaster();

});
function listExamByMaster() {
    const listExamByMasterCommand=
        {
            "searchAttribute":[],
            "pageNo":1,
            "pageSize":11,
            "courseId":sessionStorage.getItem("courseId")
        };
    jQuery.ajax({
        url:"http://localhost:9001/test/list-test-by-master",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(listExamByMasterCommand),
        success:function (data) {
            if (data.validated) {
                alert("data valid");
                prepareTable(data.examList);
            } else {
                alert("data not valid");
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
            content += "<td >" + data[i].title + "</td>";
            content += "<td >" + data[i].details+ "</td>";
            content += "<td >" + data[i].timeRequired + "</td>";
            content += "<td >" + data[i].createBy + "</td>";
            content += "<td >" + data[i].numberOfQuestion + "</td>";
            content += "<td >" +
                "<button type='submit' class='btn btn-outline-primary btn-sm' onclick='editTest(" + data[i].id+")'>edit Test</button>" +
                "</td>";
            content += "</tr>";
        }
        $('#table-body').html(content);
    }
    $('#details').html("number of records : "+data.length);
}
function showCreateExamByMasterModal(){
    $("#create-exam-by-master-modal-box").load("features/exam-management/create-exam-by-master/create-exam-by-master.html");
}
function editTest(testId) {
    sessionStorage.setItem("testId",testId);
    $("#app-content-load").load("features/test-management/add-question-by-master/add-question-by-Master.html")

}
