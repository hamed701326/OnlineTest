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
                "<button type='submit' class='btn btn-outline-secondary btn-sm' onclick='editTest(" + data[i].id+")'>edit Test</button>" +
                "</td>";
            content += "<td >" +
                "<button type='submit' class='btn btn-outline-danger btn-sm' onclick='deleteTest(" + data[i].id+")'>delete</button>" +
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
    $("#edit-exam-by-master-modal-box").load("features/exam-management/edit-exam-by-master/edit-exam-by-master.html")

}
function deleteTest(id) {
    const deleteExamByMasterCommand={
        "examId":id
    };
    jQuery.ajax({
        url:"http://localhost:9001/test/delete-test-by-master",
        type:"POST",
        contentType: "application/json",
        data:JSON.stringify(deleteExamByMasterCommand),
        success:function (data) {
            if(data.valid){
                alert("your exam successfully deleted.");
                listExamByMaster()
            }else{
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