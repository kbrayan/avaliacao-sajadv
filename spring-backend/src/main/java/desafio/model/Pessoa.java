package desafio.model;
import lombok.Getter;
import lombok.Setter;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.br.CPF;



@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @NotBlank(message = "Nome e obrigatorio")
    @Size(max=150, message = "Nome pode conter no maximo 150 caracteres")
    @Getter
    @Setter
    private String nome;

    @NotBlank(message = "email e obrigatorio")
    @Size(max=400, message = "Nome pode conter no maximo 400 caracteres")
    @Email(message = "email invalido")
    @Getter
    @Setter
    private String email;

    @CPF(message = "cpf invalido")
    @NotBlank(message = "cpf e obrigatorio")
    @Getter
    @Setter
    private String cpf;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    private Date nascimento;

    @Getter
    @Setter
    private Boolean removido;

    protected Pessoa() {}


    public Pessoa(String nome, String email, String cpf, Date date) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.nascimento = date;
        this.removido = false;

    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return String.format(
                "Pessoa[id=%d, nome='%s', email='%s', cpf='%s', nascimento='%s', removido='%s']",
                id, nome, email,cpf, format.format(nascimento),removido);
    }

}
