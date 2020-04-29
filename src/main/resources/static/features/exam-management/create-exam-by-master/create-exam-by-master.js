$("#create-exam-by-master").ready(function () {
    $("#create-exam-by-master").modal('show');
});
function createExamByMaster(){
    const title = $("#title-field").val();
    const details = $("#details-field").val();
    const requiredTime = $("#RequiredTime-field").val();
    const courseId=sessionStorage.getItem("courseId");
    alert("title:"+title+"\ndetails:"+details+"\n requiredTime:"+requiredTime+"\n courseId:"+courseId);
    const createExamByMasterCommand = {
        "courseId":courseId,
        "title": title,
        "details": details,
        "requiredTime": requiredTime
    };

    jQuery.ajax({
        url: "http://localhost:9001/test/create-test-by-master",
        type: "POST",
        data: JSON.stringify(createExamByMasterCommand),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if(data.validated) {
                $("#create-exam-by-master").modal('hide');
                listExamByMaster();
            }else{
                //Set error messages
                $.each(data.errorMessage, function (key, value) {
                    $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                });
            }
        },
        error: function (errorMessage) {
            // alert(errorMessages)
            $.each(errorMessage, function (key, value) {
                $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
            });
        }
    });
}