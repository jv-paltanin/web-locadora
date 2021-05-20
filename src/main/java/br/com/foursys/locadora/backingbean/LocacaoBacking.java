package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Filme;
import br.com.foursys.locadora.bean.FormaPagamento;
import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.bean.LocacaoFilme;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.FilmeController;
import br.com.foursys.locadora.controller.FormaPagamentoController;
import br.com.foursys.locadora.controller.LocacaoController;
import br.com.foursys.locadora.controller.LocacaoFilmeController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * @author João Victor
 * @since 05/05/2021
 * @version 1.0
 */
@ManagedBean(name = "locacaoBacking")
@ViewScoped
public class LocacaoBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private int cliente;
	private int filme;
	private String valor;
	private String dataLocacao;
	private Date dataDevolucao;
	private int formaPagamento;

	private double valorTotal;
	private boolean bloqueia;
	private boolean bloqueiaCliente;

	private Locacao locacao;
	private Filme filmeSelecionado;

	private Locacao locacaoSelecionada;

	private ArrayList<Cliente> listaClientes;
	private ArrayList<Filme> listaFilmes;
	private ArrayList<Filme> listaFilmesLocados;
	private ArrayList<FormaPagamento> listaFormaPagamento;
	private ArrayList<Locacao> listaLocacoes;

	private ArrayList<LocacaoFilme> locacaoPesquisar;

	private String nomePesquisar;

	public String getNomePesquisar() {
		return nomePesquisar;
	}

	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}

	public LocacaoBacking() {
		carregarClientes();
		carregarFilmes();
		carregarFormaPagamento();
		carregarDataLocacao();
		listaFilmesLocados = new ArrayList<Filme>();
		setValor(NumberFormat.getCurrencyInstance().format(0.0));
		setBloqueia(true);
		setBloqueiaCliente(false);
	}

	public ArrayList<LocacaoFilme> getLocacaoPesquisar() {
		return locacaoPesquisar;
	}

	public void setLocacaoPesquisar(ArrayList<LocacaoFilme> locacaoPesquisar) {
		this.locacaoPesquisar = locacaoPesquisar;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public int getFilme() {
		return filme;
	}

	public void setFilme(int filme) {
		this.filme = filme;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public int getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean isBloqueia() {
		return bloqueia;
	}

	public void setBloqueia(boolean bloqueia) {
		this.bloqueia = bloqueia;
	}

	public boolean isBloqueiaCliente() {
		return bloqueiaCliente;
	}

	public void setBloqueiaCliente(boolean bloqueiaCliente) {
		this.bloqueiaCliente = bloqueiaCliente;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Filme> getListaFilmes() {
		return listaFilmes;
	}

	public void setListaFilmes(ArrayList<Filme> listaFilmes) {
		this.listaFilmes = listaFilmes;
	}

	public ArrayList<Filme> getListaFilmesLocados() {
		return listaFilmesLocados;
	}

	public void setListaFilmesLocados(ArrayList<Filme> listaFilmesLocados) {
		this.listaFilmesLocados = listaFilmesLocados;
	}

	public ArrayList<FormaPagamento> getListaFormaPagamento() {
		return listaFormaPagamento;
	}

	public void setListaFormaPagamento(ArrayList<FormaPagamento> listaFormaPagamento) {
		this.listaFormaPagamento = listaFormaPagamento;
	}

	public ArrayList<Locacao> getListaLocacoes() {
		return listaLocacoes;
	}

	public void setListaLocacoes(ArrayList<Locacao> listaLocacoes) {
		this.listaLocacoes = listaLocacoes;
	}

	public Locacao getLocacaoSelecionada() {
		return locacaoSelecionada;
	}

	public void setLocacaoSelecionada(Locacao locacaoSelecionada) {
		this.locacaoSelecionada = locacaoSelecionada;
	}

	public void carregarClientes() {
		try {
			listaClientes = new ClienteController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregarFilmes() {
		try {
			listaFilmes = new FilmeController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregarFormaPagamento() {
		try {
			listaFormaPagamento = new FormaPagamentoController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cancelar() {
		setBloqueia(true);
		setBloqueiaCliente(false);
		limparCampos();
	}

	private void limparCampos() {
		carregarFilmes();
		setCliente(0);
		setFilme(0);
		listaFilmesLocados = new ArrayList<Filme>();
		setFormaPagamento(0);
		setDataDevolucao(null);
		valorTotal = 0.0;
		setValor(NumberFormat.getCurrencyInstance().format(0.0));
	}

	private void carregarDataLocacao() {
		Date data = new Date();
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		dataLocacao = formata.format(data);
	}

	private String getDateToString() {
		SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
		return formata.format(dataDevolucao);
	}

	public void desbloqueiaFilmes() {
		if (cliente > 0) {
			setBloqueia(false);
		} else {
			setBloqueia(true);
		}
	}

	public void adicionar() {
		if (filme > 0) {
			int index = listaFilmes.indexOf(new Filme(filme));
			Filme filme = listaFilmes.get(index);

			if (filme.getFaixaEtaria() > getClienteLista().getIdade()) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FILME_NAO_PERMITIDO);
			} else {
				setBloqueiaCliente(true);
				listaFilmesLocados.add(filme);
				listaFilmes.remove(filme);

				Collections.sort(listaFilmesLocados, Filme.FilmeComparator);

				if (filme.getPromocao().equals("Sim")) {
					valorTotal += filme.getValorPromocao();
				} else {
					valorTotal += filme.getValor();
				}
				valor = NumberFormat.getCurrencyInstance().format(valorTotal);
			}

		} else {
			JSFUtil.addErrorMessage("Efetuar Locação", "Selecione um filme!");
		}

	}

	public void remover() {
		listaFilmesLocados.remove(filmeSelecionado);
		listaFilmes.add(filmeSelecionado);

		Collections.sort(listaFilmes, Filme.FilmeComparator);

		if (filmeSelecionado.getPromocao().equals("Sim")) {
			valorTotal -= filmeSelecionado.getValorPromocao();
		} else {
			valorTotal -= filmeSelecionado.getValor();
		}
		valor = NumberFormat.getCurrencyInstance().format(valorTotal);

	}

	private boolean validar() {

		if (Valida.isIntZero(cliente)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.CLIENTE_VAZIO);
			return false;
		}

		if (Valida.isIntZero(listaFilmesLocados.size())) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FILME_VAZIO);
			return false;
		}

		if (Valida.isIntZero(formaPagamento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.FORMA_PAGAMENTO_VAZIO);
			return false;
		}

		if (Valida.isDateNull(dataDevolucao)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.DATA_DEVOLUCAO_VAZIO);
			return false;
		}

		return true;
	}

	public void salvar() {

		if (validar()) {

			try {
				getLocar();
				new LocacaoController().salvar(locacao);

				salvarFilmesLocados();

				cancelar();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_LOCACAO, Mensagem.LOCACAO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.LOCACAO_ERRO_SALVO);
			}

		}

	}

	private void getLocar() {
		locacao = new Locacao();

		locacao.setFuncionarioIdFuncionario(LoginBacking.funcionarioLogado);
		locacao.setValor(valorTotal);
		locacao.setDevolvido("Não");
		locacao.setDataLocacao(dataLocacao);
		locacao.setDataDevolucao(getDateToString());

		FormaPagamento formaPagamentoSelecionada = listaFormaPagamento
				.get(listaFormaPagamento.indexOf(new FormaPagamento(formaPagamento)));
		locacao.setFormaPagamentoIdFormaPagamento(formaPagamentoSelecionada);

		Cliente clienteSelecionado = listaClientes.get(listaClientes.indexOf(new Cliente(cliente)));
		locacao.setClienteIdCliente(clienteSelecionado);

	}

	private void salvarFilmesLocados() {
		for (Filme filme : listaFilmesLocados) {
			try {
				new LocacaoFilmeController().salvar(getLocacaoFilme(filme));

				filmeIndisponivel(filme);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void filmeIndisponivel(Filme filme) {
		filme.setDisponivel("Não");
		new FilmeController().salvar(filme);
	}

	private LocacaoFilme getLocacaoFilme(Filme filme) {
		LocacaoFilme locacaoFilme = new LocacaoFilme();
		locacaoFilme.setFilmeIdFilme(filme);
		locacaoFilme.setLocacaoIdLocacao(locacao);
		return locacaoFilme;
	}

	public String pesquisar() {

		try {
			locacaoPesquisar = new LocacaoFilmeController().buscarPorFilme(nomePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_LOCACAO, Mensagem.LOCACAO_ERRO_PESQUISA);
		}
		return "";
	}

	private Cliente getClienteLista() {
		int indCliente = listaClientes.indexOf(new Cliente(cliente));
		return listaClientes.get(indCliente);
	}

}
