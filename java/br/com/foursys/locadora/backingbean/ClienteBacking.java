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
import br.com.foursys.locadora.bean.Cliente;
import br.com.foursys.locadora.bean.Contato;
import br.com.foursys.locadora.bean.Endereco;
import br.com.foursys.locadora.bean.Estado;
import br.com.foursys.locadora.controller.CidadeController;
import br.com.foursys.locadora.controller.ClienteController;
import br.com.foursys.locadora.controller.ContatoController;
import br.com.foursys.locadora.controller.EnderecoController;
import br.com.foursys.locadora.controller.EstadoController;
import br.com.foursys.locadora.util.JSFUtil;
import br.com.foursys.locadora.util.Logradouro;
import br.com.foursys.locadora.util.Mensagem;
import br.com.foursys.locadora.util.Perfil;
import br.com.foursys.locadora.util.Titulo;
import br.com.foursys.locadora.util.Valida;

/**
 * Classe reponsável por controlar os componentes do front-end Cliente
 * 
 * @author João Victor
 * @since 27/04/2021
 * @version 1.0
 */

@ManagedBean(name = "clienteBacking")
@SessionScoped
public class ClienteBacking implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String cpf;
	private String rg;

	private Date dataNascimento;
	private String dataNascAux;

	private String idade;
	private String sexo;
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
	private Cliente clienteSelecionado;

	// atributos auxiliares
	private Cliente cliente;
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
	private ArrayList<Cliente> listaClientes;

	private boolean comboCidade = true;
	private int indexTab;

	private boolean bloqueioDetalha;

	public ClienteBacking() {
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

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
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

	public String getDataNascAux() {
		return dataNascAux;
	}

	public void setDataNascAux(String dataNascAux) {
		this.dataNascAux = dataNascAux;
	}

	private boolean validar() {

		if (Valida.isEmptyOrNull(nome)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.NOME_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(cpf)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CPF_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(rg)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.RG_VAZIO);
			return false;
		}
		if (Valida.isDateNull(dataNascimento)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.DATA_NASCIMENTO_VAZIO);
			return false;
		}
		if (!Valida.isInteger(idade)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.IDADE_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(idade))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.IDADE_INVALIDO);
			return false;
		}
		if (Valida.isEmptyOrNull(sexo)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.SEXO_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(celular)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CELULAR_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(email)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.EMAIL_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(tipoLogradouro)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.TIPO_LOGRADOURO_VAZIO);
			return false;
		}
		if (Valida.isEmptyOrNull(enderecoAux)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.ENDERECO_VAZIO);
			return false;
		}
		if (!Valida.isInteger(numero)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.NUMERO_VAZIO);
			return false;
		} else if (Valida.isIntZero(Integer.parseInt(idade))) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.NUMERO_INVALIDO);
			return false;
		}
		if (Valida.isEmptyOrNull(cep)) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CEP_VAZIO);
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
				getCliente();
				new EnderecoController().salvar(endereco);
				new ContatoController().salvar(contato);
				new ClienteController().salvar(cliente);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_SALVO);
			}
		}
	}

	private void getCliente() {
		cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setRg(rg);
		cliente.setDataNascimento(getDateToString());
		cliente.setIdade(Integer.parseInt(idade));
		cliente.setSexo(sexo);
		getEndereco();
		getContato();
		cliente.setEnderecoIdEndereco(endereco);
		cliente.setContatoIdContato(contato);
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

	public void alterarCliente() {
		if (validar()) {
			try {
				getClienteAlterar();
				new EnderecoController().salvar(endereco);
				new ContatoController().salvar(contato);
				limparCampos();
				JSFUtil.addInfoMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_SALVO);
			} catch (Exception e) {
				JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_SALVO);
			}
		}

		try {
			pesquisar();
			FacesContext.getCurrentInstance().getExternalContext().redirect("list-cliente.xhtml");
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
	 * m�todo que captura a a��o do bot�o CANCELAR na tela
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
			FacesContext.getCurrentInstance().getExternalContext().redirect("list-cliente.xhtml");
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
	 * m�todo para pesquisar clientes
	 */
	public void pesquisar() {

		try {
			listaClientes = new ClienteController().buscarPorNome(nomePesquisar);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_PESQUISAR);
		}

	}

	public void excluir() {

		try {
			new ClienteController().excluir(clienteSelecionado);
			new EnderecoController().excluir(endereco);
			new ContatoController().excluir(contato);
			pesquisar();
			JSFUtil.addInfoMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_EXCLUIDO);
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CLIENTE_ERRO_EXCLUIR);
		}
	}

	private void getClienteAlterar() {
		cliente = clienteSelecionado;
		getEnderecoAlterar();
		getContatoAlterar();
		cliente.setEnderecoIdEndereco(endereco);
		cliente.setContatoIdContato(contato);
	}

	private void getEnderecoAlterar() {
		endereco = cliente.getEnderecoIdEndereco();
		endereco.setTipoLogradouro(tipoLogradouro);
		endereco.setEndereco(enderecoAux);
		endereco.setNumero(Integer.parseInt(numero));
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCep(cep);
		endereco.setCidadeIdCidade(new Cidade(cidade));
	}

	private void getContatoAlterar() {
		contato = cliente.getContatoIdContato();
		contato.setTelefone(telefone);
		contato.setCelular(celular);
		contato.setEmail(email);
	}

	public void alterar() throws ParseException {
		nome = clienteSelecionado.getNome();
		cpf = clienteSelecionado.getCpf();
		rg = clienteSelecionado.getRg();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimento = new Date(format.parse(clienteSelecionado.getDataNascimento()).getTime());

		idade = clienteSelecionado.getIdade() + "";
		sexo = clienteSelecionado.getSexo();
		carregarEstado();
		carregarCidadesAlterar();
		tipoLogradouro = clienteSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderecoAux = clienteSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = clienteSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = clienteSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = clienteSelecionado.getEnderecoIdEndereco().getBairro();
		cep = clienteSelecionado.getEnderecoIdEndereco().getCep();
		estado = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = clienteSelecionado.getContatoIdContato().getTelefone();
		celular = clienteSelecionado.getContatoIdContato().getCelular();
		email = clienteSelecionado.getContatoIdContato().getEmail();

		setBloqueioDetalha(false);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("alt-cliente.xhtml");
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
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CIDADE_ERRO_PESQUISA);
		}
	}

	public void carregarCidadesAlterar() {
		listaCidades = new ArrayList<Cidade>();
		try {
			listaCidades = new CidadeController().buscarTodos();
			comboCidade = false;
		} catch (Exception e) {
			JSFUtil.addErrorMessage(Titulo.CADASTRO_CLIENTE, Mensagem.CIDADE_ERRO_PESQUISA);
		}
	}

	private String getDateToString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(dataNascimento);
	}

	public void detalhar() throws ParseException {
		nome = clienteSelecionado.getNome();
		cpf = clienteSelecionado.getCpf();
		rg = clienteSelecionado.getRg();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		dataNascimento = new Date(format.parse(clienteSelecionado.getDataNascimento()).getTime());
		idade = clienteSelecionado.getIdade() + "";
		sexo = clienteSelecionado.getSexo();
		carregarEstado();
		carregarCidadesAlterar();
		tipoLogradouro = clienteSelecionado.getEnderecoIdEndereco().getTipoLogradouro();
		enderecoAux = clienteSelecionado.getEnderecoIdEndereco().getEndereco();
		numero = clienteSelecionado.getEnderecoIdEndereco().getNumero() + "";
		complemento = clienteSelecionado.getEnderecoIdEndereco().getComplemento();
		bairro = clienteSelecionado.getEnderecoIdEndereco().getBairro();
		cep = clienteSelecionado.getEnderecoIdEndereco().getCep();
		estado = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getIdEstado();
		cidade = clienteSelecionado.getEnderecoIdEndereco().getCidadeIdCidade().getIdCidade();
		telefone = clienteSelecionado.getContatoIdContato().getTelefone();
		celular = clienteSelecionado.getContatoIdContato().getCelular();
		email = clienteSelecionado.getContatoIdContato().getEmail();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("det-cliente.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBloqueioDetalha(true);
		setComboCidade(true);
	}

}
