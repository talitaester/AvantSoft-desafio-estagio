package com.example.students.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.students.dto.StudentDTO;
import com.example.students.entity.Student;
import com.example.students.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {
	
    @Autowired
    private StudentService studentService;
    
    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) {
        if (dto.id() != null) {
            return ResponseEntity.badRequest().build();
        }

        Student student = studentService.create(dto);
        StudentDTO responseDTO = convertToDTO(student);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    private StudentDTO convertToDTO(Student student) {
        String missingLetter = studentService.findUniqueLetter(student);
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getGrade(),
                missingLetter
        );
    }
    
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> students = studentService.get()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable UUID id) {
    	Student student = studentService.getById(id);
    	StudentDTO responseDTO = convertToDTO(student);
        return ResponseEntity.ok(responseDTO);
    }


}


