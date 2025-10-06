package com.example.students.dto;
import java.util.UUID;

public record StudentDTO(UUID id, String name, Double grade, String missingLetter) {

}


