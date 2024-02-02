package fr.cda.cinemacda.seance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface SeanceReposity extends JpaRepository<Seance, Integer> {

    List<Seance> findByDate(LocalDate date);

}

