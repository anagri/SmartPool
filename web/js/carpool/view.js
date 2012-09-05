$(document).ready(function(){
    $('.hiddenContent').hide();
    setClickEventOnMoreDetailsButton();
    shadeTable();
});

function setClickEventOnMoreDetailsButton(){
    $(".moreDetailsButton").click(function(){
        var hiddenContent = $('.hiddenContent');
        if(hiddenContent.is(':visible')){
            hiddenContent.hide("slow");
            $(this).html("More Details");
        }else{
            hiddenContent.show("slow");
            $(this).html("Less Details");
        }
    });
}

function shadeTable(){
    $("table").find("tbody tr").each(function(index) {
        if(index % 2 == 1){
            $(this).css("background", "#ccc");
        }
    });
}