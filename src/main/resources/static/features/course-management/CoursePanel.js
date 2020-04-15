$(document).ready(function () {
    $(".menu").click(function () {
        const submenu = $(this).next();
        submenu.slideToggle(500);
        submenu.siblings("ul").hide(500);
    });
});
function loadPage(page) {
    if (page === 'list-exam-by-master.html') {
        $('#app-content-load').load('features/exam-management/list-exam-by-master/' + page)
    }else if(page==='list-member-by-admin.html') {
        $('#app-content-load').load('features/course-management/list-member-by-admin/'+page)
    }else if(page==='editPage'){
        $('#app-content-load').load('features/exam-management/list-exam-by-master-for-adding-question/list-exam-by-master.html')
    }
}
function homePage() {
    $(location).attr("href","http://localhost:9001/master/")
}