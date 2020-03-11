$("#edit-user-by-admin").ready(function () {
    $("#edit-user-by-admin").modal('show');
});

function submitIncidentByEmployee() {
    const issue = $("#issue-field").val();
    const description = $("#description-field").val();
    const userId = 1;
    const submitIncidentByEmployeeCommand = {"issue": issue, "description": description, "userId": userId};

    jQuery.ajax({
        url: "http://localhost:9001/incident/submit-incident-by-employee",
        type: "POST",
        data: JSON.stringify(submitIncidentByEmployeeCommand),
        contentType: "application/json; charset=utf-8",
        success: function (data, textStatus, jQxhr) {
            $("#edit-user-by-admin").modal('hide');
            listIncidentByEmployee();
        },
        error: function (errorMessage) {
            alert(errorMessage)
        }
    });
}
