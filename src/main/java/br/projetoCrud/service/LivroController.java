package br.projetoCrud.service;



import br.projetoCrud.entity.LivroEntidade;
import br.projetoCrud.livro.Livro;
import br.projetoCrud.repository.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping()
public class LivroController {

    private Set<Livro> livro;
    private final LivroRepository repository;

    public LivroController(LivroRepository repository){
        livro = new HashSet<>();
        this.repository = repository;
    }

    @GetMapping()
    public List findAll(){

        return repository.findAll();

    }


    @PostMapping
    public Livro create(@RequestBody Livro livro){
        return repository.save(livro);
    }



    @PutMapping("/livro/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Livro livro){
        return repository.findById(id)
                .map(record -> {
                    record.setName(livro.getNome());
                    LivroEntidade updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);


                }).orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/livro/{id}")
    public ResponseEntity <?> delete(PathVariable long id){
        return  repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
