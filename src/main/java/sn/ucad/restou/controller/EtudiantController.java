package sn.ucad.restou.controller;

import java.net.ResponseCache;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ucad.restou.entity.Etudiant ;
import sn.ucad.restou.service.EtudiantService;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService){
        this.etudiantService = etudiantService;
    }

    @GetMapping
    public Iterable<Etudiant> recupererTous(){
        return etudiantService.recupererTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> recupererParId(@PathVariable Long id){
        return etudiantService.recupererParId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Etudiant> creer(@RequestBody Etudiant etudiant){
        Etudiant nouveauEtudiant = etudiantService.creer(etudiant);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauEtudiant);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> mettreAJour(@PathVariable Long id, @RequestBody Etudiant etudiant){
        try{
            return ResponseEntity.ok(etudiantService.mettreAJour(id, etudiant));
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id){
        try{
            etudiantService.supprimer(id);
            return ResponseEntity.noContent().build();
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Etudiant> findByNomContainingIgnoreCas(@RequestParam String nom){
        return etudiantService.findByNomContainingIgnoreCase(nom);
    }

}