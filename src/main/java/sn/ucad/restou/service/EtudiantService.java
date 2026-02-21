package sn.ucad.restou.service;

import org.springframework.stereotype.Service;
import sn.ucad.restou.entity.Etudiant ;
import sn.ucad.restou.repository.EtudiantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository){
        this.etudiantRepository = etudiantRepository;
    }

    public Etudiant creer(Etudiant etudiant){
        return etudiantRepository.save(etudiant);
    }

    public Iterable < Etudiant > recupererTous() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> recupererParId( Long id ) {
        return etudiantRepository.findById(id);
    }

    public Etudiant mettreAJour(Long id, Etudiant etudiantDetails) {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(() -> new RuntimeException("Etudiant non trouve avec l'id :" +id));
        etudiant.setNom(etudiantDetails.getNom());
        etudiant.setPrenom(etudiantDetails.getPrenom());
        etudiant.setEmail(etudiantDetails.getEmail());
        etudiant.setNumeroCarte(etudiantDetails.getNumeroCarte());
        return etudiantRepository.save(etudiant);
    }

    public void supprimer(Long id){
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow(() -> new RuntimeException("Etudiant non trouve avec l'id :"+id));
        etudiantRepository.delete(etudiant);
    }

    public List<Etudiant> findByNomContainingIgnoreCase(String nom) {
        return etudiantRepository.findByNomContainingIgnoreCase(nom);
    }

}