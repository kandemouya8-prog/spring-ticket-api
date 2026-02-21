package sn.ucad.restou;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Bonjour le monde du dev !";
    }

    @GetMapping("/api/hello")
    public String helloApi() {
        return "Hello from Spring Boot API !";
    }

    @GetMapping("/api/info")
    public String infoApi(){
        return "Application : RestoU-Tickets | Version : 1.0 | Auteur : Mouya KANDE";
    }

    @GetMapping("/api/date")
    public String dateApi(){
        return "Date et heur actuelles : " +LocalDateTime.now();
    }

    @GetMapping("/api/luneka")
    public String helloLune(){
        return "Bonjour et bienvenu dans le monde du développement. Tout savoir sur notre cher Java !";
    }
}