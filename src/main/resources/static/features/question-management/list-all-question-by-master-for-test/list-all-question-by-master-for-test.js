$("#list-all-question-by-master-div").ready(function () {
    listAllQuestionByMaster()
});
function listAllQuestionByMaster(){
    const listAllQuestionByMasterCommand={
        "examId":sessionStorage.getItem("testId")
    };
    jQuery.ajax({
        url:"http://localhost:9001"+"/question/list-all-question-by-master-for-test",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(listAllQuestionByMasterCommand),
        success:function (data) {
            if (data.valid) {
                prepareTable(data.questions);
            } else {
                //Set error messages
                let content="Error:\n";
                $.each(data.errorMessages, function (key, value) {
                    content+=key+" : "+value+"\n";
                });
                alert(content)
            }

        },
        error:function (errorMessage) {
            alert(errorMessage.responseJSON.message);
        }
    })
}
function prepareTable(data) {
    let score=0;
    let details = $('#details');
    if(data!==undefined) {

        let content = '';
        for (let i = 0; i < data.length; i++) {

            content += "<tr>";
            content += "<th scope='row'>" + data[i].id + "</th>";
            content += "<td >" + data[i].title + "</td>";
            content += "<td >" + data[i].type+ "</td>";
            content += "<td >" + data[i].content + "</td>";
            content += "<td >" + data[i].Level + "</td>";
            content+="<td>"+data[i].score+"</td>";
            content += "<td >" +
                "<button  class='btn btn-outline-primary btn-sm' disabled onclick='editQuestion(" + data[i].id+")'>Edit</button>" +
                "</td>";
            content += "</tr>";
            score+=data[i].score;
        }
        $('#table-body').html(content);
    }
    details.html("number of records : "+data.length);
    details.html("Total Score : "+score);
}
