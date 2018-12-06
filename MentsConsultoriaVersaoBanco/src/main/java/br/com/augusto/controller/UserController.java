package br.com.augusto.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.augusto.models.Arquivos;
import br.com.augusto.models.Empresas;
import br.com.augusto.models.Usuario;
import br.com.augusto.repository.ArquivoRepository;
import br.com.augusto.repository.EmpresaRepository;
import br.com.augusto.repository.UsuarioRepository;

@Controller
public class UserController {
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	ArquivoRepository arquivoRepository;

	//private static final String UPLOADED_FOLDER = "C:\\Users\\augusto\\Documents\\workspace-sts-3.9.3.RELEASE\\MentsConsultoriaVersaoBanco\\src\\main\\resources\\static\\filesEmpresa\\";
	private static final String UPLOADED_FOLDER = "/home/augusto/filesMentsConsultoria/";//producao

	@RequestMapping(value = "/usuario/download/{idArquivo}", method = RequestMethod.GET)
	public HttpEntity<byte[]> download(@PathVariable Integer idArquivo) throws IOException {

		Arquivos arquivo = arquivoRepository.findOne(idArquivo);
		if (arquivo != null) {
			System.out.println("Nome do arquivo para download " + arquivo.getNomeArquivo());
			Path path = Paths.get(UPLOADED_FOLDER + arquivo.getNomeArquivo() + arquivo.getExtenssaoArquivo());
			byte[] arq = Files.readAllBytes(path);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Content-Disposition","attachment;filename=" + arquivo.getNomeArquivo() + arquivo.getExtenssaoArquivo());
			HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arq, httpHeaders);

			return entity;

		} else {
			return null;
		}

	}
	@GetMapping("/usuario/{id}")
	public ModelAndView cadastraEmpresa(@PathVariable("id") Integer userId){
		Usuario user = usuarioRepository.findOne(userId);
		System.out.println("Id da empresa "+ user.getEmpresas().getId());
		ModelAndView model = new ModelAndView();
		model.setViewName("user/mainUser");
		List<Arquivos> arquivos = arquivoRepository.findByEmpresa(user.getEmpresas());
		model.addObject("empresa", user.getEmpresas());
		model.addObject("arquivos", arquivos);
		model.addObject("usuario", user);
		return model;
	}
}
