// $('#myModal').on('shown.bs.modal', function () {
//     $('#myInput').focus()
// })

// 設定輪播時間
$('.carousel').carousel({
    interval: 2000
});

// 設定裝置尺寸變換
$(function () {
    $(window).on("resize", function () {
        let deviceW = $(window).width();
        //console.log(deviceW);

        let isShowBigImage = deviceW >= 800;

        let $allItems = $('#mv_carousel .item');
        //console.log($allItems);

        $allItems.each(function (index, item) {
            let src = isShowBigImage ? $(item).data("lg-img") : $(item).data("sm-img");
            let imgUrl = 'url("' + src + '")';

            $(item).css({
                backgroundImage: imgUrl
            });

            if (!isShowBigImage) {
                let $img = "<img src='" + src + "'>";
                $(item).empty().append($img);
            } else {
                $(item).empty();
            }
        });
    });

    // 提示框
    $('[data-toggle="tooltip"]').tooltip();

    // 頁籤寬度處理
    $(window).on("resize", function () {
        let $ul = $("#movieTime .nav");
        let $allLis = $("[role='presentation']", $ul);
        //console.log($allLis);


        let totalW = 0;
        $allLis.each(function (index, item) {
            totalW += $(item).width();
        });
        //console.log(totalW);

        let parentW = $ul.parent().width();

        if (totalW > parentW) {
            $ul.css({
                width: totalW + "px"
            })
        } else {
            $ul.removeAttr("style");
        }
    });

    // 導航列滾動處理
    let allLis = $("#lk_nav li");

    $(allLis[1]).on("click", function () {
        $("html,body").animate({
            scrollTop: $("#movieTime").offset().top
        }, 1000);
    });

    $(allLis[2]).on("click", function () {
        $("html,body").animate({
            scrollTop: $("#mv_hot").offset().top
        }, 1000);
    });

    $(allLis[3]).on("click", function () {
        $("html,body").animate({
            scrollTop: $("#mv_about").offset().top
        }, 1000);
    });

    $(allLis[4]).on("click", function () {
        $("html,body").animate({
            scrollTop: $("#mv_link").offset().top
        }, 1000);
    });
    
    $(window).trigger("resize");

});