package br.com.augusto.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.augusto.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

	Usuario findByLoginAndSenha(String login,String senha);

	Usuario findByLogin(String login);
}
