package br.com.augusto.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	 @Value("${spring.application.name}")
	    String appName;
	@GetMapping("/")
    public String homePage(Model model) {
       /*model.addAttribute("appName", appName);
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       if (principal instanceof UserDetails) {
           String nome = ((UserDetails)principal).getUsername();
           Collection<? extends GrantedAuthority> credenciais = ((UserDetails) principal).getAuthorities();           
           
           for (GrantedAuthority grantedAuthority : credenciais) {
        	   System.out.println(grantedAuthority.getAuthority());
           }
           
       } else {
           String nome = principal.toString();
           System.out.println("teste"+nome);
       }*/
       
        return "index";
    }
	
}
