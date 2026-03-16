package com.biopark.alunos.controller;

import com.biopark.alunos.model.Aluno;
import com.biopark.alunos.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // LISTAR
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", alunoService.listarTodos());
        return "alunos/lista";
    }

    // FORMULÁRIO NOVO
    @GetMapping("/novo")
    public String novoFormulario(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "alunos/formulario";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirect) {

        return alunoService.buscarPorId(id)
                .map(aluno -> {
                    model.addAttribute("aluno", aluno);
                    return "alunos/formulario";
                })
                .orElseGet(() -> {
                    redirect.addFlashAttribute("erro", "Aluno não encontrado");
                    return "redirect:/alunos";
                });
    }

    // SALVAR (CRIAR OU ALTERAR)
    @PostMapping("/salvar")
    public String salvar(@Valid Aluno aluno,
                         BindingResult result,
                         RedirectAttributes redirect) {

        if (result.hasErrors()) {
            return "alunos/formulario";
        }

        boolean novo = aluno.getId() == null;

        alunoService.salvar(aluno);

        redirect.addFlashAttribute("mensagem",
                novo ? "Aluno criado!" : "Aluno atualizado!");

        return "redirect:/alunos";
    }

    // EXCLUIR
    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id,
                          RedirectAttributes redirect) {

        alunoService.excluir(id);

        redirect.addFlashAttribute("mensagem", "Aluno removido!");

        return "redirect:/alunos";
    }
}