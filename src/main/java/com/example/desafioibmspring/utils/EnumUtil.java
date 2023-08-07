package com.example.desafioibmspring.utils;

import com.example.desafioibmspring.domain.Reserva;
import com.example.desafioibmspring.service.exceptions.InvalidEnumValueException;

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
