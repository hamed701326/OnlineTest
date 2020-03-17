$("#add-course-by-admin").ready(function () {
    $("#add-course-by-admin").modal('show');
});
function addCourseByAdmin() {
    const title = $("#title-field").val();
    const startDate = $("#startDate-field").val();
    const expiredDate = $("#finishDate-field").val();
    const addCourseByAdminCommand = {
        "title": title, "startDate": startDate, "expiredDate": expiredDate
    };

    jQuery.ajax({
        url: "http://localhost:9001/course/add-course-by-admin",
        type: "POST",
        data: JSON.stringify(addCourseByAdminCommand),
        contentType: "application/json; charset=utf-8",
        success: function (data, textStatus, jQxhr) {
            $("#add-course-by-admin").modal('hide');
            listCourseByAdmin();
        },
        error: function (errorMessage) {
            alert(errorMessage)
        }
    });
}