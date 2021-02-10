$(document).ready(function (){
    const authFrom = $("#authFrom");
    $(this).on("keyup", function () {
        if ($("#id").val() === "" || $("#pw").val() === "") {
            $("#btnSubmit").attr('disabled', true);
        }  else {
            $("#btnSubmit").removeAttr('disabled', false);
        }
    });

    $(this).on("keyup", function () {
        if ($("#id").val() === "" || $("#pw").val() === "" || $("#name").val() === "" || $("#fullname").val() === "") {
            $("#auth_btn").attr('disabled', true);
        }  else {
            $("#auth_btn").removeAttr('disabled', false);
        }
    });

    $("#joinform").submit(function () {
        const emailRule = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
        const passRule = RegExp(/^[A-Za-z0-9]{8,16}$/);

        if (!emailRule.test($('#id').val())) {
            $(".error1").empty();
            $(".error1").append("Email doesn't matches");
            $("#id").val('');
            return false;
        }
        if (!passRule.test($("#pw").val())){
            $(".error2").empty();
            $(".error2").append("Please enter at least 8 characters and no more than 16 characters");
            $("#pw").val('');
            return false;
        }

    })
})
