$("#edit-exam-by-master").ready(function () {
    $("#edit-exam-by-master").modal('show');
    const id=sessionStorage.getItem("testId");

    $("#title-field").val($("#title"+id).html());
    $("#details-field").val($("#details"+id).html());
    $("#RequiredTime-field").val($("#timeRequired"+id).html());
});
function editExamByMaster(){
    const title = $("#title-field").val();
    const details = $("#details-field").val();
    const requiredTime = $("#RequiredTime-field").val();
    const examId=sessionStorage.getItem("testId");
    const editExamByMasterCommand = {
        "examId":examId,
        "title": title,
        "details": details,
        "requiredTime": requiredTime
    };

    jQuery.ajax({
        url: "http://localhost:9001/test/edit-test-by-master",
        type: "POST",
        data: JSON.stringify(editExamByMasterCommand),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if(data.valid) {
                $("#edit-exam-by-master").modal('hide');
                listExamByMaster();
            }else{
                //Set error messages
                $.each(data.errorMessage, function (key, value) {
                    $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                });
            }
        },
        error: function (errorMessage) {
            // alert(errorMessage)
            $.each(errorMessage, function (key, value) {
                $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
            });
        }
    });
}
