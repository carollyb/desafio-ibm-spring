package com.example.desafioibmspring.service;

import com.example.desafioibmspring.domain.Reserva;
import com.example.desafioibmspring.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findAll() {
        List<Reserva> list = reservaRepository.findAll();
        return list;
    }

    public Reserva insert(Reserva obj) {
        obj.setId(null);
        obj.setStatus(Reserva.Status.CONFIRMADA);
        obj = reservaRepository.save(obj);
        return obj;
    }
}
