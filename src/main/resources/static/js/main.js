$(document).ready(function () {

    $(".slider").bxSlider({
        infiniteLoop:false,
        hideControlOnEnd:true,
        pager:false
    });

    // $(window).resize(function () {
    //     const config = {
    //         infiniteLoop:false,
    //         // hideControlOnEnd:true,
    //         pager:false
    //     }
    //     const slider = []
    //     $(".slider").each(function (i, config){
    //         $(".slider").bxSlider(config);
    //     })
    //     $(slider).each(function (){
    //         $(this).reloadSlider(config);
    //     })
    // })

})