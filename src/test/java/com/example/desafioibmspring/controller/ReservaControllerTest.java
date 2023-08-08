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

import java.util.Date;

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
}
