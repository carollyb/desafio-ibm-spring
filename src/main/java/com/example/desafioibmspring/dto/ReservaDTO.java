package com.example.desafioibmspring.dto;

import com.example.desafioibmspring.domain.Reserva;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ReservaDTO implements Serializable {
    private Integer id;
    private String nomeHospede;
    private Date dataInicio;
    private Date dataFim;
    private Integer quantidadePessoas;
    private Reserva.Status status;
    public ReservaDTO(Reserva obj) {
        id = obj.getId();
        nomeHospede = obj.getNomeHospede();
        dataInicio = obj.getDataInicio();
        dataFim = obj.getDataFim();
        quantidadePessoas = obj.getQuantidadePessoas();
        status = obj.getStatus();
    }
}
