$(document).ready(function () {
    $(".menu").click(function () {
        const submenu = $(this).next();
        submenu.slideToggle(500);
        submenu.siblings("ul").hide(500);
    });
});
function loadPage(page) {
    if(page==="list-course-by-student.html") {
        $('#app-content-load').load('features/course-management/list-course-by-student/'+page)
    }

}
function logOut() {
    $(location).attr('href',"http://localhost:9001/logout");
}