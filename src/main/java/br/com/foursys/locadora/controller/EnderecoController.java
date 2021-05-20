package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.dao.EnderecoDAO;

/**
 * Classe para acessar o objeto Dao e efetuar altera��es e consultas na base de
 * dados
 * 
 * @author João Victor
 * @since 27/04/2021
 * @version 1.0
 */
public class EnderecoController {

	public void salvar(Endereco endereco) {
		try {
			new EnderecoDAO().salvar(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Endereco> buscarPorNome(String nome) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Endereco> retorno = new ArrayList<Endereco>();

		try {
			retorno = new EnderecoDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/*
	 * metodo para excluir um registro no bdd
	 */
	public void excluir(Endereco endereco) {
		try {
			new EnderecoDAO().excluir(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Endereco> buscarPorEstado(Estado estado) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Endereco> retorno = new ArrayList<Endereco>();

		try {
			retorno = new EnderecoDAO().buscarPorEstado(estado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
