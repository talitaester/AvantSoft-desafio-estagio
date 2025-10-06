package com.example.students.service;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.students.dto.StudentDTO;
import com.example.students.entity.Student;
import com.example.students.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student create(StudentDTO dto){
        return studentRepository.save(moveToEntity(dto));
    }

    private Student moveToEntity(StudentDTO dto) {
        return new Student(dto.name(), dto.grade());
    }

    public String del(UUID id){
         studentRepository.deleteById(id);
         return "\n" +
                 "Student deleted successfully";
    }

    public Student update(StudentDTO dto){
    	Student existingStudent = getById(dto.id());
        
    	existingStudent.setName(dto.name());
    	existingStudent.setGrade(dto.grade());
   
        
        return studentRepository.save(existingStudent);
    }

    public List<Student> get(){
        return studentRepository.findAll();
    }

    public Student getById(UUID id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }



    public String findUniqueLetter(Student student) {
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return "_";
        }
        String lowerCaseName = student.getName().toLowerCase();
        int[] letterCount = new int[26]; 


        for (char c : lowerCaseName.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                letterCount[c - 'a']++;
            }
        }

        for (char c : lowerCaseName.toCharArray()) {
            if (c >= 'a' && c <= 'z' && letterCount[c - 'a'] == 1) {
                return String.valueOf(c);
            }
        }

        return "_"; 
    }

}
