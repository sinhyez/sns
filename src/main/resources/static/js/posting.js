$(function () {

    const filetarget = $('.file_box .upload');

    filetarget.on('change', function() {
        if(window.FileReader) {
            var filename = $(this)[0].files[0].name;
        } else {
            var filename = $(this).val().split('/').pop().split('\\').pop();
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

function postEdit(postid) {

}

function savePosting() {

    const data = new FormData($("#postform")[0]);

    $.ajax({
        url : '/upload',
        method : "POST",
        data : data,
        enctype : "multiple/form-data",
        contentType : false,
        processData : false,
        success : function () {
            location.reload()
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function saveUserImg(userid) {
    const data = new FormData($("#edit_form")[0]);
    $.ajax({
        url : '/ipro/user/img_insert/'+userid,
        method : "POST",
        data : data,
        enctype : "multiple/form-data",
        contentType : false,
        processData : false,
        success : function () {
            location.reload();
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
        method : "POST",
        data : data,
        enctype : "multiple/form-data",
        contentType : false,
        processData : false,
        success : function () {
            location.reload();
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function setThumbnail(event) {
    var reader = new FileReader();

    reader.onload = function(event) {
        var img = document.createElement("img");
        img.setAttribute("src", event.target.result);
        document.querySelector("#image_container").appendChild(img);
    };
    reader.readAsDataURL(event.target.files[0]);
}