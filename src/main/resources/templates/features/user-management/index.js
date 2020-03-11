$(document).ready(function () {
    $(".menu").click(function () {
        const submenu = $(this).next();
        submenu.slideToggle(500);
        submenu.siblings("ul").hide(500);
    });
});
function loadPage(page) {
    if(page==="list-drug-by-user") {
        $('#app-content-load').load('features/drug-management/list-drug-by-user/list-drug-by-user.html');
    }
}