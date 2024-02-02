package fr.cda.cinemacda.seance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeanceReposity extends JpaRepository<Seance, Integer> {


}
