package br.com.augusto.model;

public class Usuario {
	private String nome;
	private String email;
	private String imagen;

	public Usuario(String nome, String email, String imagen) {
		this.nome = nome;
		this.email = email;
		this.imagen = imagen;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
