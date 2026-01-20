package com.example.Projet_IRT31.controller;
import com.example.Projet_IRT31.entity.Employer;
import com.example.Projet_IRT31.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
@CrossOrigin("*") // Important pour autoriser le futur Frontend
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    // POST /api/employers -> Ajouter une entreprise
    // On ajoute un paramètre ?userId=1 dans l'URL
    @PostMapping
    public ResponseEntity<Employer> createEmployer(@RequestBody Employer employer,
                                                   @RequestParam(required = false) Integer userId) {

        // Si un userId est fourni, on essaie de lier l'utilisateur (optionnel pour l'instant)
        if (userId != null) {
            // Astuce rapide : on crée un objet User "vide" juste avec l'ID pour faire le lien
            // (Idéalement, il faudrait appeler un UserService pour vérifier s'il existe)
            com.example.Projet_IRT31.entity.User user = new com.example.Projet_IRT31.entity.User();
            user.setId(userId); // Assurez-vous que User a bien une méthode setId()
            employer.setUser(user);
        }

        Employer newEmployer = employerService.createEmployer(employer);
        return new ResponseEntity<>(newEmployer, HttpStatus.CREATED);
    }

    // GET /api/employers -> Voir toutes les entreprises
    @GetMapping
    public ResponseEntity<List<Employer>> getAllEmployers() {
        return ResponseEntity.ok(employerService.getAllEmployers());
    }

    // GET /api/employers/{id} -> Voir une seule entreprise
    @GetMapping("/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Integer id) {
        return ResponseEntity.ok(employerService.getEmployerById(id));
    }

    // DELETE /api/employers/{id} -> Supprimer une entreprise
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployer(@PathVariable Integer id) {
        employerService.deleteEmployer(id);
        return ResponseEntity.ok("Employeur supprimé avec succès.");
    }
}