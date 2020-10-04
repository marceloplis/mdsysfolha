function isIE(){
	return (window.navigator.appName).indexOf('Explorer') > 0;
}


//Formata Gen�rico passando a m�scara como par�metro
function formatarGenerico(src, mask){
	var i = src.value.length;
	var saida = mask.substring(0,1);
	var texto = mask.substring(i);
	if (texto.substring(0,1) != saida){
		src.value += texto.substring(0,1);
	}
}

//valida telefone
function ValidaTelefone(tel){
	exp = /\(\d{2}\)\d{4}\-\d{4}/;
	exp9= /\(\d{2}\)\d{5}\-\d{4}/;
	if(tel.value.length > 0 && !exp.test(tel.value) && !exp9.test(tel.value)){
		alert('Numero de Telefone Invalido!');
		tel.focus();
	}
}

//valida CEP
function ValidaCep(cep){
	exp = /\d{5}\-\d{3}/;
	if(cep.value.length > 0 && !exp.test(cep.value)){
		alert('Numero de Cep Invalido!');   
		cep.focus();
	}
}

//valida data
function ValidaData(data){
	exp = /\d{2}\/\d{2}\/\d{4}/;
	if(data.value.length > 0 && !exp.test(data.value)){
		alert('Data Invalida!');       
		data.focus();
	}
}

//valida Horario
function ValidaHorario(hora){
	exp = /\d{2}\:\d{2}/;
	if(hora.value.length > 0 && !exp.test(hora.value)){
		alert('Horário Invalido!');   
		hora.focus();
	}
}

//valida se � n�mero
function somente_numero(campo){  
	var digits="0123456789."; 
	var campo_temp;   
	for (var i=0;i<campo.value.length;i++){  
		campo_temp=campo.value.substring(i,i+1);   
		if (digits.indexOf(campo_temp)==-1){  
			campo.value = campo.value.substring(0,i);  
		}  
	}  
}

/**
 * Aplica a mascara para o campo desejado.
 * Detalhe : O formato da mascara e de livre escolha.
 * 		A mascara deve ser composta pelo n�mero 9 para desiginar algarismos numericos
 * 		A mascara deve ser composta pela letra X para desiginar algarismos alfanumericos
 * Exemplos: 9999-X,(99)9999-9999,999.999.999-99,XXXX-XXX
 * Uso: no evento onkeyup="aplicarMascara(this,'9999-X')"
 */
function aplicarMascara(campo, mascara) {

	campo.maxLength = mascara.length;
	mascara = mascara.toUpperCase();

	var digitoObrigatorio = mascara.replace('?', '');

	if (campo.value.length <= digitoObrigatorio.length) {
		mascara = digitoObrigatorio;
	}
	else {
		var novaMascara = "";
		for (var i = 0; i < mascara.length; i++) {
			if (mascara.charAt(i) == '?') {
				novaMascara += '9';
			}
			else {
				novaMascara += mascara.charAt(i);
			}
		}

		mascara = novaMascara;
	}

	var valor = campo.value;
	var resultado = "";
	// Remove todo caractere que nao seja numero e letras
	for(i = 0 ; i < mascara.length ; i++){
		if(mascara.substr(i,1) != 'X' && mascara.substr(i,1) != '9'){
			valor = valor.replace(mascara.substr(i,1),'');
		}
	}
	// Valor contem os dados em branco
	var iValor = 0;
	for(i = 0 ; i < mascara.length; i++){
		var itemMascara = mascara.substr(i,1);
		var itemValor = valor.substr(iValor,1);
		if(itemValor != ''){
			if(itemMascara != 'X' && itemMascara != '9'){
				// Se a mascara nao disser que e numero ou letra
				// Atualiza com o conteudo da mascara
				resultado = resultado + itemMascara;
				iValor--;
			}
			if(itemMascara == 'X'  && getTipo(itemValor)  == 2){
				// Codigo correto para o campo
				// Atualiza no resultado
				resultado = resultado + itemValor;

			}else if(itemMascara == '9' && getTipo(itemValor) == 1){
				// Codigo correto para numero
				// Atualiza
				resultado = resultado + itemValor;
				//iValor++;
			}
			iValor++;
		}

	}
	if(campo.length < mascara.length){
		campoCompleto = false;
	}else{
		campoCompleto = true;
	}
	campo.value = resultado;
	return false;
}   

/**
 * Qualifica uma string seguindo as seguintes regras abaixo
 * Detalhe:   Retorna 1 para algarismos numericos
 *		 Retorna 2 para algarismos alfanumericos
 *		 Retorna 3 para caracteres de controel
 *		 Retorna 0 para outros caracteres
 * Ser� analisado a primeira posicao da string.
 * Uso: funcao auxiliar para outras funcoes
 */
