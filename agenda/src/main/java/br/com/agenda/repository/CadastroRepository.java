package br.com.agenda.repository;

import br.com.agenda.model.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
}
