$(document).ready(function(){
    $('.hiddenContent').hide();
    setClickEventOnMoreDetailsButton();
    shadeOccupiedSeats();
    shadeAvailableSeats();
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

function shadeOccupiedSeats(){
    $("table").find("tbody tr.seat-occupied").each(function(index) {
        if(index % 2 == 1){
            $(this).css("background", "#ccc");
        }
    });
}

function shadeAvailableSeats(){
    $("table").find("tbody tr.seat-available").each(function(index) {
        if(index % 2 == 1){
            $(this).css("background", "#a0e5a0");
        }else{
            $(this).css("background", "#c0ffc0");
        }
    });
}