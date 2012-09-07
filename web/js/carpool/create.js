$(document).ready(function(){
    var cabTypeBox = $("select[name=cabType]");
    var capacityBox = $("#capacity")
    cabTypeBox.change(function(){
        if (this.children[this.selectedIndex].value == "PERSONAL")
            capacityBox.show();
        else
            capacityBox.hide();
    })
});