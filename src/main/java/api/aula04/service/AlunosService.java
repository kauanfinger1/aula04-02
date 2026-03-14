package api.aula04.service;

import api.aula04.model.AlunoModel;
import api.aula04.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunosService {

    private final AlunoRepository alunoRepository;

    public AlunosService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoModel> listarTodos() {
        return alunoRepository.findAll();
    }

    public void salvar(AlunoModel aluno) {
        alunoRepository.save(aluno);
    }

    public Optional<AlunoModel> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    public void excluir(Long id) {
        alunoRepository.deleteById(id);
    }
}