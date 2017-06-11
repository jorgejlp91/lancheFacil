/***/

function findSnacks() {
	$(".loader").show();
	$("#carousel-example-generic").hide();
	$.get("/lanchefacil/lanches/listAll", function(data) {		
		$.each(data.lanches, preencheHomeLanches);
		$(".loader").fadeOut("fast");
		$("#carousel-example-generic").carousel('prev')
		$("#carousel-example-generic").fadeIn(3000);
		
	}).fail(function() {
		$(".loader").fadeOut("fast");
		$("#errorModal").modal();
	});
}

function findIng() {
	$(".loader").show();
	$.get("/lanchefacil/ingredientes/findAll", function(data) {		
		$.each(data.ingredientes, montaTabelaIngredientes);
		$(".loader").fadeOut("fast");
				
	}).fail(function() {
		$(".loader").fadeOut("fast");
		$("#errorModal").modal();
	});
}

function clearSnackInfo(){
	$("#totalLanche").empty();
	$("#ingredientesSelecionados").empty();
	$('#promocoesContempladas').empty();
}

function calcularPromocao(){
	$(".loader").show();
	
	clearSnackInfo();
	var ingredientes = [];
	
	$('#tableIngrediente tr[id^="ing"]').each(function(){
		$this = $(this);
		var qtd = $this.find('input[type="text"]');
		if ($(qtd).val() > 0){
			var ingrediente = $this.data("ingrediente");
			ingrediente.quantidade = $(qtd).val();
			ingredientes.push(ingrediente);
		}
	});
	var postData = {
			"nome" : "monta",
			"ingredientes" : ingredientes
		};
	
	var ajaxPost = $.ajax({
		url : "/lanchefacil/lanches/promocoes",
		type : "POST",
		dataType : 'json',
		data : JSON.stringify(postData),
		contentType : "application/json"
	});

	ajaxPost.done(function(data) {
		
		exibePromocoes(data, ingredientes)
		$(".loader").fadeOut("fast");
		$("#btn_comprar").prop('disabled', false);

	});

	ajaxPost.fail(function() {
		$(".loader").fadeOut("fast");		
		$("#errorModal").modal();
	});
}
