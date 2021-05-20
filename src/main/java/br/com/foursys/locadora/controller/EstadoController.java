package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.dao.EstadoDAO;

/**
 * Classe para acessar o objeto Dao e efetuar altera��es e consultas na base de
 * dados
 * 
 * @author João Victor
 * @since 27 de abr. de 2021
 * @version 1.0
 */
public class EstadoController {

	public void salvar(Estado estado) {
		try {
			new EstadoDAO().salvar(estado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Estado> buscarPorNome(String nome) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Estado> retorno = new ArrayList<Estado>();

		try {
			retorno = new EstadoDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/*
	 * metodo para excluir um registro no bdd
	 */
	public void excluir(Estado estado) {
		try {
			new EstadoDAO().excluir(estado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Estado> buscarTodos() throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Estado> retorno = new ArrayList<Estado>();

		try {
			retorno = new EstadoDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
