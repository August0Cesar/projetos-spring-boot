package br.com.augusto.responseDtos;

import br.com.augusto.models.Empresas;
import br.com.augusto.utils.DateUtiuls;

public class EmpresaResposeDto {
	private Integer idEmpresa;
	private String nomeEmpresa;
	private String nomeEmpresario;
	private String telefone;
	private String usuario;
	private String senha;
	private String status;
	private String dataCadastro;

	public EmpresaResposeDto(Empresas empresa) {
		super();
		this.idEmpresa = empresa.getId();
		this.nomeEmpresa = empresa.getNome_empresa();
		this.nomeEmpresario = empresa.getNome_empresario();
		this.telefone = empresa.getTelefone();
		this.dataCadastro = empresa.getDataCadastro() != null ? DateUtiuls.formatDate(empresa.getDataCadastro()) : "noa possui data";
		if(!empresa.getUsuarioEmpresa().isEmpty()) {
			this.usuario = empresa.getUsuarioEmpresa().get(0).getLogin();
			this.senha = empresa.getUsuarioEmpresa().get(0).getSenhaNoCripted();
			this.status = empresa.getUsuarioEmpresa().get(0).getActive().toString();
		}
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setNomeEmpresario(String nomeEmpresario) {
		this.nomeEmpresario = nomeEmpresario;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeEmpresario() {
		return nomeEmpresario;
	}

	public void setNome_empresario(String nomeEmpresario) {
		this.nomeEmpresario = nomeEmpresario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
