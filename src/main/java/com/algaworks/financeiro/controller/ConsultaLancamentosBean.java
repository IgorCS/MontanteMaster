package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.repository.Lancamentos;
import com.algaworks.financeiro.repository.Pessoas;
import com.algaworks.financeiro.repository.Usuarios;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;



@Controller
@javax.faces.view.ViewScoped
@Scope("request")
@Service
@Component("lancamentos")
public class ConsultaLancamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Lancamentos lancamentosRepository;

	@Autowired
	private CadastroLancamentos cadastro;

	@Autowired
	private Pessoas pessoas;

	@Autowired
	private Usuarios usuarios;
	
	
	@Autowired
	private List<Lancamento> lancamentos;

	
	
	@Autowired

	private BigDecimal total;
	@Autowired
	private BigDecimal lucro;
	@Autowired
	private BigDecimal saldoNegativos;

	public BigDecimal getSaldoNegativos() {
		return saldoNegativos;
	}

	public void setSaldoNegativos(BigDecimal saldoNegativos) {
		this.saldoNegativos = saldoNegativos;
	}
	@Autowired
	private Lancamento lancamentoSelecionado;
	@Autowired
	private TipoLancamento tipoLancamento;
	@Autowired
	private Lancamento lancamento;
	@Autowired
	private Usuario usuario;
	@Autowired
	private String username;

	/* PEGA PELO NOME PESSOA */

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

	// POR NOME
	/*
	 * public String getNome() { return nome; }
	 * 
	 * public void setNome(String nome) { this.nome = nome; }
	 */
	@Autowired
	private List<Pessoa> todasPessoas;
	@Autowired
	private List<Usuario> todosUsuarios;

	public void buscaLanc() {
		this.todasPessoas = this.pessoas.todas();
		this.todosUsuarios = this.usuarios.todosUsuarios();

		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			this.cadastro.excluir(this.lancamentoSelecionado);
			this.consultar();
			this.consultaLancamentoUsuario();

			context.addMessage(null, new FacesMessage(
					"Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void consultar() {
		// this.lancamentos = lancamentosRepository.lancePessoa(pessoa);		
		
		/*Aqui neste metódo o usuário vai listar todos lançamentos do banco*/
		this.lancamentos = lancamentosRepository.lancamentos(usuario);
	    //this.usuarioLogado =  new UsuarioController();		
		this.lancamentos = lancamentosRepository.todosLancamento(usuario);
		this.todasPessoas = this.pessoas.todasPessoas();
		this.todosUsuarios = this.usuarios.todosUsuarios();
		this.lancamento = new Lancamento();
		this.pessoa = new Pessoa();
	}

	/*
	 * public void consultaLancamentoUsuario() { this.lancamentos =
	 * lancamentosRepository.lancePessoa(pessoa); //this.lancamentos =
	 * lancamentosRepository.todosLanc(); this.todasPessoas =
	 * this.pessoas.todasPessoas(); this.lancamento = new Lancamento();
	 * this.pessoa = new Pessoa(); }
	 */

	public void extrato() {
		this.lancamentos = lancamentosRepository.lancamentoUsuario(usuario);
		//this.lancamentos = lancamentosRepository.lancePessoa(pessoa);
		// this.lancamentos = lancamentosRepository.todosLanc();
		this.todosUsuarios = this.usuarios.todosUsuarios();
		this.todasPessoas = this.pessoas.todasPessoas();
		this.lancamento = new Lancamento();
		this.usuario = new Usuario();
		//this.pessoa = new Pessoa();
	}

	public void consultaLancamentoUsuario() {
		this.lancamentos = lancamentosRepository.lancamentoUsuario(usuario);
		this.todosUsuarios = this.usuarios.todosUsuarios();
		// this.todasPessoas = this.pessoas.todasPessoas();
		this.usuario = new Usuario();
		// this.pessoa = new Pessoa();
		this.lancamento = new Lancamento();
		// lancamentoUsuario
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	/*
	 * public List<String> pesquisarDescricoes(String descricao) { return
	 * this.lancamentos.descricoesQueContem(descricao); }
	 */

	public void calcula() {
		// total = lancamentosRepository.calculaTotalMovimentado(tipoLancamento,
		// pessoa);
		total = lancamentosRepository.calculaTotalMovimentado(tipoLancamento,
				pessoa);

		// lucro = lancamentosRepository.lucroTotal(pessoa);

	}

	public void calculaLucro() throws NegocioException {

		// FacesContext context = FacesContext.getCurrentInstance();
		// Aqui faz a busca em lancamento pela Pessoa
		//this.lancamentos = lancamentosRepository.lancePessoa(pessoa);
		this.lancamentos = lancamentosRepository.lancamentoUsuario(usuario);
		/*Lucro por PEssoa
		 * lucro = lancamentosRepository.lucroTotal(tipoLancamento, pessoa);

		total = lancamentosRepository.Lucro(tipoLancamento, pessoa);

		saldoNegativos = lancamentosRepository.saldoNegativo(tipoLancamento,
				pessoa);*/
		//Por usuários
		lucro = lancamentosRepository.lucroTotal(tipoLancamento, usuario);

		total = lancamentosRepository.Lucro(tipoLancamento, usuario);

		saldoNegativos = lancamentosRepository.saldoNegativo(tipoLancamento,
				usuario);

	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getLucro() {
		return lucro;
	}

	public void setLucro(BigDecimal lucro) {
		this.lucro = lucro;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}

	public List<Usuario> getTodosUsuarios() {
		return this.todosUsuarios;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	/**
	 * @return the pessoa
	 */

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

}