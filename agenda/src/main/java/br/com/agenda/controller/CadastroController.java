package br.com.agenda.controller;
import br.com.agenda.model.Cadastro;
import br.com.agenda.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("cadastros", cadastroService.listarTodos());
        return "listar";
    }


    @PostMapping("/cadastro")
    public String salvarCadastro(@ModelAttribute Cadastro cadastro) {
        cadastroService.salvar(cadastro);
        return "redirect:/cadastro";
    }

    @GetMapping("/deletar")
    public String exibirFormularioDelecao() {
        return "deletar";
    }

    @PostMapping("/deletar")
    public String deletarCadastroPorId(@RequestParam Long id) {
        cadastroService.deletar(id);
        return "redirect:/listar";
    }
    @GetMapping("/buscar")
    public String exibirFormularioBusca() {
        return "buscar";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam Long id, Model model) {
        Optional<Cadastro> cadastroOptional = cadastroService.buscar(id);

        if (cadastroOptional.isPresent()) {
            model.addAttribute("cadastro", cadastroOptional.get());
        } else {
            model.addAttribute("mensagemErro", "Cadastro não encontrado.");
        }

        return "buscar";
    }

    @GetMapping("/alterar")
    public String exibirFormularioBuscar() {
        return "alterar";
    }
    @PostMapping("/buscar-id")
    public String buscarParaAlterar(@RequestParam("id") Long id, Model model) {
        Optional<Cadastro> cadastroOptional = cadastroService.buscar(id);
        if (cadastroOptional.isPresent()) {
            model.addAttribute("cadastro", cadastroOptional.get());
        } else {
            model.addAttribute("erro", "Cadastro não encontrado.");
        }
        return "alterar";
    }

    @PostMapping("/alterar")
    public String salvarAlteracao(@RequestParam("id") Long id,
                                  @RequestParam("nome") String nome,
                                  @RequestParam("email") String email,
                                  Model model) {

        Optional<Cadastro> cadastroOptional = cadastroService.buscar(id);
        if (cadastroOptional.isPresent()) {
            Cadastro cadastro = cadastroOptional.get();
            if (!nome.isBlank()) cadastro.setNome(nome);
            if (!email.isBlank()) cadastro.setEmail(email);
            cadastroService.salvar(cadastro);
            model.addAttribute("mensagem", "Cadastro alterado com sucesso!");
            model.addAttribute("cadastro", cadastro);
        } else {
            model.addAttribute("erro", "Cadastro não encontrado.");
        }
        return "alterar";
    }
}


