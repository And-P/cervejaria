Brewer = Brewer || {};

Brewer.Autocomplete = (function() {
	
	function Autocomplete() {
		this.skuOuNomeInput = $('.js-sku-nome-cerveja-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-cerveja').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete); 
		
	}
	
	Autocomplete.prototype.iniciar = function(){
		var options = {
			url: function(skuOuNome){
				return '/cervejaria/cervejas?skuOuNome=' + skuOuNome;
			},
			getValue: 'nome',
			minCharNumber: 2,
			requestDelay: 300,
			maxNumberOfElements: 3,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template:{
				type: 'custom',
				method: function(nome, cerveja){
					
					cerveja.valorFormatado = Brewer.formatarMoeda(cerveja.valor);
					
					return this.template(cerveja);
				}.bind(this)
			}
		};
		
		this.skuOuNomeInput.easyAutocomplete(options);
		
		
	}
	
	return Autocomplete
	
}());

$(function (){
	var autocomplete = new Brewer.Autocomplete();
	autocomplete.iniciar();
})

