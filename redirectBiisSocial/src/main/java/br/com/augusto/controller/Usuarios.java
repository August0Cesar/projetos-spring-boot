package br.com.augusto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.augusto.model.Usuario;

@RestController("/api")
public class Usuarios {
	@Autowired
	private ServletContext servletContext;

	@RequestMapping("/usuarios")
	public List<Usuario> usuariosAll() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("Petter Parker", "homenaranha@gmail.com", "redirectbiissocial.herokuapp.com/download/spring-boot"));
		usuarios.add(new Usuario("Wade Winston Wilson", "deadpool@gmail.com", "redirectbiissocial.herokuapp.com/download/angular"));
		usuarios.add(new Usuario("Henry \"Hank\" Pym", "homenformiga@gmail.com", "redirectbiissocial.herokuapp.com/download/java"));
		usuarios.add(new Usuario("T'Challa", "panteranegra@gmail.com", "redirectbiissocial.herokuapp.com/download/javascript"));

		return usuarios;
	}

	@RequestMapping(value = "/download/{nome}", method = RequestMethod.GET)
	public HttpEntity<byte[]> download(@PathVariable String nome) throws IOException {

		byte[] arquivo = Files.readAllBytes(Paths.get("src/main/resources/static/imagens/" + nome + ".jpeg"));

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("Content-Disposition", "attachment;filename=" + nome + ".jpeg");

		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

		return entity;
	}
}
