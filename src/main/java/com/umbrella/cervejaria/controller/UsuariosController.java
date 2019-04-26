package com.umbrella.cervejaria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umbrella.cervejaria.model.Usuario;
import com.umbrella.cervejaria.repository.Grupos;
import com.umbrella.cervejaria.service.CadastroUsuarioService;
import com.umbrella.cervejaria.service.exception.EmailUsuarioJaCadastradoException;
import com.umbrella.cervejaria.service.exception.SenhaObrigatoriaUsuarioException;


@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	//Parametros/////////////////////////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired 
	private Grupos grupos; 

	//Funções//////////////////////////////////////////////////////////////////////////////////////////////
	
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
}









