$(document).ready(function () {
    $(".menu").click(function () {
        const submenu = $(this).next();
        submenu.slideToggle(500);
        submenu.siblings("ul").hide(500);
    });
});
function loadPage(page) {
    if(page==="list-user-by-admin") {
        $('#app-content-load').load('features/admin-management/list-user-by-admin/list-user-by-admin.html');
    }
}