$(document).ready(function () {

    $(".slider").bxSlider({
        infiniteLoop:false,
        hideControlOnEnd:true,
        pager:false
    });

    $('.photo_caption').each(function(){
        const content = $(this).children('.caption');
        const contentTxt = content.text();
        const contentTxtShort = contentTxt.substring(0,30)+"...";
        const btnMore = $('<a href="javascript:void(0)" class="more"> read more</a>');

        $(this).append(btnMore);

        if(contentTxt.length >= 30){
            content.html(contentTxtShort);

        }else{
            btnMore.hide();
        }
        btnMore.click(toggle_content);

        function toggle_content(){
            if($(this).hasClass('short')){
                // 접기 상태
                $(this).html('more');
                content.html(contentTxtShort);
                $(this).removeClass('short');
            }else{
                // 더보기 상태
                $(this).html('less');
                content.html(contentTxt);
                $(this).addClass('short');
            }
        }
    });

    $('.posting_caption').each(function(){
        const content = $(this).children('span');
        const contentTxt = content.text();
        const contentTxtShort = contentTxt.substring(0,100)+"...";
        const btnMore = $('<a href="javascript:void(0)" class="more"> read more</a>');

        $(this).append(btnMore);

        if(contentTxt.length >= 100){
            content.html(contentTxtShort);

        }else{
            btnMore.hide();
        }
        btnMore.click(toggle_content);

        function toggle_content(){
            if($(this).hasClass('short')){
                // 접기 상태
                $(this).html('more');
                content.html(contentTxtShort);
                $(this).removeClass('short');
            }else{
                // 더보기 상태
                $(this).html('less');
                content.html(contentTxt);
                $(this).addClass('short');
            }
        }
    });

        $('.profile_intro').each(function(){
                const content = $(this).children('.intro');
                const contentTxt = content.text();
                const contentTxtShort = contentTxt.substring(0,200)+"...";
                const btnMore = $('<a href="javascript:void(0)" class="more"> read more</a>');

                $(this).append(btnMore);

                if(contentTxt.length >= 200){
                        content.html(contentTxtShort);

                }else{
                        btnMore.hide();
                }
                btnMore.click(toggle_content);

                function toggle_content(){
                        if($(this).hasClass('short')){
                                // 접기 상태
                                $(this).html('more');
                                content.html(contentTxtShort);
                                $(this).removeClass('short');
                        }else{
                                // 더보기 상태
                                $(this).html('less');
                                content.html(contentTxt);
                                $(this).addClass('short');
                        }
                }
        });

        $(".edt-btn").on("click", function () {
            if ($("#nick").val().length > 15) {
                $('.error-msg').empty();
                $('.error-msg').append("Please enter less than 15 characters.");
                return false;
            }
            if ($("#rname").val().length > 50) {
                $('.error-msg').empty();
                $('.error-msg').append("Please enter less than 50 characters.");
                return false
            }
        })

});

function deletePost(postid) {
    const page = '/deletePost/' + postid;
    $.ajax({
        url: page,
        method: "delete",
        data: postid,
        success: function (data) {
            if (data === "ok") location.replace("/main/user/");
        }
    })
}
