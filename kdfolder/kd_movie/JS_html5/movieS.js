// $(function(){
//   $("ul.subs").hide(); //隱藏子選單
//   $(".main").click(function(){
//     $("ul.subs").hide();
//     if($("+ul",this).css("display")=="none"){
//       $("+ul",this).show();
//     }
//   });
//   //點擊主選單(.main) 隱藏子選單(ul.subs) 如果被點擊的這個元素相鄰的ul display=none 顯示相鄰的ul
// });


// $(function(){
//   $("ul.subs").hide();
//   $(".main").click(function(){
//     $("ul.subs").slideUp(); //向上滑動隱藏
//     if($("+ul",this).css("display")=="none"){
//       $("+ul",this).slideDown();//向下滑動顯示
//     }
//   });
//   //slideUp()、slideDown()、可透過參數控制滑動速度，fast、normal、slow，以毫秒為單位設定，預設值為nomal。
// });


// $(function(){
//   $("ul.subs").hide();
//   $(".main").click(function(){
//     if($("+ul",this).css("display")=="none"){
//       $("+ul",this).slideDown();
//     }else{
//       $("+ul",this).slideUp();
//     };
//   });
//   //點擊主選單(.main) 如果被點擊的這個元素相鄰的ul display=none 則向下滑動出相鄰的ul 否則向上滑動相鄰的ul。
// });


// $(function(){
//   $("ul.subs").hide();
//   $(".main").click(function(){
//     $("ul.subs").slideUp();
//     $(".main").removeClass("open"); //移除在(.main)的class屬性(open)
//     if($("+ul",this).css("display")=="none"){
//       $("+ul",this).slideDown();
//       $(this).addClass("open"); //被點擊的元素新增一組class(open)
//     }
//   });
//   //點擊主選單(.main) 子選單(ul.subs)向上滑動隱藏，移除主選單(.main)的class(open)，如果被點擊的這個元素相鄰的ul display=none 則向下滑動出相鄰的ul並新稱class(open)。
// });

$(function(){
  $("ul.subs").hide(); //子選單(ul.subs)隱藏
  $(".main").click(function(){
    $("ul.subs").slideUp();
    $(".main").removeClass("open"); //移除在(.main)的class屬性(open)
    if($("+ul",this).css("display")=="none"){
      $("+ul",this).slideDown();
      $(this).addClass("open"); //被點擊的元素新增一組class(open)
    }
  }).mouseover(function(){
    $(this).addClass("rollover") //滑鼠移入這個元素時新增class(rollover)
  }).mouseout(function(){
    $(this).removeClass("rollover") //滑鼠移開這個元素時移除class(rollover)
  });
  //點擊主選單(.main) 子選單(ul.subs)向上滑動隱藏，移除主選單(.main)的class(open)，如果被點擊的這個元素相鄰的ul display=none 則向下滑動出相鄰的ul並新稱class(open)。
});
