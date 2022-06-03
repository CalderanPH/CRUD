package br.projetoCrud.livro;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class LivroController {

    private Set<Livro> livro;
    private final LivroRepository repository;

    public LivroController(LivroRepository repository){
        livro = new HashSet<>();
        this.repository = repository;
    }

    @GetMapping("/")
    public String listar(Model memoria){

        memoria.addAttribute("litaLivros", repository
                .findAll()
                .stream()
                .map(livro -> new Livro(livro.getNome()))
                .collect(Collectors.toList()));

        return  "/crud";
    }

    @PostMapping("/criar")
    public String criar(Livro livro, BindingResult validacao, Model memoria){
        if(validacao.hasErrors()){
            validacao
                    .getFieldErrors()
                    .forEach(error ->
                            memoria.addAttribute(
                                    error.getField(),
                                    error.getDefaultMessage())
                    );

            memoria.addAttribute("nomeInformado", livro.getNome());
            memoria.addAttribute("listaLivros", livro);

            return ("/crud");
        }else{
            repository.save(livro.clonar());
        }
        return "redirect:/";
    }
}
