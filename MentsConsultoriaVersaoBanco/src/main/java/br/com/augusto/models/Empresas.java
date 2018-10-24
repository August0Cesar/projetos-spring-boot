package br.com.augusto.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Empresas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome_empresa;
	private String nome_empresario;
	private String razao_social;
	private String cnpj;
	private String telefone;
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@OneToMany(mappedBy = "empresas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Usuario> usuarioEmpresa;

	public Empresas() {
	}

	public Empresas(String nome_empresa, String nome_empresario, String razao_social, String cnpj, String telefone,
			Date dataCadastro, List<Usuario> usuarioEmpresa,String emailEmpresa) {
		super();
		this.nome_empresa = nome_empresa;
		this.nome_empresario = nome_empresario;
		this.razao_social = razao_social;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.dataCadastro = dataCadastro;
		this.usuarioEmpresa = usuarioEmpresa;
		this.email = emailEmpresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome_empresario() {
		return nome_empresario;
	}

	public void setNome_empresario(String nome_empresario) {
		this.nome_empresario = nome_empresario;
	}

	public List<Usuario> getUsuarioEmpresa() {
		return usuarioEmpresa;
	}

	public void setUsuarioEmpresa(List<Usuario> usuarioEmpresa) {
		this.usuarioEmpresa = usuarioEmpresa;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome_empresa() {
		return nome_empresa;
	}

	public void setNome_empresa(String nome_empresa) {
		this.nome_empresa = nome_empresa;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	

}
