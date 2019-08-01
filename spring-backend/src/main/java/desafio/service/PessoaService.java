package desafio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

import desafio.model.*;
import desafio.repository.*;
import desafio.criteria.*;

import org.springframework.data.domain.Sort.Direction;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public Page<Pessoa> findAllPage(Pageable page) {
        return repository.findAll(page);
    }

    public Iterable<Pessoa> findAllFiltered(String nome, String email, String cpf) {
        return repository.findByNomeOrEmailOrCpfAllIgnoreCase(nome, email, cpf);
    }

    public Optional<Pessoa> findById(Long id) {

        return repository.findById(id);

    }

    public Pessoa save(Pessoa p) {
        
        return repository.save(p);

    }

    public List<Pessoa> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        List<Pessoa> pessoaList = repository.findAll(pageable).getContent();
        return pessoaList;
    }

    public boolean cpfExistente(String cpf) {
        return repository.countByCpf(cpf) != 0;
    }

    public List<Pessoa> findAllPagina(int pagina, int quantidade, String nome, String email, String cpf, String ordem, String direcao) {
        Specification<Pessoa> specFinal;
        PessoaSpecification specNome, specEmail, specCpf, specNaoRemovido;

        if (!(nome == null)) {
            specNome = new PessoaSpecification(new SearchCriteria("nome", ":", nome));

        } else {
            specNome = new PessoaSpecification(null);
        }

        if (!(email == null)) {
            specEmail = new PessoaSpecification(new SearchCriteria("email", ":", email));
        } else {
            specEmail = new PessoaSpecification(null);
        }

        if (!(cpf == null)) {
            specCpf = new PessoaSpecification(new SearchCriteria("cpf", ":", cpf));
        } else {
            specCpf = new PessoaSpecification(null);
        }

        specNaoRemovido = new PessoaSpecification(new SearchCriteria("removido", ":", false));
        
        specFinal = specNome.and(specEmail).and(specCpf).and(specNaoRemovido);
      
        Pageable pageable;
        if(direcao.equals("desc")){
            pageable = PageRequest.of(pagina, quantidade, Direction.DESC, ordem);
        }
        else{
            pageable = PageRequest.of(pagina, quantidade, Direction.ASC, ordem);
        }

        List<Pessoa> pessoaList = repository.findAll(specFinal, pageable).getContent();

        return pessoaList;

    }

    public long countFilter(String nome, String email, String cpf) {
        Specification<Pessoa> specFinal;
        PessoaSpecification specNome, specEmail, specCpf, specNaoRemovido;

        if (!(nome == null)) {
            specNome = new PessoaSpecification(new SearchCriteria("nome", ":", nome));

        } else {
            specNome = new PessoaSpecification(null);
        }

        if (!(email == null)) {
            specEmail = new PessoaSpecification(new SearchCriteria("email", ":", email));
        } else {
            specEmail = new PessoaSpecification(null);
        }

        if (!(cpf == null)) {
            specCpf = new PessoaSpecification(new SearchCriteria("cpf", ":", cpf));
        } else {
            specCpf = new PessoaSpecification(null);
        }

        specNaoRemovido = new PessoaSpecification(new SearchCriteria("removido", ":", false));
        specFinal = specNome.and(specEmail).and(specCpf).and(specNaoRemovido);

        long count = repository.count(specFinal);

        return count;

    }

}