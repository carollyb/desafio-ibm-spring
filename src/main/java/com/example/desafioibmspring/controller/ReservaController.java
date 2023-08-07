package com.example.desafioibmspring.controller;

import com.example.desafioibmspring.domain.Reserva;
import com.example.desafioibmspring.dto.ReservaDTO;
import com.example.desafioibmspring.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> findAll() {
        List<Reserva> list = reservaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Integer id) {
        Reserva reserva = reservaService.findById(id);
        return ResponseEntity.ok().body(reserva);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Reserva reserva) {
        Reserva obj = reservaService.insert(reserva);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ReservaDTO dto, @PathVariable Integer id) {
        Reserva obj = reservaService.fromDTO(dto);
        obj.setId(id);
        reservaService.update(obj);
        return ResponseEntity.noContent().build();
    }

}
