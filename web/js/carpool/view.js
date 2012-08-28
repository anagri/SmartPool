$(document).ready(function(){
    $('.hiddenContent').hide();
    $("#moreDetailsButton").click(function(){
        var hiddenContent = $('.hiddenContent')
        if(hiddenContent.is(':visible')){
            hiddenContent.hide();
            this.innerHTML = "More Details"
        }
        else{
            hiddenContent.show();
            this.innerHTML="Less Details"
        }

    });
})