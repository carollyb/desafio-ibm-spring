package com.example.desafioibmspring.controller;

import com.example.desafioibmspring.domain.Reserva;
import com.example.desafioibmspring.dto.ReservaDTO;
import com.example.desafioibmspring.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    @BeforeEach
    public void setup() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ReservaDTO reservaDTO = new ReservaDTO(1, "nome", sdf.parse("2023-12-12"), sdf.parse("2023-12-22"), 2, Reserva.Status.CONFIRMADA);
    }

    @Test
    public void testInsertReserva() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Reserva reservaInserida = new Reserva(1, "nome", sdf.parse("2023-12-12"), sdf.parse("2023-12-22"), 2);
        when(reservaService.insert(any(Reserva.class))).thenReturn(reservaInserida);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"nomeHospede\":\"nome\",\"dataInicio\":\"2023-12-12\",\"dataFim\":\"2023-12-22\",\"quantidadePessoas\":2}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", "http://localhost/reservas/1"));
    }

    @Test
    public void testFindReservaById() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer reservaId = 1;
        Reserva reservaEncontrada = new Reserva(reservaId, "nome", sdf.parse("2023-12-01"), sdf.parse("2023-12-10"), 2, Reserva.Status.CONFIRMADA);

        when(reservaService.findById(reservaId)).thenReturn(reservaEncontrada);

        mockMvc.perform(MockMvcRequestBuilders.get("/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomeHospede").value("nome"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataInicio").value("2023-12-01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataFim").value("2023-12-10"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantidadePessoas").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("CONFIRMADA"));
    }

    @Test
    public void testFindAllReservas() throws Exception {
        Reserva reserva1 = new Reserva();
        Reserva reserva2 = new Reserva();
        List<Reserva> list = Arrays.asList(reserva1, reserva2);

        when(reservaService.findAll()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/reservas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteReserva() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/reservas/1/cancelar"))
                .andExpect(status().isOk());
    }
}
