package com.example.desafioibmspring.utils;

import com.example.desafioibmspring.domain.Reserva;
import com.example.desafioibmspring.service.exceptions.InvalidEnumValueException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.util.Optional;

public class EnumUtil {
    public static Optional<Reserva.Status> parseStatus(String statusName) throws InvalidEnumValueException {
        try {
            return Optional.of(Enum.valueOf(Reserva.Status.class, statusName));
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(e.getMessage());
        }
    }
}
