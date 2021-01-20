$(document).ready(function() {
    var filetarget = $('.file_box .upload');

    filetarget.on('change', function() {
        if(window.FileReader) {
            var filename = $this[0].files[0].name;
        } else {
            var filename = $(this).val().split('/').pop().split('\\').pop();
        }

        $(this).siblings('.upload_name').val(filename);
    });
});