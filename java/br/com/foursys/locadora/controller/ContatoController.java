package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.dao.ContatoDAO;

/**
 * Classe para acessar o objeto Dao e efetuar altera��es e consultas na base de
 * dados
 * 
 * @author João Victor
 * @since 27 de abr. de 2021
 * @version 1.0
 */
public class ContatoController {

	public void salvar(Contato contato) {
		try {
			new ContatoDAO().salvar(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Contato> buscarPorNome(String nome) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Contato> retorno = new ArrayList<Contato>();

		try {
			retorno = new ContatoDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/*
	 * metodo para excluir um registro no bdd
	 */
	public void excluir(Contato contato) {
		try {
			new ContatoDAO().excluir(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Contato> buscarPorEstado(Estado estado) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Contato> retorno = new ArrayList<Contato>();

		try {
			retorno = new ContatoDAO().buscarPorEstado(estado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
