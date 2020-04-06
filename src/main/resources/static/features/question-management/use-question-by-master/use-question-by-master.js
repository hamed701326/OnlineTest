function findAllByTitle() {
    $('#table-question').show();
    const findQuestionByMasterCommand=
        {
            "searchAttribute":[],
            "pageNo":1,
            "pageSize":11,
            "title":$('#title-field').val(),
            "courseId":sessionStorage.getItem("courseId")
        };
    jQuery.ajax({
        url:"http://localhost:9001/question/find-question-by-master",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(findQuestionByMasterCommand),
        success:function (data) {
            if (data.valid) {
                prepareTable(data.questions);
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
            content += "<td >" + data[i].title + "</td>";
            content += "<td >" + data[i].type+ "</td>";
            content += "<td >" + data[i].content + "</td>";
            content += "<td >" +
                "<button  class='btn btn-outline-primary btn-sm' onclick='addQuestion(" + data[i].id+")'>Add</button>" +
                "</td>";
            content += "<td >" +
                "<button  class='btn btn-outline-primary btn-sm' onclick='editQuestion(" + data[i].id+")'>Edit</button>" +
                "</td>";
            content += "</tr>";
        }
        $('#table-body').html(content);
    }
    $('#details').html("number of records : "+data.length);
}

function addQuestion(questionId) {
    const UseQuestionByMaster={
        "questionId":questionId,
        "examId":sessionStorage.getItem("examId"),
        "edited":false
    };
    flush(UseQuestionByMaster);
}
function editQuestion(questionId) {
    const UseQuestionByMaster={
        "questionId":questionId,
        "testId":sessionStorage.getItem("examId"),
        "edited":true
    };
    flush(UseQuestionByMaster);
}
function flush(question) {
    jQuery.ajax({
        url:"http://localhost:9001/question/use-question-by-master",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(question),
        success:function (data) {
            if (data.valid) {
                prepareTable(data.questions);
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