package br.com.augusto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.augusto.models.Arquivos;
import br.com.augusto.models.Empresas;
import br.com.augusto.models.Role;
import br.com.augusto.models.Usuario;
import br.com.augusto.repository.ArquivoRepository;
import br.com.augusto.repository.EmpresaRepository;
import br.com.augusto.repository.RoleRepository;
import br.com.augusto.repository.UsuarioRepository;
import br.com.augusto.responseDtos.EmpresaResposeDto;

@Controller
public class AdminController {
	final String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
			"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
			"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
			"Y", "Z" };
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	ArquivoRepository arquivoRepository;

	@Autowired
	RoleRepository roleRepository;

	private static final String UPLOADED_FOLDER = "C:\\Users\\augusto\\Documents\\workspace-sts-3.9.3.RELEASE\\MentsConsultoriaVersaoBanco\\src\\main\\resources\\static\\filesEmpresa\\";
	//private static final String UPLOADED_FOLDER_PRODUCAO = "C:\\Users\\augusto\\Documents\\workspace-sts-3.9.3.RELEASE\\MentsConsultoriaVersaoBanco\\src\\main\\resources\\static\\filesEmpresa\\";

	@GetMapping("/postFileAdmin")
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/postFileAdmin");
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String nome = ((UserDetails) principal).getUsername();
			model.addObject("usuario", nome);
			List<Empresas> empresas = (List<Empresas>) empresaRepository.findAll();
			List<EmpresaResposeDto> dtos = new ArrayList<>();
			for (Empresas empresa : empresas) {
				EmpresaResposeDto dto = new EmpresaResposeDto(empresa);
				dtos.add(dto);
			}
			model.addObject("empresas", dtos);
		}

		return model;
	}

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("empresaId") Integer empresaId, RedirectAttributes redirectAttributes) {
		if(empresaId == null) {
			redirectAttributes.addFlashAttribute("message", "Por Favor selecione uma empresa");
			return "redirect:uploadStatus";
		}
		
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Por Favor selecione um arquivo para upload");
			return "redirect:uploadStatus";
		}
		Empresas empresa = empresaRepository.findOne(empresaId);
		
		String arquivoOriginal = file.getOriginalFilename();
		arquivoOriginal = arquivoOriginal.replace(".", ";");
		arquivoOriginal = arquivoOriginal.replace(" ", "");
		String[] arq = arquivoOriginal.split(";");
		
		Arquivos arquivo = new Arquivos();
		arquivo.setCaminhoArquivo(UPLOADED_FOLDER + file.getOriginalFilename());
		arquivo.setDataPostagem(new Date());
		arquivo.setEmpresa(empresa);
		arquivo.setNomeArquivo(arq[0]);
		arquivo.setExtenssaoArquivo("." + arq[1]);
		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		arquivoRepository.save(arquivo);

		return "redirect:/anexarArquivos/" + empresa.getId();
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "admin/uploadStatus";
	}

	@PostMapping("/cadastraEmpresa") // //new annotation since 4.3
	public String cadastraEmpresa(@RequestParam("nomeEmpresa") String nomeEmpresa,
			@RequestParam("telefoneEpresa") String telefoneEpresa,
			@RequestParam("cnpjEmpresa") String cnpjEmpresa,
			@RequestParam("razaoSocialEmpresa") String razaoSocialEmpresa,
			@RequestParam("emailEmpresa") String emailEmpresa,
			@RequestParam("nomeEmpresario") String nomeEmpresario) {
		
		Role roleUsuario = new Role();
		roleUsuario = roleRepository.findByNomeRole("ROLE_USER");
		List<Role> listaPermissoes = new ArrayList<>();
		listaPermissoes.add(roleUsuario);
		
		String senha = geraSenha();
		Usuario usuario = new Usuario(geraLogin(nomeEmpresario),nomeEmpresario,true
					,new BCryptPasswordEncoder().encode(geraSenha()),listaPermissoes,senha);
		
		List<Usuario> listausuario = new ArrayList<Usuario>();
		listausuario.add(usuarioRepository.save(usuario));
		
		Empresas empresa = new Empresas(nomeEmpresa,nomeEmpresario,razaoSocialEmpresa,cnpjEmpresa,telefoneEpresa,new Date(),listausuario,emailEmpresa);
		empresaRepository.save(empresa);
		usuario.setEmpresas(empresa);
		usuarioRepository.save(usuario);
		return "redirect:/postFileAdmin";
	}

	private String geraSenha() {
		
		String senha = "";

		for (int x = 0; x < 8; x++) {
			int j = (int) (Math.random() * this.carct.length);
			senha += this.carct[j];
		}
		return senha;
	}
	
	private String geraLogin(String nomeEmpresario) {
		nomeEmpresario = nomeEmpresario.replace(" ", ";");
		String login[] = nomeEmpresario.split(";");
		String loginUsuario = login[0];
		for (int x = 0; x < 2; x++) {
			int j = (int) (Math.random() * this.carct.length);
			loginUsuario += carct[j];
		}
		return loginUsuario;
	}
	
	@GetMapping("/anexarArquivos/{id}")
	public ModelAndView cadastraEmpresa(@PathVariable("id") Integer idEmpresa){
		System.out.println("Id da empresa "+ idEmpresa);
		ModelAndView model = new ModelAndView();
		model.setViewName("admin/uploadStatusAdmin");
		Empresas empresa = empresaRepository.findOne(idEmpresa);
		List<Arquivos> arquivos = arquivoRepository.findByEmpresa(empresa);
		model.addObject("empresa", empresa);
		model.addObject("arquivos", arquivos);
		return model;
	}
}
