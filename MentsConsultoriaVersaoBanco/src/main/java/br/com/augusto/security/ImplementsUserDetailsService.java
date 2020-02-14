package br.com.augusto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.augusto.models.Usuario;
import br.com.augusto.repository.UsuarioRepository;

/**
 * 
 * @author Classe responsavel por fazer a autenticação do usuario
 *
 */
@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {
	@Autowired
	UsuarioRepository daoUsuario;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = daoUsuario.findByLogin(login);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario nao encontrado!");
		}

		return new User(usuario.getUsername(),usuario.getPassword(),true,true,true,true,usuario.getAuthorities());
	}

}
