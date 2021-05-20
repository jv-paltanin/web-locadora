package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.dao.FuncionarioDAO;

/**
 * Classe responsável por acessar o objeto DAO e efetuar consulta na base de
 * dados
 * 
 * @author João Victor
 * @since 27 de abr. de 2021
 * @version 1.0
 */
public class FuncionarioController {

	public void salvar(Funcionario funcionario) {
		try {
			new FuncionarioDAO().salvar(funcionario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(Funcionario funcionario) {
		try {
			new FuncionarioDAO().excluir(funcionario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Funcionario> buscarPorNome(String nome) throws Exception {
		// lista auxiliar para retornar no m�todo
		ArrayList<Funcionario> retorno = new ArrayList<Funcionario>();

		try {
			retorno = new FuncionarioDAO().buscarPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public ArrayList<Funcionario> buscarPorLogin(String login) throws Exception {
		// lista auxiliar para retornar no método
		ArrayList<Funcionario> retorno = new ArrayList<Funcionario>();

		try {
			retorno = new FuncionarioDAO().buscarPorLogin(login);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public ArrayList<Funcionario> buscarTodos() {
		ArrayList<Funcionario> retorno = new ArrayList<Funcionario>();
		try {
			retorno = new FuncionarioDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
