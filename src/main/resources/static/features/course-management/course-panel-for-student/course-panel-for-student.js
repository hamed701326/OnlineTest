$(document).ready(function () {
    $(".menu").click(function () {
        const submenu = $(this).next();
        submenu.slideToggle(500);
        submenu.siblings("ul").hide(500);
    });
});
function loadPage(page) {
    if (page === 'list-exam-by-student.html') {
        $('#app-content-load').load('features/exam-management/list-exam-by-student/' + page)
    }
}
function homePage() {
    $(location).attr("href","http://localhost:9001/student/")
}