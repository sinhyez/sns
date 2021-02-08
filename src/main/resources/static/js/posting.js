$(function () {

    const filetarget = $('.file_box .upload');

    filetarget.on('change', function() {
        let filename;

        if(window.FileReader) {
            filename = $(this)[0].files[0].name;
        } else {
            filename = $(this).val().split('/').pop().split('\\').pop();
        }

        $(this).siblings('.upload_name').val(filename);
    });

    $('.posting').on("keyup", function () {
        if ($('.form_caption').val() === "" || !$(".upload").val()) {
            $(".post_btn").attr('disabled', true);
        }  else {
            $(".post_btn").removeAttr('disabled', false);
        }
    });

})

function savePosting() {

    const data = new FormData($("#postform")[0]);
    $.ajax({
        url : '/upload',
        method : 'post',
        data : data,
        enctype : 'multipart/form-data',
        async : false,
        contentType : false,
        processData : false,
        success : function (data) {
            if (data === "ok") {
                location.href;
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function saveUserImg(userid) {
    const data = new FormData($("#edit_form")[0]);
    $.ajax({
        url : '/user/img_insert/'+userid,
        type : "put",
        data : data,
        enctype : "multiple/form-data",
        async : false,
        contentType : false,
        processData : false,
        success : function (data) {
            if (data === "ok") {
                location.href;
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function deleteUserImg(userid) {
    const data = $("#delete_form").serialize();
    $.ajax({
        url : '/user/deleteimg/'+userid,
        type : "POST",
        data : data,
        enctype : "multiple/form-data",
        async : false,
        contentType : false,
        processData : false,
        success : function () {
            location.href;
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function setThumbnail(event) {
    const reader = new FileReader();

    reader.onload = function(event) {
        const img = document.createElement("img");
        img.setAttribute("src", event.target.result);
        document.querySelector("#image_container").appendChild(img);
    };
    reader.readAsDataURL(event.target.files[0]);
}