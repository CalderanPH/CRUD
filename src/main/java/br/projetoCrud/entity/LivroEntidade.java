package br.projetoCrud.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "livro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroEntidade implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name="livro_nome")
    private String nome;

}
