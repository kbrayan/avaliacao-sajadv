package desafio;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import desafio.model.Pessoa;
import desafio.repository.PessoaRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void inserir_Pessoa() throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Pessoa p = new Pessoa("pessoa1", "p1@email.com", "44861936012", format.parse("2016-05-05"));

        mvc.perform( MockMvcRequestBuilders.post("/Pessoa")
        .content(asJsonString(p))
        .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("kevin")))
                .andExpect(jsonPath("$.cpf", is("44861936012")))
                .andExpect(jsonPath("$.nascimento", is("2016-05-05T03:00:00.000+0000")));

                
    }


    @Test
    public void verificar_Cpf_Repetido() throws Exception {
        
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Pessoa p = new Pessoa("pessoa2", "p2@email.com", "94125744017", format.parse("2016-05-05"));
    
        mvc.perform( MockMvcRequestBuilders.post("/Pessoa")
        .content(asJsonString(p))
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    
         
    mvc.perform( MockMvcRequestBuilders.post("/Pessoa")
      .content(asJsonString(p))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());

    }

    @Test
    public void email_invalido() throws Exception {
        
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Pessoa p = new Pessoa("pessoa3", "p3@email.com.", "10283073900", format.parse("2016-05-05"));
    
    mvc.perform( MockMvcRequestBuilders.post("/Pessoa")
        .content(asJsonString(p))
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    
    
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
