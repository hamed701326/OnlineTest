$("#edit-question-by-master").ready(function () {
    $("#edit-question-by-master").modal('show');
    let id=sessionStorage.getItem("questionId");
    $("#title-field").val($("#title"+id).html());
    $("#content-field").val($("#content"+id).html());
    $("#score-field").val($("#score"+id).html());
});

function editQuestionByMaster() {
    const UseQuestionByMaster={
        "questionId":sessionStorage.getItem("questionId"),
        "testId":sessionStorage.getItem("examId"),
        "edited":true,
        "score":$("#score-field").val()
    };
    flush(UseQuestionByMaster);
}