function getTipo(valor){	
	var codigo = valor.charCodeAt(0);
	// codigos numeros
	if(codigo >= 48 && codigo <= 57){
		return 1;
	}
	// Codigos alfanumericos
	if((codigo >= 97 && codigo <= 122) || (codigo >= 65 && codigo <= 90)){
		return 2;
	}
	// Codigos de controle
	if (codigo >= 0 && codigo < 48){
		return 3;
	}
	return 0;
}


function formata_decimal(objTextBox, SeparadorMilesimo, SeparadorDecimal, e, maxlenght){

	if (objTextBox.value.length >= maxlenght) {
		return;
	}

	var key = '';
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var whichCode = 0;
	if (e.keyCode) whichCode = e.keyCode;      
	else if (e.which) whichCode = e.which; // Netscape 4.?  
	else if (e.charCode) whichCode = e.charCode; // Mozilla 

	if ((whichCode == 13) || (whichCode == 0) || (whichCode == 8))  {
		return true;
	}

	key = String.fromCharCode(whichCode); // Valor para o c�digo da Chave
	if (strCheck.indexOf(key) == -1) return false; // Chave inv�lida
	len = objTextBox.value.length;
	for(i = 0; i < len; i++)
		if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal)) break;
	aux = '';
	for(; i < len; i++)
		if (strCheck.indexOf(objTextBox.value.charAt(i))!=-1) aux += objTextBox.value.charAt(i);
	aux += key;
	len = aux.length;
	if (len == 0) objTextBox.value = '';
	if (len == 1) objTextBox.value = '0'+ SeparadorDecimal + '0' + aux;
	if (len == 2) objTextBox.value = '0'+ SeparadorDecimal + aux;
	if (len > 2) {
		aux2 = '';
		for (j = 0, i = len - 3; i >= 0; i--) {
			if (j == 3) {
				aux2 += SeparadorMilesimo;
				j = 0;
			}
			aux2 += aux.charAt(i);
			j++;
		}
		objTextBox.value = '';
		len2 = aux2.length;
		for (i = len2 - 1; i >= 0; i--)
			objTextBox.value += aux2.charAt(i);
		objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
	}
	return false;
}

//Valida CPF
function ValidaCpf(campo) {

	var pattern = new RegExp(eval("/" + "[^0-9]*" + "/g"));
	var valorAValidar = campo.value;
	valorAValidar= valorAValidar.replace(pattern, "");

	var CPF = valorAValidar; // Recebe o valor digitado no campo

	// Aqui come�a a checagem do CPF
	var POSICAO, I, SOMA, DV, DV_INFORMADO;
	var DIGITO = new Array(10);
	DV_INFORMADO = CPF.substr(9, 2); // Retira os dois �ltimos d�gitos do n�mero informado

	// Desemembra o n�mero do CPF na array DIGITO
	for (I=0; I<=8; I++) {
		DIGITO[I] = CPF.substr( I, 1);
	}

	// Calcula o valor do 10� d�gito da verifica��o
	POSICAO = 10;
	SOMA = 0;
	for (I=0; I<=8; I++) {
		SOMA = SOMA + DIGITO[I] * POSICAO;
		POSICAO = POSICAO - 1;
	}
	DIGITO[9] = SOMA % 11;
	if (DIGITO[9] < 2) {
		DIGITO[9] = 0;
	}
	else{
		DIGITO[9] = 11 - DIGITO[9];
	}

	// Calcula o valor do 11� d�gito da verifica��o
	POSICAO = 11;
	SOMA = 0;
	for (I=0; I<=9; I++) {
		SOMA = SOMA + DIGITO[I] * POSICAO;
		POSICAO = POSICAO - 1;
	}
	DIGITO[10] = SOMA % 11;
	if (DIGITO[10] < 2) {
		DIGITO[10] = 0;
	}
	else {
		DIGITO[10] = 11 - DIGITO[10];
	}

	// Verifica se os valores dos d�gitos verificadores conferem
	DV = DIGITO[9] * 10 + DIGITO[10];
	if (DV != DV_INFORMADO) {
		alert('CPF inválido');
		campo.value = '';
		campo.focus();
		return false;
	} 

}


function openTab(evt, cityName) {
	// Declare all variables
	var i, tabcontent, tablinks;

	// Get all elements with class="tabcontent" and hide them
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Get all elements with class="tablinks" and remove the class "active"
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}

	// Show the current tab, and add an "active" class to the button that opened the tab
	document.getElementById(cityName).style.display = "block";
	evt.currentTarget.className += " active";
}


