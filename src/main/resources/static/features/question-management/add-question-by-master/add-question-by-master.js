var html='<tr>\n' +
    '            <td>\n' +
    '                <input type="text" name="name" required> \n' +
    '            </td>\n' +
    '            <td>\n' +
    '                <button class="remove">-</button>\n' +
    '            </td>\n' +
    '        </tr>';
$("input[name='type']").change(function () {
    let type = $("input[name='type']:checked").val();
    if(type==="Choice Question"){
        $('#div-free').hide();
        $('#div-choice').show();
    }
    if(type==="Free Question"){
        $('#div-choice').hide();
        $('#div-free').show();
    }
});
$('#addRow').click(function () {
    $('.option').append(html);
});
$(document).on('click','.remove',function () {
    $(this).parents('tr').remove();
});


function submit() {
    var values=[];
    $('input[name="name"]').each(function (i, elem) {
        values.push(
            {
                'content':$(elem).val(),
                'correct':(i===0)
            });
    });
    const addQuestionByMasterCommand={
        'type':$("input[name='type']:checked").val(),
        'title':$('#title').val(),
        'content':$('#content').val(),
        'score':$('#score').val(),
        'answers':values,
        'answer':$('#answer').val(),
        'examId':sessionStorage.getItem("testId")
    };
    jQuery.ajax({
        url: "http://localhost:9001/question/add-question-by-master",
        type: "POST",
        data: JSON.stringify(addQuestionByMasterCommand),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            var result=$("#result");
            if(data.validated){
                result.css("color:green");
                result.html("this question added successfully");
                $(".container").reset();
            }else{
                //Set error messages
                $.each(data.errorMessage, function (key, value) {
                    // $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                    result.css("color:red");
                    result.append("name:"+key+"\n"+value)
                });
            }
        },
        error: function (errorMessage) {
            // alert(errorMessage)
            $.each(errorMessage, function (key, value) {
                $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
            });
        }
    });
}