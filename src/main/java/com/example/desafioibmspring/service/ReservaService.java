package com.example.desafioibmspring.service;

import com.example.desafioibmspring.domain.Reserva;
import com.example.desafioibmspring.dto.ReservaDTO;
import com.example.desafioibmspring.repository.ReservaRepository;
import com.example.desafioibmspring.service.exceptions.ObjectNotFoundException;
import com.example.desafioibmspring.utils.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findAll() {
        List<Reserva> list = reservaRepository.findAll();
        return list;
    }

    public Reserva findById(Integer id) {
        Optional<Reserva> obj = reservaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " " + Reserva.class.getName()));
    }

    public Reserva insert(Reserva obj) {
        obj.setId(null);
        obj.setStatus(Reserva.Status.CONFIRMADA);
        obj = reservaRepository.save(obj);
        return obj;
    }

    public Reserva update(Reserva obj) {
        Reserva att = findById(obj.getId());
        updateData(att, obj);
        return reservaRepository.save(att);
    }

    private void updateData(Reserva novo, Reserva antigo) {
        novo.setNomeHospede(antigo.getNomeHospede());
        novo.setDataInicio(antigo.getDataInicio());
        novo.setDataFim(antigo.getDataFim());
        novo.setQuantidadePessoas(antigo.getQuantidadePessoas());
        novo.setStatus(antigo.getStatus());
    }

    public Reserva fromDTO(ReservaDTO obj) {
        EnumUtil.parseStatus(obj.getStatus().name());

        return new Reserva((obj.getId()), obj.getNomeHospede(), obj.getDataInicio(), obj.getDataFim(), obj.getQuantidadePessoas(), obj.getStatus());
    }

    public void delete(Integer id) {
        reservaRepository.deleteById(id);
    }

}
