package nl.han.student;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StudentForm {

    @NotNull(message = "Het studentnummer mag niet leeg zijn.")
    @Pattern(regexp = "[0-9]{6}", message = "Studentnummer moet exact 6 cijfers zijn.")
    private String nummer;

    @NotNull(message = "Naam mag niet leeg zijn.")
    @NotBlank(message = "Naam mag niet leeg zijn.")
    private String naam;

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNummer() {
        return nummer;
    }

    public String getNaam() {
        return naam;
    }
}
