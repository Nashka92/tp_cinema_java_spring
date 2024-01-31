package fr.cda.cinemacda.realisateur;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RealisateurRepository extends JpaRepository<Realisateur, Integer> {
    Optional<Realisateur> findByNom(String nom);
}
