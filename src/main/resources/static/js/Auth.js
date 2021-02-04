$(function () {
    $("#authForm").on("keyup", function () {
        if ($("#id").val() === "" || $("#pw").val() === "") {
            $("#btnSubmit").attr('disabled', true);
        }  else {
            $("#btnSubmit").removeAttr('disabled', false);
        }
    });
})

