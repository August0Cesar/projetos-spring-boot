package br.com.augusto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.augusto.models.Usuario;
import br.com.augusto.repository.UsuarioRepository;

@Controller
public class HomeController {
	@Value("${spring.application.name}")
	String appName;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping(value = { "/", "/login" })
	public String homePage(Model model) {

		model.addAttribute("appName", appName);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String nome = ((UserDetails) principal).getUsername();
			model.addAttribute("usuario", nome);
			Collection<? extends GrantedAuthority> credenciais = ((UserDetails) principal).getAuthorities();
			System.out.println("Logado com " + nome);
			for (GrantedAuthority grantedAuthority : credenciais) {
				if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
					System.out.println("Logado com " + nome);
					return "redirect:/postFileAdmin";
				}
			}
			Usuario usuario = usuarioRepository.findByLogin(nome);
			return "forward:/usuario/" + usuario.getId();

		} else {
			String nome = principal.toString();
			System.out.println("teste " + nome);
		}

		return "index";
	}

	@PostMapping("/login")
	public String login() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			String nome = ((UserDetails) principal).getUsername();
			Collection<? extends GrantedAuthority> credenciais = ((UserDetails) principal).getAuthorities();
			for (GrantedAuthority grantedAuthority : credenciais) {
				
				if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
					System.out.println("Logado com " + nome);
					return "redirect:/admin/postFileAdmin";
				}
			}
			Usuario usuario = usuarioRepository.findByLogin(nome);
			return "forward:/usuario/" + usuario.getId();

		} else {
			String nome = principal.toString();
			System.out.println("teste" + nome);
			return "index";
		}
	}

}
