package api.aula04.controller;

import api.aula04.model.AlunoModel;
import api.aula04.service.AlunosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunosService service;

    public AlunoController(AlunosService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alunos", service.listarTodos());
        return "alunos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("aluno", new AlunoModel());
        return "alunos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(AlunoModel aluno) {
        service.salvar(aluno);
        return "redirect:/alunos";
    }

    @PutMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        var alunoOpt = service.buscarPorId(id);
        if (alunoOpt.isPresent()) {
            model.addAttribute("aluno", alunoOpt.get());
            return "alunos/formulario";
        } else {
            return "redirect:/alunos";
        }
    }

    @DeleteMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/alunos";
    }
}