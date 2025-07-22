package br.com.agenda.service;
import br.com.agenda.model.Cadastro;
import br.com.agenda.repository.CadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {
    @Autowired
    private CadastroRepository cadastroRepository;

    public List<Cadastro> listarTodos(){
        return cadastroRepository.findAll();
    }

    public Cadastro salvar(Cadastro cadastro) {
        return cadastroRepository.save(cadastro);

    }
    public Optional<Cadastro> buscar(Long id) {
        return cadastroRepository.findById(id);
    }
    public void deletar(Long id) {
        cadastroRepository.deleteById(id);
    }
}



