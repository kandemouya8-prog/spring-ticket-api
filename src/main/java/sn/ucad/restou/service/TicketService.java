package sn.ucad.restou.service;

import org.springframework.stereotype.Service;
import sn.ucad.restou.entity.Ticket;
import sn.ucad.restou.repository.TicketRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    public Ticket creer(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Iterable<Ticket> recupererTous(){
        return ticketRepository.findAll();
    }

    public Optional<Ticket> recupererParId(Long id){
        return ticketRepository.findById(id);
    }

    public Iterable<Ticket> recupererParStatut(Boolean utilise){
        return ticketRepository.findByUtilise(utilise);
    }

    public Iterable<Ticket>  recupererParEtudiant(Long etudiantId){
        return ticketRepository.findByEtudiantId(etudiantId);
    }

    public Ticket valider(Long id){
        Ticket ticket = ticketRepository. findById(id).orElseThrow(() -> new RuntimeException("Ticket non trouve"));

        // verifier expiration
        if(ticket.getDateValidite().isBefore(LocalDate.now())){
            throw new RuntimeException("Ticket expiré - validation impossible");
        }

        if (ticket.getUtilise()) {
            throw new RuntimeException("Ce ticket a deja ete utilise !");
        }
        
        ticket.setUtilise(true);
        return ticketRepository.save(ticket);
    }

    public void supprimer(Long id){
        ticketRepository.deleteById(id);
    }
    
    public Map< String, Long> getStats(){
        /*
        long total = ticketRepository.count();
        long utilises = ticketRepository.countByUtiliseTrue();
        long disponibles = ticketRepository.countByUtiliseFalse();
        */
       long total = ticketRepository.count();
       long used = ticketRepository.countByUtiliseTrue();
       long available = total - used;

        Map<String, Long> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("used", used);
        stats.put("available", available);

        return stats;
    }
}