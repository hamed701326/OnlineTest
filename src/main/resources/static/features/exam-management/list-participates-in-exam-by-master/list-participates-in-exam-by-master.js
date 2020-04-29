$("#list-participates-in-exam-by-master").ready(function () {
    listParticipatesByMasterTypical();
});

function listParticipatesByMasterTypical(){
    const listParticipateInExamByMasterCommand=
        {
            "searchAttribute":[],
            "pageNo":1,
            "pageSize":11,
            "courseId":sessionStorage.getItem("courseId")
        };
    jQuery.ajax({
        url:"http://localhost:9001"+"/test/list-participator-in-exam-by-master",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(listParticipateInExamByMasterCommand),
        success:function (data) {
            if (data.valid) {
                    prepareTable(data.participateList);

            } else {
                //Set error messages
                let content="Error"+"" +"\n"+
                $.each(data.errorMessages, function (key, value) {
                  content+=key+":"+value+"\n"
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
    if(data!==undefined) {
        let content = '';
        for (let i = 0; i < data.length; i++) {
            content += "<tr>";
            content += "<th >" + data[i].id + "</th>";
            content += "<td >" + data[i].userName + "</td>";
            content += "<td >" + data[i].status+ "</td>";

            content += "</tr>";
        }
        $('#table-body').html(content);
    }
    $('#details').html("Number Of Records : "+data.length);
}

