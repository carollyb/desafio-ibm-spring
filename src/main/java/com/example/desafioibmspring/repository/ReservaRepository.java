package com.example.desafioibmspring.repository;

import com.example.desafioibmspring.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
}
