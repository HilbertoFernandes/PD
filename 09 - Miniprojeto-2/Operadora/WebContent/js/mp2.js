function concatenar() {
	var numero = document.getElementById('numeroDestino');
	var mensagem = document.getElementById('mensagem');
	var Resultado = numero.value + ":" + mensagem.value;
	document.getElementById("sendMessage").value = Resultado;
}



	function concatenarCliente() {
		var url = document.getElementById('url');
		var numeroCliente = document.getElementById('cliente');
		var Resultado = url.value + numeroCliente.value;
		document.getElementById("serverUrl").value = Resultado;
	}

	function somenteNumeros(num) {
		var er = /[^0-9.]/;
		er.lastIndex = 0;
		var campo = num;
		if (er.test(campo.value)) {
			campo.value = "";
		}
	}

	function inserirCredito() {
		document.getElementById("numeroDestino").value = 'creditar';	
		document.getElementById("mensagem").value = '';	
		document.getElementById('numeroDestino').style.display = "none"
		document.getElementById('numMensg').style.display = "none"
		document.getElementById('msg').style.display = "none"
		document.getElementById('valCred').style.display = "inline"
		document.getElementById('requestArea2').style.display = "none"
		document.getElementById('lbRequestArea1').style.display = "none"
		document.getElementById('lbRequestArea2').style.display = "inline" 
		document.getElementById('btCancelarCredito').style.display = "inline" 
	}
	
	function verSaldo() {
		document.getElementById('numeroDestino').style.display = "none";
		document.getElementById("numeroDestino").value = 'saldo';	
		document.getElementById('numMensg').style.display = "none";
		document.getElementById('msg').style.display = "none";
		document.getElementById('numSaldo').style.display = "inline";
		document.getElementById('requestArea2').style.display = "none";
		document.getElementById('lbRequestArea1').style.display = "none";
		document.getElementById('lbRequestArea2').style.display = "inline" ;
		document.getElementById('btCancelarSaldo').style.display = "inline" ;
		document.getElementById('mensagem').setAttribute('value', '');
		document.getElementById('mensagem').style.display = "none";  
	}
	
	
	function cancelarSaldo() {
		document.getElementById("mensagem").value = '';	
		document.getElementById("numeroDestino").value = '';	
		document.getElementById('numeroDestino').style.display = "inline"
		document.getElementById('numMensg').style.display = "inline"
		document.getElementById('msg').style.display = "inline"
		document.getElementById('numSaldo').style.display = "none" 
		document.getElementById('requestArea2').style.display = "inline"
		document.getElementById('lbRequestArea1').style.display = "inline"
		document.getElementById('lbRequestArea2').style.display = "none" 
		document.getElementById('btCancelarSaldo').style.display = "none" 
		document.getElementById('mensagem').style.display = "inline" 
	}
	
	
	function cancelarCredito() {
		document.getElementById("numeroDestino").value = '';	
		document.getElementById('numeroDestino').style.display = "inline"
		document.getElementById('numMensg').style.display = "inline"
		document.getElementById('msg').style.display = "inline"
		document.getElementById('valCred').style.display = "none" 
		document.getElementById('requestArea2').style.display = "inline"
		document.getElementById('lbRequestArea1').style.display = "inline"
		document.getElementById('lbRequestArea2').style.display = "none" 
		document.getElementById('btCancelarCredito').style.display = "none" 
			
	}