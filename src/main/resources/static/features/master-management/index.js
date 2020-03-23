$(document).ready(function () {
    $(".menu").click(function () {
        const submenu = $(this).next();
        submenu.slideToggle(500);
        submenu.siblings("ul").hide(500);
    });
});
function loadPage(page) {
    if (page === "list-user-by-master.html") {
        $('#app-content-load').load('features/user-management/list-user-by-master/' + page)
    }else if(page==="list-course-by-master.html") {
        $('#app-content-load').load('features/course-management/list-course-by-master/'+page)
    }

}