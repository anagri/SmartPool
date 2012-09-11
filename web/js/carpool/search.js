function shadeTable(){
    $("table").find("tbody tr").each(function(index) {
        if(index % 2 == 1){
            $(this).css("background", "#ccc");
        }
    });
}

$(document).ready(function(){
    shadeTable();
});