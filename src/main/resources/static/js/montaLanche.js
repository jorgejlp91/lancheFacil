/**
 *  Funcionalidades da tela de montar Lanche
 *  Monta a tabela de ingredientes e calcula o preço do lanche verificando
 *  se está contemplado em alguma promoção
 */


function montaTabelaIngredientes(i, ingrediente) {
	
	$('#tableIngrediente>tbody').append($('<tr id="ing'+ingrediente.id+'">'));
	$('#ing'+ingrediente.id).data("ingrediente",ingrediente);
	$('#ing'+ingrediente.id).append($('<td id="nomeIng'+ingrediente.id+'"><span class="name">'+ingrediente.nome+'</span></td>'));
	$('#ing'+ingrediente.id).append($('<td id="valorIng'+ingrediente.id+'" class="hidden-phone">R$'+ingrediente.valorFormatado+'</td>'));
	$('#ing'+ingrediente.id).append($('<td id="qtdIng'+ingrediente.id+'"class="hidden-phone"><input class="form-control numeric-input" id="input_qtd'+ingrediente.id+'" type="text"></td>'));
	$('#ing'+ingrediente.id).append($('<td><span id="tot'+ingrediente.id+'" class="name">R$0,00</span>'));
	$('#input_qtd'+ingrediente.id)
		.keyup(function() {
			var qtd = $(this).val();
			var val = ""+(ingrediente.valor * qtd).toFixed(2);
			$('#tot'+ingrediente.id).text('R$'+ val.replace(".",","));
		});

	$(".numeric-input").keypress(function (e){
		  var charCode = (e.which) ? e.which : e.keyCode;
		  if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		    return false;
		  }
		});
}

function exibePromocoes(data, ingredientes){
	
	$("#totalLanche").text('Valor total do Lanche: R$'+data.valorFinal);
	
	$("#ingredientesSelecionados").append($('<b>Ingredientes Selecionados</b>'));
	$.each(ingredientes, function(i, ingrediente){
		$("#ingredientesSelecionados").append($('<li>'+ingrediente.nome+' : '+ingrediente.quantidade+' unid</li>'));
	})
	
	if (data.promocoes){
		$('#promocoesContempladas').append($('<h2>Promoções Contempladas</h2>'));
		$.each(data.promocoes,
				function(i, promocao) {
					$('#promocoesContempladas').append($('<div id="promo'+i+'" class="col-lg-4 col-sm-6 text-center">'));
					$('#promo'+i).append($('<img class="img-circle img-responsive img-center" src="http://placehold.it/200x200" alt=""/>'));
					$('#promo'+i).append($('<h3>'+promocao.nome+'</h3><p>Desconto de R$'+promocao.valorDesconto+'</p>'));
					
			});
	}
}