package br.com.agenda.controller;

import br.com.agenda.model.Cadastro;
import br.com.agenda.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/cadastro")
    public String exibirFormulario(Model model) {
        model.addAttribute("cadastro", new Cadastro());
        return "/cadastro";
    }

    @PostMapping("/cadastro")
    public String salvarCadastro(@ModelAttribute Cadastro cadastro) {
        cadastroService.salvar(cadastro);
        return "redirect:/cadastro";
    }
}
