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

function setThumbnail(event) {
    var reader = new FileReader();

    reader.onload = function(event) {
        var img = document.createElement("img");
        img.setAttribute("src", event.target.result);
        document.querySelector("#image_container").appendChild(img);
    };
    reader.readAsDataURL(event.target.files[0]);
}