package cat.politecnicllevant.prova.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller fa que aquesta classe sigui un Bean que controli el framework i a més permet que mapegi petición HTTP
// i retorni viestes HTML (amb Thymeleaf) com a resposta. Molt semblant al que heu fet amb servlets.
@Controller
@RequestMapping("/mostra")
public class SampleController {

    // exemple GET rebent paràmetres en el cos de la request.
    @GetMapping()
    public String hola(
            @RequestParam(name = "nom", required = false) String name,
            @RequestParam(name="edat",required = false, defaultValue = "0") int age,
            Model model)
    {
        System.out.println(name);
        System.out.println(age);

        model.addAttribute("nom", name);
        model.addAttribute("edat", age);

        return "hola";
    }

    //exemple GET rebent variables al path de la url
    @GetMapping("/users/{id}/{nom}")
    public String users(@PathVariable int id, @PathVariable String nom, Model model){
        System.out.println(id);
        System.out.println(nom);
        return "users/usersList";
    }
}