package br.com.foursys.locadora.controller;

import java.util.ArrayList;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.dao.LocacaoFilmeDAO;

public class LocacaoFilmeController {

	public void salvar(LocacaoFilme locacaoFilme) {
		try {
			new LocacaoFilmeDAO().salvar(locacaoFilme);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<LocacaoFilme> buscarPorLocacao(Locacao locacao){
		try {
			return new LocacaoFilmeDAO().buscarPorLocacao(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<LocacaoFilme> buscarTodos() throws Exception {
		ArrayList<LocacaoFilme> retorno = new ArrayList<LocacaoFilme>();

		try {
			retorno = new LocacaoFilmeDAO().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public ArrayList<LocacaoFilme> buscarPorFilme(String nome) {
		try {
			ArrayList<LocacaoFilme> retorno = new ArrayList<LocacaoFilme>();
			
			for (LocacaoFilme locacao : new LocacaoFilmeDAO().buscarTodos()) {
				String aux = locacao.getFilmeIdFilme().getNome();
				
				for (Filme cli : new FilmeController().buscarPorNome(nome)) {
					if (cli.getNome().equals(aux)) {
						retorno.add(locacao);
					}
				}
			}
			return retorno; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
