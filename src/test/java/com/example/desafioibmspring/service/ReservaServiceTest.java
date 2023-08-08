package com.example.desafioibmspring.service;

import com.example.desafioibmspring.domain.Reserva;
import com.example.desafioibmspring.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    public void testInsertReserva() {
        Reserva reserva = new Reserva(1, "nome teste", new Date("10/08/2023"), new Date("15/08/2023"), 4);
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva reservaInserida = reservaService.insert(reserva);

        assertNotNull(reservaInserida);
        assertEquals(reserva.getNomeHospede(), reservaInserida.getNomeHospede());
        assertEquals(reserva.getDataInicio(), reservaInserida.getDataInicio());
        assertEquals(reserva.getDataFim(), reservaInserida.getDataFim());
        assertEquals(reserva.getQuantidadePessoas(), reservaInserida.getQuantidadePessoas());
        assertEquals(reserva.getStatus(), reservaInserida.getStatus());

        verify(reservaRepository, times(1)).save(any(Reserva.class));

    }
    
}
