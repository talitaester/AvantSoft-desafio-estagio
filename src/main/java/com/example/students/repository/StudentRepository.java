package com.example.students.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.students.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
}