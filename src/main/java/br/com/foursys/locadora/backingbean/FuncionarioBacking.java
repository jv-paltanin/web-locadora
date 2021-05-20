package br.com.foursys.locadora.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.foursys.locadora.bean.Cidade;
import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.bean.Funcionario;
import br.com.foursys.locadora.controller.CidadeController;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.EstadoController;
import br.com.foursys.locadora.controller.FuncionarioController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Logradouro;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe reponsável por controlar os componentes do front-end Funcionário
 * 
 * @author João Victor
 * @since 27/04/2021
 * @version 1.0
 */

@ManagedBean(name = "funcionarioBacking")
@SessionScoped
public class FuncionarioBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private String idade;
	private String sexo;
	private String login;
	private String senha;
	private String perfilAcesso;
	private String telefone;
	private String celular;
	private String email;
	private String tipoLogradouro;
	private String enderecoAux;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	// atributos da tela de consulta
	private String nomePesquisar;
	private Funcionario funcionarioSelecionado;

	// atributos auxiliares
	private Funcionario funcionario;
	private Endereco endereco;
	private Contato contato;

	// atributos da tela de cadastro
	private int estado;
	private int cidade;

	// atributos auxilziares
	private ArrayList<Estado> listaEstados;
	private ArrayList<Cidade> listaCidades;
	private ArrayList<String> listaPerfil;
	private ArrayList<String> listaLogradouro;
	private ArrayList<Funcionario> listaFuncionarios;

	private boolean comboCidade = true;
	private int indexTab;

	private boolean bloqueioDetalha;

	public FuncionarioBacking() {
		carregarPerfilAcesso();
		carregarLogradouro();
		carregarEstado();
		limparCampos();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(String perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEnderecoAux() {
		return enderecoAux;
	}

	public void setEnderecoAux(String enderecoAux) {
		this.enderecoAux = enderecoAux;
	}

	public ArrayList<String> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(ArrayList<String> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public ArrayList<String> getListaLogradouro() {
		return listaLogradouro;
	}

	public void setListaLogradouro(ArrayList<String> listaLogradouro) {
		this.listaLogradouro = listaLogradouro;
	}

	public String getNomePesquisar() {
		return nomePesquisar;
	}

	public void setNomePesquisar(String nomePesquisar) {
		this.nomePesquisar = nomePesquisar;
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}

	public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
		this.funcionarioSelecionado = funcionarioSelecionado;
	}

	public ArrayList<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(ArrayList<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public ArrayList<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(ArrayList<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public ArrayList<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(ArrayList<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public boolean isComboCidade() {
		return comboCidade;
	}

	public void setComboCidade(boolean comboCidade) {
		this.comboCidade = comboCidade;
	}

	public int getIndexTab() {
		return indexTab;
	}

	public void setIndexTab(int indexTab) {
		this.indexTab = indexTab;
	}

	public boolean isBloqueioDetalha() {
		return bloqueioDetalha;
	}

	public void setBloqueioDetalha(boolean bloqueioDetalha) {
		this.bloqueioDetalha = bloqueioDetalha;
	}

	private boolean validar() {

		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NOME_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(cpf)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CPF_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(rg)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.RG_VAZIO);
			return false;
		}
		if (Valida.isDateNull(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.DATA_NASCIMENTO_VAZIO);
			return false;
		}
		if (!Valida.isInteger(idade)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.IDADE_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(idade))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.IDADE_INVALIDO);
			return false;
		}
		if (Valida.isEmptyOrNull(sexo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.SEXO_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(login)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.LOGIN_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(senha)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.SENHA_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(perfilAcesso)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.PERFIL_ACESSO_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(celular)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CELULAR_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(email)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.EMAIL_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(tipoLogradouro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.TIPO_LOGRADOURO_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(enderecoAux)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.ENDERECO_VAZIO);
			return false;
		}
		if (!Valida.isInteger(numero)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NUMERO_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(idade))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.NUMERO_INVALIDO);
			return false;
		}
		if (Valida.isEmptyOrNull(cep)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CEP_VAZIO);
			return false;
		}

		return true;
	}

	/*
	 * método que captura a ação do botão CADASTRAR
	 */
	public void cadastrar() {
		if (validar()) {
			try {
				getFuncionario();
				new EnderecoController().salvar(endereco);
				new ContatoController().salvar(contato);
				new FuncionarioController().salvar(funcionario);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}
		}
	}

	private void getFuncionario() {
		funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setRg(rg);
		funcionario.setDataNascimento(getDateToString());
		funcionario.setIdade(Integer.parseInt(idade));
		funcionario.setSexo(sexo);
		funcionario.setLogin(login);
		funcionario.setSenha(senha);
		funcionario.setPerfilAcesso(perfilAcesso);
		getEndereco();
		getContato();
		funcionario.setEnderecoIdEndereco(endereco);
		funcionario.setContatoIdContato(contato);
	}

	private void getEndereco() {
		endereco = new Endereco();
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setEndereco(enderecoAux);
		endereco.setNumero(Integer.parseInt(numero));
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(new Cidade(cidade));
	}

	private void getContato() {
		contato = new Contato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}

	public void alterarFuncionario() {
		if (validar()) {
			try {
				getFuncionarioAlterar();
				new EnderecoController().salvar(endereco);
				new ContatoController().salvar(contato);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_SALVO);
			}
		}

		try {
			pesquisar();
			FacesContext.getCurrentInstance().getExternalContext().redirect("list-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void limparCampos() {
		setNome(null);
		setCpf(null);
		setRg(null);
		setDataNascimento(null);
		setIdade(null);
		setSexo(null);
		setLogin(null);
		setSenha(null);
		setPerfilAcesso(null);
		setTelefone(null);
		setCelular(null);
		setEmail(null);
		setTipoLogradouro(null);
		setEnderecoAux(null);
		setNumero(null);
		setComplemento(null);
		setBairro(null);
		setCep(null);
		setCidade(0);
		setEstado(0);

	}

	private void carregarEstado() {
		try {
			listaEstados = new EstadoController().buscarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * m�todo que captura a a��o do bot�o CANCELAR na tela cad-filme.jsp
	 */
	public void cancelar() {
		limparCampos();
	}

	/*
	 * método que captura a ação do botão SAIR
	 */
	public void sair() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sairAlterar() {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("list-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * método para carregar a lista de perfil de acesso
	 */
	public void carregarPerfilAcesso() {
		listaPerfil = new ArrayList<String>();

		for (Perfil p : Perfil.values()) {
			listaPerfil.add(p.getPerfil());
		}
	}

	/*
	 * método para carregar a lista de logradouro
	 */
	public void carregarLogradouro() {
		listaLogradouro = new ArrayList<String>();

		for (Logradouro l : Logradouro.values()) {
			listaLogradouro.add(l.getTipoLogradouro());
		}
	}

	/*
	 * método para pesquisar filmes
	 */
	public void pesquisar() {

		try {
			listaFuncionarios = new ArrayList<Funcionario>();

			for (Funcionario funcionario : new FuncionarioController().buscarPorNome(nomePesquisar)) {
				if (funcionario.getPerfilAcesso().equals(Perfil.DEV.getPerfil())) {
					if (LoginBacking.funcionarioLogado.getPerfilAcesso().equals(Perfil.DEV.getPerfil())) {
						listaFuncionarios.add(funcionario);
					}
				} else {
					listaFuncionarios.add(funcionario);
				}
			}

		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_PESQUISAR);
		}

	}

	public void excluir() {

		try {
			new FuncionarioController().excluir(funcionarioSelecionado);
			new EnderecoController().excluir(endereco);
			new ContatoController().excluir(contato);
			pesquisar();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_EXCLUIDO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.FUNCIONARIO_ERRO_EXCLUIR);
		}
	}

	private void getFuncionarioAlterar() {
		funcionario = funcionarioSelecionado;
		getEnderecoAlterar();
		getContatoAlterar();
		funcionario.setEnderecoIdEndereco(endereco);
		funcionario.setContatoIdContato(contato);
	}

	private void getEnderecoAlterar() {
		endereco = funcionario.getEnderecoIdEndereco();
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setEndereco(enderecoAux);
		endereco.setNumero(Integer.parseInt(numero));
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(new Cidade(cidade));
	}

	private void getContatoAlterar() {
		contato = funcionario.getContatoIdContato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}

	public void alterar() throws ParseException {
		nome = funcionarioSelecionado.getNome();
		cpf = funcionarioSelecionado.getCpf();
		rg = funcionarioSelecionado.getRg();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimento = new Date(format.parse(funcionarioSelecionado.getDataNascimento()).getTime());

		idade = funcionarioSelecionado.getIdade() + "";
		sexo = funcionarioSelecionado.getSexo();
		login = funcionarioSelecionado.getLogin();
		senha = funcionarioSelecionado.getSenha();
		perfilAcesso = funcionarioSelecionado.getPerfilAcesso();
		carregarEstado();
		carregarCidadesAlterar();
		tipoLogradouro = funcionarioSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderecoAux = funcionarioSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = funcionarioSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = funcionarioSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = funcionarioSelecionado.getEnderecoIdEndereco().getBairro();
		cep = funcionarioSelecionado.getEnderecoIdEndereco().getCep();
		estado = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = funcionarioSelecionado.getContatoIdContato().getTelefone();
		celular = funcionarioSelecionado.getContatoIdContato().getCelular();
		email = funcionarioSelecionado.getContatoIdContato().getEmail();

		setBloqueioDetalha(false);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void carregarCidade() {
		listaCidades = new ArrayList<Cidade>();
		try {
			if (estado > 0) {
				listaCidades = new CidadeController().buscarPorEstado(new Estado(estado));
				comboCidade = false;
			} else {
				comboCidade = true;
			}
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CIDADE_ERRO_PESQUISA);
		}
	}

	public void carregarCidadesAlterar() {
		listaCidades = new ArrayList<Cidade>();
		try {
			listaCidades = new CidadeController().buscarTodos();
			comboCidade = false;
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_FUNCIONARIO, Mensagem.CIDADE_ERRO_PESQUISA);
		}
	}

	private String getDateToString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(dataNascimento);
	}

	public void detalhar() throws ParseException {
		nome = funcionarioSelecionado.getNome();
		cpf = funcionarioSelecionado.getCpf();
		rg = funcionarioSelecionado.getRg();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimento = new Date(format.parse(funcionarioSelecionado.getDataNascimento()).getTime());

		idade = funcionarioSelecionado.getIdade() + "";
		sexo = funcionarioSelecionado.getSexo();
		login = funcionarioSelecionado.getLogin();
		senha = funcionarioSelecionado.getSenha();
		perfilAcesso = funcionarioSelecionado.getPerfilAcesso();
		carregarEstado();
		carregarCidadesAlterar();
		tipoLogradouro = funcionarioSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderecoAux = funcionarioSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = funcionarioSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = funcionarioSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = funcionarioSelecionado.getEnderecoIdEndereco().getBairro();
		cep = funcionarioSelecionado.getEnderecoIdEndereco().getCep();
		estado = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = funcionarioSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = funcionarioSelecionado.getContatoIdContato().getTelefone();
		celular = funcionarioSelecionado.getContatoIdContato().getCelular();
		email = funcionarioSelecionado.getContatoIdContato().getEmail();

		setBloqueioDetalha(true);
		setComboCidade(true);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("det-funcionario.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
