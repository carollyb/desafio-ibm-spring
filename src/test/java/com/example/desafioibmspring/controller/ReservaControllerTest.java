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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    @BeforeEach
    public void setup() {
        ReservaDTO reservaDTO = new ReservaDTO(1, "nome", new Date("12/12/2023"), new Date("22/12/2023"), 2, Reserva.Status.CONFIRMADA);
    }

    @Test
    public void testInsertReserva() throws Exception {
        Reserva reservaInserida = new Reserva(1, "nome", new Date("12/12/2023"), new Date("22/12/2023"), 2);
        when(reservaService.insert(any(Reserva.class))).thenReturn(reservaInserida);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"nomeHospede\":\"nome\",\"dataInicio\":\"12/12/2023\",\"dataFim\":\"22/12/2023\",\"quantidadePessoas\":2}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", "http://localhost/reservas/1"));
    }

    @Test
    public void testFindReservaById() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Integer reservaId = 1;
        Reserva reservaEncontrada = new Reserva(reservaId, "nome", sdf.parse("01/12/2023"), sdf.parse("10/12/2023"), 2, Reserva.Status.CONFIRMADA);

        when(reservaService.findById(reservaId)).thenReturn(reservaEncontrada);

        mockMvc.perform(MockMvcRequestBuilders.get("/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomeHospede").value("nome"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataInicio").value("01/12/2023"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataFim").value("10/12/2023"))
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
        mockMvc.perform(MockMvcRequestBuilders.delete("/reservas/1"))
                .andExpect(status().isNoContent());
    }
}
