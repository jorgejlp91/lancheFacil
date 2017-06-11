/**
 * 
 */

function preencheHomeLanches(i, lanche) {
	var active = "";
	if (i == 0){
		active = "active";
	} 
	$('#indicador-carrossel').append($('<li>', {
		'data-target' : "#carousel-example-generic",
		'data-slide-to' : i,
		'class' : active
	}));
	$('#img-carrossel').append($('<div class="item '+active+'"><img class="slide-image" src="http://placehold.it/800x300" alt=""></div>'));
	
	$('#div-lanches').prepend($('<div id="lanche'+i+'" class="col-sm-4 col-lg-4 col-md-4">'));
	
	$('#lanche'+i).append($('<div id="thumbnail'+i+'" class="thumbnail">'));
	$('#thumbnail'+i).append($('<img src="http://placehold.it/320x150" alt="">'));
	$('#thumbnail'+i).append($('<div class="caption">'));
	$('#thumbnail'+i+'>div').append($('<h4 class="pull-right">R$'+lanche.valorTotal+'</h4>'));
	$('#thumbnail'+i+'>div').append($('<h4><a onclick="$(\'#myModal\').modal();">'+lanche.nome+'</a></h4>'));
	$('#thumbnail'+i+'>div').append($('<b>Ingredientes</b>'));
    
	$.each(lanche.ingredientes,
			function(j, ingrediente) {
			$('#thumbnail'+i+'>div').append($('<li>'+ingrediente.nome+'</li>'));
			
		});
		
}