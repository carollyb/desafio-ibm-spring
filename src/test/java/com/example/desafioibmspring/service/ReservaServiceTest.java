package com.example.desafioibmspring.service;

import com.example.desafioibmspring.domain.Reserva;
import com.example.desafioibmspring.repository.ReservaRepository;
import com.example.desafioibmspring.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    public void testInsertReserva() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Reserva reserva = new Reserva(1, "nome teste", sdf.parse("2023-08-10"), sdf.parse("2023-08-15"), 4);
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

    @Test
    public void testFindAllReservas() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer reservaId = 1;
        Integer reservaId2 = 2;
        Reserva reserva1 = new Reserva(reservaId, "nome teste", sdf.parse("2023-08-10"), sdf.parse("2023-08-15"), 4);
        Reserva reserva2 = new Reserva(reservaId2, "nome teste 2", sdf.parse("2023-08-20"), sdf.parse("2023-08-25"), 2);

        when(reservaRepository.findAll()).thenReturn(Arrays.asList(reserva1, reserva2));

        List<Reserva> resultado = reservaService.findAll();
        assertEquals(resultado, Arrays.asList(reserva1, reserva2));

        verify(reservaRepository, times(1)).findAll();
    }

    @Test
    public void testFindReservaByExistentId() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer reservaId = 1;
        Reserva reserva = new Reserva(reservaId, "nome teste", sdf.parse("2023-08-10"), sdf.parse("2023-08-15"), 4);

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));
        Reserva reservaEncontrada = reservaService.findById(reservaId);

        assertNotNull(reservaEncontrada);
        assertEquals(reserva.getId(), reservaEncontrada.getId());
        assertEquals(reserva.getNomeHospede(), reservaEncontrada.getNomeHospede());
        assertEquals(reserva.getDataInicio(), reservaEncontrada.getDataInicio());
        assertEquals(reserva.getDataFim(), reservaEncontrada.getDataFim());
        assertEquals(reserva.getQuantidadePessoas(), reservaEncontrada.getQuantidadePessoas());
        assertEquals(reserva.getStatus(), reservaEncontrada.getStatus());

        verify(reservaRepository, times(1)).findById(reservaId);

    }

    @Test
    public void testFindReservaByNonExistentId() {
        Integer reservaId = 11;
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> reservaService.findById(reservaId));
        verify(reservaRepository, times(1)).findById(reservaId);
    }

    @Test
    public void testDeleteReserva() {
        Integer reservaId = 1;
        reservaService.delete(reservaId);
        verify(reservaRepository, times(1)).deleteById(reservaId);
    }

    @Test
    public void testUpdateReserva() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer reservaId = 1;
        Reserva reservaAtualizada = new Reserva(reservaId, "nome atualizado", sdf.parse("2023-08-10"), sdf.parse("2023-08-15"), 4);
        Reserva reservaExistente = new Reserva(reservaId, "nome teste", sdf.parse("2023-08-10"), sdf.parse("2023-08-15"), 4);

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reservaExistente));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaAtualizada);

        Reserva resultado = reservaService.update(reservaAtualizada);

        assertNotNull(resultado);
        assertEquals(reservaId, resultado.getId());
        assertEquals(reservaAtualizada.getNomeHospede(), resultado.getNomeHospede());
        assertEquals(reservaAtualizada.getDataInicio(), resultado.getDataInicio());
        assertEquals(reservaAtualizada.getDataFim(), resultado.getDataFim());
        assertEquals(reservaAtualizada.getQuantidadePessoas(), resultado.getQuantidadePessoas());
        assertEquals(reservaAtualizada.getStatus(), resultado.getStatus());

        verify(reservaRepository, times(1)).findById(reservaId);
        verify(reservaRepository, times(1)).save(any(Reserva.class));

    }

}
