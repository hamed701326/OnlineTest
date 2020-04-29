$("#list-free-question-by-master").ready(function () {
    receiveQuestionAnswer()
});
var result=$("#correct-question-result-div");
var pos=0;
var questions;
var question;
function receiveQuestionAnswer() {
    const listQuestionAndAnswerByMasterCommand={
        "takeExamId":sessionStorage.getItem("takeExamId")
    };
    jQuery.ajax({
        url:"http://localhost:9001"+"/test/list-question-and-answer-by-master",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(listQuestionAndAnswerByMasterCommand),
        success:function (data) {
                if (data.valid){
                    questions=data.questionAndAnswers;
                    if(questions.length>0) {
                        renderQuestionAndAnswer(questions);
                    }else{

                        result.css("color","red");
                        result.html(
                            "The Exam is verified, There's nothing to correct."
                        )
                    }
                }else{
                    alert(
                        "Error:\n" +
                    $.each(errorMessage, function (key, value) {

                            key+":"+value+"\n"

                    })
                    )
                }

        },
        error:function (errorMessage) {
            alert(errorMessage.responseJSON.message);
        }
        
    })
}
function renderQuestionAndAnswer(questions) {
    $("#question-and-answer-div").show();
    question=questions[pos];
    sessionStorage.setItem("takeAnswerId",question.takeAnswerId);
    let studentAnswer=question.studentAnswer;
    let masterAnswer=question.masterAnswer;
    $("#counterQuestion").show();
    $("#counterQuestion").val(
      "Question "+(pos+1)+" of "+questions.length
    );
    $("#question").val(question.questionContent);
    $("#student-answer").val(studentAnswer);
    $("#master-answer").val(masterAnswer);


}
function addScoreToExam() {
    const correctFreeQuestionByMasterCommand={
        "takeExamId":sessionStorage.getItem("takeExamId"),
        "takeAnswerId":sessionStorage.getItem("takeAnswerId"),
        "score":$("#score-field").val()
    };
    jQuery.ajax({
        url:"http://localhost:9001"+"/test/correct-free-question-by-master",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(correctFreeQuestionByMasterCommand),
        success:function (data) {
            if (data.valid){
                result.css("color","green");
                result.html(
                    "changes saved"
                );
            }else{
                let content="Error:\n" ;
                $.each(data.errorMessages, function (key, value) {
                       content+= key+":"+value;
                });
                alert(content);
            }

        },
        error:function (errorMessage) {
            $.each(errorMessage, function (key, value) {
                alert(
                    "Error:\n" +
                    key+":"+value
                )
            })
        }

    })
}
function goPrivies() {
    pos--;
    if (pos<0){
        pos=questions.length-1;
    }
    renderQuestionAndAnswer(questions);
}
function goNext() {
    pos++;
    if(pos===questions.length){
        pos=0;
    }
    receiveQuestionAnswer(questions)

}
function  finish() {
    const finishCorrectionByMasterCommand={
        "takeExamId":sessionStorage.getItem("takeExamId")
    };
    jQuery.ajax({
        url:"http://localhost:9001"+"/test/finish-correction-by-master",
        type:"POST",
        contentType:"application/json",
        data:JSON.stringify(finishCorrectionByMasterCommand),
        success:function (data) {
            if (data.valid){
                result.css("color","green");
                result.html(
                    "The Test Corrected by master."
                );
            }else{
                let content="Error:\n" ;
                $.each(data.errorMessages, function (key, value) {
                    content+= key+":"+value;
                });
                alert(content);
            }

        },
        error:function (errorMessage) {
            $.each(errorMessage, function (key, value) {
                alert(
                    "Error:\n" +
                    key+":"+value
                )
            })
        }

    });
    $('#result-for-now-exam-by-master')
                .load("features/exam-management/list-participates-in-exam-by-master/list-participates-in-exam-by-master.html")
}