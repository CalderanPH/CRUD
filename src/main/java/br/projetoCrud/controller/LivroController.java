package br.projetoCrud.controller;


import br.projetoCrud.entity.Livro;
import br.projetoCrud.repository.LivroRepository;
import br.projetoCrud.request.LivroPutRequestBody;
import br.projetoCrud.service.LivroService;
import br.projetoCrud.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;



@RestController
@RequestMapping("livros")
@Log4j2
@AllArgsConstructor
public class LivroController {
    private DateUtil dateUtil;
    private LivroService livroService;
    private LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<List<Livro>> list() {
        log.info(dateUtil.formatLocalDateTimeTODatebaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(livroService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable long id) {
        return ResponseEntity.ok(livroService.findByIdOrThorwBadRequestException(id));
    }

//    @GetMapping(path = "/{find}")
//    public ResponseEntity<List<Livro>> findByName(@RequestParam String name) {
//        return ResponseEntity.ok(livroService.findByName(name));
//    }

    @PostMapping
    public Livro create(@RequestBody Livro livro) {
       return livroRepository.save(livro);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        livroService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody LivroPutRequestBody livroPutRequestBody) {
        livroService.replace(livroPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//    @Autowired
//    private LivroRepository livroRepository;
//
//
//    @PostMapping
//    public Livro create(@RequestBody Livro livro) {
//        return livroRepository.save(livro);
//    }
//
//
//    @PutMapping("/livro/{id}")
//    public ResponseEntity<Livro> update(@PathVariable long id,
//                                        @RequestBody Livro livroDetails) {
//        Livro updateLivro = livroRepository.findById(id);
//
//        updateLivro.
//
//    }
//
////    @DeleteMapping("/livro/{id}")
////    public ResponseEntity <?> delete(PathVariable long id){
////        return  repository.findById(id)
////                .map(record -> {
////                    repository.deleteById(id);
////                    return ResponseEntity.ok().build();
////                }).orElse(ResponseEntity.notFound().build());
////    };
}
