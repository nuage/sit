$(document).ready(function(){
    $("input:submit, a, button").button();
    $("#resizable").resizable({
            handles: "se"
    });
    $("form.answer").submit(function(event) {
        event.preventDefault();
        
        var data = "";
        $.each($(this).serializeArray(), function(i, field) {
            data += field.name + "=" + field.value + "&";
        });
        
        var url = this.action;
        var cont = $(this).siblings(".childs");
        $.post(url, data, function(result) {
            var c = cont.prepend(result).children(":first-child");
            c.find("input:submit").button();
            c.slideDown('slow', function() {
                $(this).removeClass("ui-state-highlight");
            });
            
        });
        
    });
});