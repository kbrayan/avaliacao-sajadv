package desafio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import desafio.model.*;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa>  {

    List<Pessoa> findByEmail(String email);
    List<Pessoa> findByNome(String email);
    
    Page<Pessoa> findByRemovidoAllIgnoreCase(boolean removido, Pageable pageable);

    int countByRemovido(boolean removido);

    List<Pessoa> findFirst2ByNomeOrEmailAllIgnoreCase(String nome, String email);

    List<Pessoa> findByNomeOrEmailAllIgnoreCase(String nome, String email);

    List<Pessoa>findByNomeOrEmailOrCpfAllIgnoreCase(String nome, String email, String cpf);

    int countByCpf(String cpf);
        
}
