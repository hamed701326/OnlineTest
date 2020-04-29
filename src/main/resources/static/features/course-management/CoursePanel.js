let resultExam= "<div id=\"exam-result-div\">\n" +
    "                <div id=\"buttons-for-listing-result\">\n" +
    "                    <button class='btn-info' onclick=\"loadPage('list-participates-in-now-exam')\">List Participates</button>\n" +
    "                    <button class='btn-info' onclick=\"loadPage('list-exam-result-per-member')\">Exam Result</button>\n" +
    "                </div>\n" +
    "                <div id=\"result-for-now-exam-by-master\">\n" +
    "\n" +
    "                </div>\n" +
    "            </div>";

$(document).ready(function () {
    $(".menu").click(function () {
        const submenu = $(this).next();
        submenu.slideToggle(500);
        submenu.siblings("ul").hide(500);
    });
});
$(document).ready( function () {
    $('#app-content-load').html(resultExam);
});
function loadPage(page) {
    if (page === 'list-exam-by-master.html') {
        $('#app-content-load').load('features/exam-management/list-exam-by-master/' + page)
    }else if(page==='list-member-by-admin.html') {
        $('#app-content-load').load('features/course-management/list-member-by-admin/'+page)
    }else if(page==='editPage'){
        $('#app-content-load').load('features/exam-management/list-exam-by-master-for-adding-question/list-exam-by-master.html')
    }else if(page==='list-participates-in-now-exam'){
        $('#result-for-now-exam-by-master').load('features/exam-management/list-participates-in-exam-by-master/list-participates-in-exam-by-master.html')
    }else if(page==='list-exam-result-per-member'){
        $('#result-for-now-exam-by-master').load('features/exam-management/list-participates-in-exam-with-result/list-participates-in-exam-with-result.html')
    }
}
function homePage() {
    $(location).attr("href","http://localhost:9001/master/")
}
