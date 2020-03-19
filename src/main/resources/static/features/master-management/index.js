$(document).ready(function () {
    $(".menu").click(function () {
        const submenu = $(this).next();
        submenu.slideToggle(500);
        submenu.siblings("ul").hide(500);
    });
});
function loadPage(page) {
    if (page === "list-user-by-admin.html") {
        $('#app-content-load').load('features/admin-management/list-user-by-c/' + page)
    }else if(page==="list-course-by-admin.html") {
        $('#app-content-load').load('features/course-management/list-course-by-master'+page)
    }

}