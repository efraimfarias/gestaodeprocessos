package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class Responsavel extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsavel_seq")
    public Long id;

    @Constraints.Required
    public String cpf;

    @Constraints.Required
    public String nome;

    @Constraints.Required
    public String email;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date data_nascimento = new Date();

    public static final Finder<Long, Responsavel> find = new Finder<>(Responsavel.class);

}