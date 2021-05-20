package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.util.Genero;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe reponsável por controlar os componentes do front-end Filme
 * 
 * @author João Victor
 * @since 27/04/2021
 * @version 1.0
 */

@ManagedBean(name = "filmeBacking")
@SessionScoped
public class FilmeBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String valor;
	private String disponivel;
	private String promocao;
	private String valorPromocao;
	private String diretor;
	private String anoLancamento;
	private String faixaEtaria;
	private String genero;

	// atributos da tela de consulta
	private String nomePesquisar;
	private Filme filmeSelecionado;

	// atributos auxiliares
	private Filme filme;
	private ArrayList<Filme> listaFilmes;
	private ArrayList<String> listaGeneros;

	public FilmeBacking() {
		carregarGenero();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}

	public String getPromocao() {
		return promocao;
	}

	public void setPromocao(String promocao) {
		this.promocao = promocao;
	}

	public String getValorPromocao() {
		return valorPromocao;
	}

	public void setValorPromocao(String valorPromocao) {
		this.valorPromocao = valorPromocao;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public String getNomePesquisa() {
		return nomePesquisar;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisar = nomePesquisa;
	}

	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}

	public ArrayList<String> getListaGeneros() {
		return listaGeneros;
	}

	public void setListaGeneros(ArrayList<String> listaGeneros) {
		this.listaGeneros = listaGeneros;
	}

	/*
	 * Método que captura a ação do botoção CADASTRAR na tela cad-filme.jsp
	 */
	public void cadastrar() {

		if (validar()) {
			try {
				getFilme();
				new FilmeController().salvar(filme);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_SALVO);

			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ERRO_SALVO);
			}
		}

	}

	public void alterarFilme() {

		if (validar()) {

			try {
				getFilmeAlterar();
				new FilmeController().salvar(filme);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_SALVO);
				FacesContext.getCurrentInstance().getExternalContext().redirect("list-filme.xhtml");
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ERRO_SALVO);
			}

		}

	}

	private boolean validar() {

		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.NOME_VAZIO);
			return false;
		}

		if (!Valida.isDouble(valor)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_VAZIO);
			return false;
		} else if (Valida.isDoubleZero(Double.parseDouble(valor))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_INVALIDO);
			return false;
		}

		if (Valida.isEmptyOrNull(disponivel)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.DISPONIVEL_VAZIO);
			return false;
		}

		if (Valida.isEmptyOrNull(promocao)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.PROMOCAO_VAZIO);
			return false;
		}

		if (promocao.equals("Sim")) {
			if (!Valida.isDouble(valorPromocao)) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_PROMOCAO_VAZIO);
				return false;
			} else if (Valida.isDoubleZero(Double.parseDouble(valorPromocao))) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.VALOR_PROMOCAO_INVALIDO);
				return false;
			}
		}

		if (Valida.isEmptyOrNull(diretor)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.DIRETOR_VAZIO);
			return false;
		}

		if (!Valida.isInteger(anoLancamento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.ANO_LANCAMENTO_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(anoLancamento))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.ANO_LANCAMENTO_INVALIDO);
			return false;
		}

		if (!Valida.isInteger(faixaEtaria)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FAIXA_ETARIA_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(faixaEtaria))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FAIXA_ETARIA_INVALIDO);
			return false;
		}

		if (Valida.isEmptyOrNull(genero)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.GENERO_VAZIO);
			return false;
		}

		return true;
	}

	/*
	 * m�todo que captura a a��o do bot�o CANCELAR na tela cad-filme.jsp
	 */
	public void cancelar() {
		limparCampos();
	}

	/*
	 * m�todo que captura a a��o do bot�o SAIR na tela cad-filme.jsp
	 */
	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/*
	 * método para abrir a tela de cadastro
	 */
	public String cadastroFilme() {
		limparCampos();
		return "";
	}

	/*
	 * m�todo para abrir a tela de consulta
	 */
	public String consultarFilme() {
		nomePesquisar = null;
		listaFilmes = null;
		return "";
	}

	/*
	 * m�todo para retonar um objeto Filme
	 */
	private void getFilme() {
		filme = new Filme();

		filme.setNome(nome);
		filme.setValor(Double.parseDouble(valor));
		filme.setDisponivel(disponivel);
		filme.setPromocao(promocao);

		if (promocao.equals("Sim")) {
			filme.setValorPromocao(Double.parseDouble(valorPromocao));
		}

		filme.setDiretor(diretor);
		filme.setAnoLancamento(anoLancamento);
		filme.setFaixaEtaria(Integer.parseInt(faixaEtaria));
		filme.setGenero(genero);
	}

	/*
	 * m�todo para retonar um objeto Filme
	 */
	private void getFilmeAlterar() {
		filme = filmeSelecionado;
		filme.setNome(nome);
		filme.setValor(Double.parseDouble(valor));
		filme.setDisponivel(disponivel);
		filme.setPromocao(promocao);

		if (promocao.equals("Sim")) {
			filme.setValorPromocao(Double.parseDouble(valorPromocao));
		}

		filme.setDiretor(diretor);
		filme.setAnoLancamento(anoLancamento);
		filme.setFaixaEtaria(Integer.parseInt(faixaEtaria));
		filme.setGenero(genero);
	}

	private void limparCampos() {

		setNome(null);
		setValor(null);
		setDisponivel(null);
		setPromocao(null);
		setValorPromocao(null);
		setDiretor(null);
		setFaixaEtaria(null);
		setAnoLancamento(null);
		setGenero(null);

	}

	/*
	 * m�todo para alterar os filmes
	 */
	public void alterar() {
		nome = filmeSelecionado.getNome();
		valor = filmeSelecionado.getValor() + "";
		disponivel = filmeSelecionado.getDisponivel();
		promocao = filmeSelecionado.getPromocao() + "";
		valorPromocao = filmeSelecionado.getValorPromocao() + "";
		diretor = filmeSelecionado.getDiretor();
		faixaEtaria = filmeSelecionado.getFaixaEtaria() + "";
		anoLancamento = filmeSelecionado.getAnoLancamento();
		genero = filmeSelecionado.getGenero();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-filme.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * m�todo para pesquisar filmes
	 */
	public String pesquisar() {
		try {
			listaFilmes = new FilmeController().buscarPorNome(nomePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ERRO_PESQUISAR);
		}
		return "";
	}

	/*
	 * m�todo para excluir os filmes
	 */
	public void excluir() {
		try {
			new FilmeController().excluir(filmeSelecionado);
			pesquisar();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_EXCLUIDO);
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FILME, Mensagem.FILME_ERRO_EXCLUIR);
		}
	}

	/*
	 * método para carregar a lista de gênero
	 */
	public void carregarGenero() {
		listaGeneros = new ArrayList<String>();

		for (Genero g : Genero.values()) {
			listaGeneros.add(g.getDescricao());
		}
	}

}// fim da classe
