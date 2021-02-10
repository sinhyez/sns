$(function () {

    $(".slider").bxSlider({
        infiniteLoop:false,
        hideControlOnEnd:true,
        pager:false
    });

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

});

function savePosting() {

    const data = new FormData($("#postform")[0]);
    const valid = $(".form_caption").val();
    const file = $(".upload")[0].files;

    if (valid.length > 300) {
        alert("[POSTING ERROR] Please enter less than 300 characters.");
        return false;
    }
    if (file.length > 5) {
        alert("[POSTING ERROR]You can insert up to 5 images.");
        return false;
    }
    else {
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
                    location.reload();
                }
            },
            error: function (request, status, error) {
                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            }
        });
    }
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
                location.reload();
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
            location.replace("/mian/user/");
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function setThumbnail(e) {

    var selFile = [];
    var file = e.target.files;
    let fileArr = Array.prototype.slice.call(file);
    let index = 0;

    $("#image_container").empty();

    fileArr.forEach(function (f) {
        selFile.push(f);

        const reader = new FileReader();
        reader.onload = function(e) {
            const img = document.createElement("img");
            img.setAttribute("src", e.target.result);
            document.querySelector("#image_container").appendChild(img);
            index++;
        }
        reader.readAsDataURL(f);
    });

}