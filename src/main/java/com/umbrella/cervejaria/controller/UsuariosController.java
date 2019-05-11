package com.umbrella.cervejaria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umbrella.cervejaria.model.Usuario;
import com.umbrella.cervejaria.repository.Grupos;
import com.umbrella.cervejaria.repository.Usuarios;
import com.umbrella.cervejaria.repository.filter.UsuarioFilter;
import com.umbrella.cervejaria.service.CadastroUsuarioService;
import com.umbrella.cervejaria.service.StatusUsuario;
import com.umbrella.cervejaria.service.exception.EmailUsuarioJaCadastradoException;
import com.umbrella.cervejaria.service.exception.SenhaObrigatoriaUsuarioException;


@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	//Parametros
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired 
	private Grupos grupos; 
	
	@Autowired
	private Usuarios usuarios;

	//Funções
	
	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("grupos", grupos.findAll());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes ){
		if(result.hasErrors()) {
			return novo(usuario);
		}
		
		try {
			cadastroUsuarioService.salvar(usuario);
		}catch(EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		}catch(SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/usuarios/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter) {
		
		ModelAndView mv = new ModelAndView("usuario/PesquisaUsuarios");
		
		mv.addObject("usuarios", usuarios.filtrar(usuarioFilter));
		mv.addObject("grupos", grupos.findAll());

		return mv;
	}
	
	@PutMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos, @RequestParam("status") StatusUsuario statusUsuario) {
		
		cadastroUsuarioService.alterarStatus(codigos, statusUsuario);
	}
}









