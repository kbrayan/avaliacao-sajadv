package desafio.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import desafio.service.*;
import desafio.Application;
import desafio.model.*;
import desafio.repository.*;




@CrossOrigin
@RestController
public class PessoaController {
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @Autowired
    private PessoaRepository repository;

    @Autowired
    private PessoaService pService;
    
    @CrossOrigin
    @GetMapping("/pessoasFiltrado")
    @ResponseBody
    public Iterable<Pessoa> findFiltrado(@RequestParam Map<String,String> parametrosDeFiltro) {
 
        String nome = parametrosDeFiltro.get("nome").toLowerCase() ;
        String cpf = parametrosDeFiltro.get("cpf").toLowerCase() ;
        
        String email = parametrosDeFiltro.get("email").toLowerCase() ;

        return pService.findAllFiltered( nome, email, cpf);
    }

    @CrossOrigin
    @RequestMapping("/pessoas")
    public Iterable<Pessoa> indexa(@RequestParam int page, @RequestParam int size) {
    
        Pageable pageable = PageRequest.of(page, size);

        List<Pessoa> pessoaList = pService.findAllPage(pageable).getContent(); 

        return pessoaList;
    }

   @CrossOrigin
   @RequestMapping(value="/Pessoa/{id}")
   public Optional<Pessoa> PessoaById(@PathVariable("id") long id){
	return pService.findById(id);
   }

   @CrossOrigin
   @PostMapping("/Pessoa")
   public ResponseEntity<Pessoa> create(@Valid @RequestBody Pessoa pessoa){

    log.info("Requesicao criar Pessoa:");    
    log.info(pessoa.toString());

    if(pService.cpfExistente(pessoa.getCpf())){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    Pessoa p = pService.save(pessoa);

   	return ResponseEntity.ok().body(p);
   }


  @CrossOrigin
  @PutMapping("Pessoa/{id}")
  public ResponseEntity<Pessoa> update(@PathVariable("id") long id,
                                        @RequestBody Pessoa pessoa){
                                            
    return pService.findById(id)
        .map(record -> {   
            record.setNome(pessoa.getNome());
            record.setEmail(pessoa.getEmail());
            record.setCpf(pessoa.getCpf()); 
            record.setNascimento(pessoa.getNascimento());
            record.setRemovido(pessoa.getRemovido());     
              
            Pessoa updated = pService.save(record);

            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
  }

  @CrossOrigin
  @DeleteMapping("Pessoa/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") long id) {
    return pService.findById(id)
        .map(record -> {

            //TODO:remove repository from controller
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
  }
  
  @CrossOrigin
  @DeleteMapping("SoftDeletePessoa/{id}")
  public ResponseEntity<?> softDelete(@PathVariable("id") long id) {
    return pService.findById(id)
        .map(record -> {   
            
            record.setRemovido(true);     
              
            Pessoa updated = pService.save(record);

            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
  }

   @CrossOrigin
   @RequestMapping(value="/PessoasPage/{page}")
   public List<Pessoa> PessoaAll(@PathVariable("page") int page){

    List<Pessoa> pessoaList = pService.findAll(page);
    return pessoaList;
    
   }


   @CrossOrigin
   @RequestMapping(value="/PessoasFilterPage")
   public List<Pessoa> PessoasFilterPage(
        @RequestParam(required = true) int pagina,
        @RequestParam(required = true) int quantidade,
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String cpf
        )

        {
        
        if(nome==null){nome="";}
        if(email==null){email="";}
        if(cpf==null){cpf="";}
        
        
        
	    return pService.findAllPagina(pagina,quantidade,nome,email,cpf);
   }

   @CrossOrigin
   @RequestMapping(value="/CountFilterPage")
   public long CountFilterPage(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String cpf
        )

        {
        
        if(nome==null){nome="";}
        if(email==null){email="";}
        if(cpf==null){cpf="";}

        log.warn("Requisicao de Contagem");
        log.warn(nome);
        log.warn(email);
        log.warn(cpf);
        
	    return pService.countFilter(nome,email,cpf);
   }
   




}









