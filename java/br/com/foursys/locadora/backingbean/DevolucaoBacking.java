package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;

/**
 * @author João Victor
 * @since 06/05/2021
 * @version 1.0
 */
@ManagedBean(name = "devolucaoBacking")
@ViewScoped
public class DevolucaoBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private int locacao;

	private String cliente;
	private String dataLocacao;
	private String dataDevolucao;
	private String dataAtual;
	private Locacao devolucao;
	private ArrayList<Locacao> listaLocacoes;
	private ArrayList<Filme> listaFilmes;

	private boolean bloqueiaBotoes;

	public DevolucaoBacking() {
		carregarLocacoes();
		bloqueiaBotoes = true;
	}

	public int getLocacao() {
		return locacao;
	}

	public void setLocacao(int locacao) {
		this.locacao = locacao;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(String dataAtual) {
		this.dataAtual = dataAtual;
	}

	public ArrayList<Locacao> getListaLocacoes() {
		return listaLocacoes;
	}

	public void setListaLocacoes(ArrayList<Locacao> listaLocacoes) {
		this.listaLocacoes = listaLocacoes;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public Locacao getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(Locacao devolucao) {
		this.devolucao = devolucao;
	}

	public boolean isBloqueiaBotoes() {
		return bloqueiaBotoes;
	}

	public void setBloqueiaBotoes(boolean bloqueiaBotoes) {
		this.bloqueiaBotoes = bloqueiaBotoes;
	}

	public void carregarLocacoes() {
		try {
			listaLocacoes = new LocacaoController().buscarDevolvido("Não");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregarLocacaoSelecionada() {
		listaFilmes = new ArrayList<Filme>();
		devolucao = listaLocacoes.get(listaLocacoes.indexOf(new Locacao(locacao)));
		setCliente(devolucao.getClienteIdCliente().getNome());
		setDataLocacao(devolucao.getDataLocacao());
		setDataDevolucao(devolucao.getDataDevolucao());
		setDataAtual(getDataAtual());

		for (LocacaoFilme locacaoFilme : new LocacaoFilmeController().buscarPorLocacao(devolucao)) {
			listaFilmes.add(locacaoFilme.getFilmeIdFilme());
		}
		carregarDataAtual();
		bloqueiaBotoes = false;
	}

	public void cancelar() {
		setLocacao(0);
		bloqueiaBotoes = true;
		limparCampos();
	}

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void limparCampos() {
		setCliente(null);
		setDataLocacao(null);
		setDataDevolucao(null);
		setDataAtual(null);
		listaFilmes = new ArrayList<Filme>();
	}

	private void carregarDataAtual() {
		Date data = new Date();
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		dataAtual = formata.format(data);
	}

	public void salvar() {

		try {
			getDevolver();
			new LocacaoController().salvar(devolucao);

			alterarFilmesLocacao();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_DEVOLUCAO, Mensagem.DEVOLUCAO_SALVO);
			cancelar();
			carregarLocacoes();
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_DEVOLUCAO, Mensagem.DEVOLUCAO_ERRO_SALVO);
		}
	}

	private void getDevolver() {
		devolucao.setDevolvido("Sim");
		if (!dataDevolucao.equals(dataAtual)) {
			devolucao.setDataDevolucao(dataAtual);
		}
	}

	private void alterarFilmesLocacao() {
		for (Filme filme : listaFilmes) {
			filme.setDisponivel("Sim");

			try {
				new FilmeController().salvar(filme);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
