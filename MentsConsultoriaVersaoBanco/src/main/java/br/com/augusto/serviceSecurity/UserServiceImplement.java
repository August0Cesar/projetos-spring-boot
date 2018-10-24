package br.com.augusto.serviceSecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.augusto.models.Usuario;
import br.com.augusto.repository.RoleRepository;
import br.com.augusto.repository.UsuarioRepository;

public class UserServiceImplement implements UserService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	RoleRepository roleRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Usuario findUserByLoginAndSenha(String login, String senha) {
		return usuarioRepository.findByLoginAndSenha(login, senha);
	}

	@Override
	public void saveUsuario(Usuario user) {
		user.setSenha(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		usuarioRepository.save(user);

	}

}
