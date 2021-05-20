package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.dao.ClienteDAO;

/**
 * Classe responsável por acessar o objeto DAO e efetuar consulta na base de
 * dados
 * 
 * @author João Victor
 * @since 27 de abr. de 2021
 * @version 1.0
 */
public class ClienteController {

	public void salvar(Cliente cliente) {
		try {
			new ClienteDAO().salvar(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(Cliente cliente) {
		try {
			new ClienteDAO().excluir(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Cliente> buscarPorNome(String nome) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Cliente> retorno = new ArrayList<Cliente>();

		try {
			retorno = new ClienteDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public ArrayList<Cliente> buscarTodos() throws Exception {
		ArrayList<Cliente> retorno = new ArrayList<Cliente>();

		try {
			retorno = new ClienteDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
