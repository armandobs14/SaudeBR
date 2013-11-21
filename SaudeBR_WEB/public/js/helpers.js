$(document).ready(function(){
    $(".stContainer").hide();
    $("table input").keyup(function(){
        var table = $(this).parents('table');
        var index = $(this).parent().index();
        var nth = $(table).find("td:nth-child("+(index+1).toString()+")");
        var valor = $(this).val().toUpperCase();
        $(table).find("tbody tr").show();
        $(nth).each(function(){
            if($(this).text().toUpperCase().indexOf(valor) < 0){
            $(this).parent().hide();
           }
        });
        
    });
    $("table input").blur(function(){
        $(this).val("");
    });

    cityCounter = 0;
    $("#cityView").click(function(){
        if(cityCounter == 0){
            $(this).html('Esconder Cidades');
            cityCounter = 1;
        }else{
            $(this).html('Visualisar Cidades');
            cityCounter = 0;
        }
        $("#cityCotnainer").toggle();
    });

    ubsCounter = 0;
    $("#ubsView").click(function(){
        if(ubsCounter == 0){
            $(this).html('Esconder UBS');
            ubsCounter = 1;
        }else{
            $(this).html('Visualisar UBS');
            ubsCounter = 0;
        }
        $("#ubsContainer").toggle();
    });
});
