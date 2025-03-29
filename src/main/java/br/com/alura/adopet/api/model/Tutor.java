package br.com.alura.adopet.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.alura.adopet.api.dto.AtualizaTutor;
import br.com.alura.adopet.api.dto.CadastroTutor;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tutores")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    @OneToMany(mappedBy = "tutor")
    @JsonManagedReference("tutor_adocoes")
    private List<Adocao> adocoes;

    public Tutor() {
    }

    public Tutor(CadastroTutor tutorDto) {
        this.nome = tutorDto.nome();
        this.telefone = tutorDto.telefone();
        this.email = tutorDto.email();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tutor tutor = (Tutor) o;
        return Objects.equals(id, tutor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Adocao> getAdocoes() {
        return adocoes;
    }

    public void setAdocoes(List<Adocao> adocoes) {
        this.adocoes = adocoes;
    }

    public void atualizar(AtualizaTutor tutorDto) {
        if (tutorDto.email() != null && tutorDto.email() != "") {
            this.email = tutorDto.email();
        }
        if (tutorDto.telefone() != null && tutorDto.telefone() != "") {
            this.telefone = tutorDto.telefone();
        }
        if (tutorDto.nome() != null && tutorDto.nome() != "") {
            this.nome = tutorDto.nome();
        }
    }

    public void validarTelefone(String telefone) {
        String regex = "\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}";
        if (!telefone.matches(regex)) {
            throw new IllegalArgumentException("Formato de telefone inválido");
        }
    }
}
