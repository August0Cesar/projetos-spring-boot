package br.com.augusto.api_teste.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "car")
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String nome;
    private String estado;
    private String cor;
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFabricacao;
    
	public Car() {
		super();
	}

	public Car(Long id, String nome, String estado, String cor, Date dataFabricacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
		this.cor = cor;
		this.dataFabricacao = dataFabricacao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Date getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(Date dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
    
    
    
}