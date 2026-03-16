package com.biopark.alunos.service;

import com.biopark.alunos.model.Aluno;
import com.biopark.alunos.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void salvar(Aluno aluno) {
        repository.save(aluno);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}