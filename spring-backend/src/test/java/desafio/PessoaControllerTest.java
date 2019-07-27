package desafio;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import desafio.model.Pessoa;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    private MockMvc mvc;

    

    @Test
    public void verificarCpfRepetido() throws Exception {
        
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Pessoa p = new Pessoa("kevin", "k@b", "44861936012", format.parse("2016-05-05"));

    mvc.perform( MockMvcRequestBuilders.post("/Pessoa")
      .content(asJsonString(p))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());

      mvc.perform( MockMvcRequestBuilders.post("/Pessoa")
      .content(asJsonString(p))
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON))
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
