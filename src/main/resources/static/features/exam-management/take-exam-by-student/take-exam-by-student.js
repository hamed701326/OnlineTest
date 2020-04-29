let pos, test, test_status, question;
let questions;

var time;
var second=59;
function f() {

    if(second===0){
        time--;
        second=59
    }
    else {
        second--
    }
    let t="Remain Time:"+ Math.floor(time)+":";
    if(second<10){
        t+="0";
    }
    t+=second;
    $("#reverseTime").html(t);
    if(time===0){
        $("#reverseTime").html("EXPIRED");
        alert("your time finished and your response save successfully.:)");
        finish();
    }

}
function finish() {
    sessionStorage.removeItem("examId");
    jQuery.ajax({
        url:"http://localhost:9001"+"/test/finish-test-by-student",
        type:"POST",
        success:function (data) {
            if(data.valid) {
                alert("your test finished successfully"
                    + "\n your score for choice question is: "+data.score);
                $(location).attr("href", "http://localhost:9001" + "/course/course-panel-for-student")
            }else {
                
                $.each(data.errorMessages, function (key, value) {
                    alert(key+' : ' + value );
                });
            }
        }

    })

}
function startQuestion() {
    //getting questions and answer from server:
    let takeExamByStudentCommand = {
        "examId": sessionStorage.getItem("examId")
    };

    jQuery.ajax({
       url:"http://localhost:9001"+"/test/take-test-by-student",
       type:"POST",
       contentType:"application/json",
       data:JSON.stringify(takeExamByStudentCommand),
       success:function (data) {
           if (data.valid) {
               questions=data.questions;
               time=data.time;
               setInterval(f,1000);
               //start question
               pos=0;
               renderQuestion();
           } else {
               $('#result-submit-response-by-student').html("Errors:");
               //Set error messages
               $.each(data.errorMessages, function (key, value) {
                   $('#result-submit-response-by-student').append('<span>'+key+' : ' + value + '</span>');
               });
           }
       }
    });

}

function _(x) {
    return $("#"+x);
}
function renderQuestion() {
    test=_("test");
    (_("test_status")).html("Question "+(pos+1)+" of "+questions.length);
    question=questions[pos];
    let content="<h3>"+question.content+"</h3>";
    if(question.type==="ChoiceQuestion") {
        let answers = question.answers;
        //todo:shuffle answers:

        for (let i = 0; i < answers.length; i++) {
            content += (i+1) + ". <input type='radio' name='choices' value='" + answers[i].id + "'>" + answers[i].content + "<br>";
        }
    }else{
        content+="Answer:\n"+
            "<input type='text' id='freeAnswer'>"
    }
    content+="<button class='btn  btn-info' onclick='saveAnswer()'>Submit Answer</button>";
    content+="<div>";
    if(pos>=1){
        content+="<button class='btn btn-success' onclick='goBefore()'>Previous</button>";
    }
    if(pos<questions.length-1){
        content+="<button class='btn btn-success' onclick='goNext()'>Next</button>";
    }

    content+="</div>";
    if(pos===(questions.length-1)){
        content+="<button class='btn btn-danger' onclick='finish()'> Finish </button>"
    }
    test.html(content)

}
function saveAnswer() {
    //save
    let answerId,freeAnswer;
    if(question.type==="ChoiceQuestion"){
        answerId=$("input[name='choices']:checked").val();
    }else {
        freeAnswer=$("#freeAnswer").val();
    }
    alert("FreeAnswer:"+freeAnswer);
    const questionId=questions[pos].id;
    const submitResponseByStudent={
        "answerId":answerId,
        "freeAnswer":freeAnswer,
        "questionId":questionId,
        "examId":sessionStorage.getItem("examId"),
        "time":time
    };
    jQuery.ajax({
        url:"http://localhost:9001"+"/test/submit-answer-by-student",
        type:"POST",
        contentType: "application/json",
        data: JSON.stringify(submitResponseByStudent),
        success:function (data) {
            let result=$("#result-submit-response-by-student");
            if(data.valid){
                result.html("successfully submitted");
            }else {
                $.each(data.errorMessages, function (key, value) {
                    result.after('<span class="error">' + value + '</span>');
                });
            }

        }
    })

}
function goBefore() {
    pos--;
    renderQuestion()
}
function goNext() {
    pos++;
    renderQuestion();
}
$("#exam-page-for-student").on("unload",function () {
    // set take exam time:
    setLeftTime();
});
function setLeftTime() {
    const leftTime=time+second/60;
    const setLeftTimeForExam={
        "leftTime":leftTime
    };
    jQuery.ajax({
        url:"http://localhost:9001"+"/test/set-leftTime-for-exam",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(setLeftTimeForExam),
        success:function (data) {
            if(data.valid){
                alert("Left Time saved successfully.")
            }else {
                $.each(data.errorMessages, function (key, value) {
                    alert("Error :"+value );
                });
            }
        }

    })
}