package br.com.augusto.serviceSecurity;

import br.com.augusto.models.Usuario;

public interface UserService {

	public Usuario findUserByLoginAndSenha(String login,String senha);

	public void saveUsuario(Usuario usuário);
}
