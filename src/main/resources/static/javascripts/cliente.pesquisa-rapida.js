Brewer = Brewer || {}; 

Brewer.PesquisaRapidaCliente = (function(){
	
	function PesquisaRapidaCliente(){
		
		this.pesquisaRapidaClienteModal = $('#pesquisaRapidaClientes');
		
	}
	
	PesquisaRapidaCliente.prototype.iniciar = function(){
		
		$.ajax({
			url: this.pesquisaRapidaClienteModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data:{
				nome: 'Arnold'
			}
		});
		
	}
	
	return PesquisaRapidaCliente; 
	
	
	
}());

$(function(){
	var pesquisaRapidaCliente = new Brewer.PesquisaRapidaCliente(); 
	pesquisaRapidaCliente.iniciar(); 
	
});