/**
 * Arquivo responsável por formatar as máscaras input
 */

function masc_cpf(objeto) {

	var retorno = "";
	var padrao = /\D/g;
	var numero;

	numero = objeto.value.replace(padrao, "");

	parte1 = numero.substr(0, 3);
	if (parte1.length > 0) {
		retorno = parte1;
	}

	parte2 = numero.substr(3, 3);
	if (parte2.length > 0) {
		retorno += "." + parte2;
	}

	parte3 = numero.substr(6, 3);
	if (parte3.length > 0) {
		retorno += "." + parte3;
	}

	parte4 = numero.substr(9, 2);
	if (parte4.length > 0) {
		retorno += "-" + parte4;
	}

	objeto.value = retorno;
}

function masc_rg(objeto) {

	var retorno = "";
	var padrao = /\D/g;
	var digito = /\W/g;
	var numero;

	numero = objeto.value.replace(padrao, "");
	numero1 = objeto.value.replace(digito, "");

	parte1 = numero.substr(0, 2);
	if (parte1.length > 0) {
		retorno = parte1;
	}

	parte2 = numero.substr(2, 3);
	if (parte2.length > 0) {
		retorno += "." + parte2;
	}

	parte3 = numero.substr(5, 3);
	if (parte3.length > 0) {
		retorno += "." + parte3;
	}

	parte4 = numero1.substr(8, 1);
	if (parte4.length > 0) {
		if (parte4 >= 0 || parte4 <= 9)
			retorno += "-" + parte4;
		else {
			retorno += "-X";
		}
	}

	objeto.value = retorno;
}
function masc_telefone(objeto) {

	var retorno = "";
	var padrao = /\D/g;
	var numero;

	numero = objeto.value.replace(padrao, "");

	parte1 = numero.substr(0, 2);
	if (parte1.length > 0) {
		retorno = "(" + parte1 + ")";
	}

	parte2 = numero.substr(2, 4);
	if (parte2.length > 0) {
		retorno += parte2;
	}

	parte3 = numero.substr(6, 4);
	if (parte3.length > 0) {
		retorno += "-" + parte3;
	}

	objeto.value = retorno;
}

function masc_celular(objeto) {

	var retorno = "";
	var padrao = /\D/g;
	var numero;

	numero = objeto.value.replace(padrao, "");

	parte1 = numero.substr(0, 2);
	if (parte1.length > 0) {
		retorno = "(" + parte1 + ")";
	}

	parte2 = numero.substr(2, 5);
	if (parte2.length > 0) {
		retorno += parte2;
	}

	parte3 = numero.substr(7, 4);
	if (parte3.length > 0) {
		retorno += "-" + parte3;
	}

	objeto.value = retorno;
}

function masc_cep(objeto) {

	var retorno = "";
	var padrao = /\D/g;
	var numero;

	numero = objeto.value.replace(padrao, "");

	parte1 = numero.substr(0, 5);
	if (parte1.length > 0) {
		retorno = parte1;
	}

	parte2 = numero.substr(5, 4);
	if (parte2.length > 0) {
		retorno += "-" + parte2;
	}

	objeto.value = retorno;
}

function masc_num(objeto) {

	var retorno = "";
	var padrao = /\D/g;
	var numero;

	numero = objeto.value.replace(padrao, "");

	parte1 = numero.substr(0, 5);
	if (parte1.length > 0) {
		retorno = parte1;
	}

	objeto.value = retorno;
}
