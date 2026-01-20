package com.example.Projet_IRT31.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "skills")
@Data @NoArgsConstructor
public class Skill {
    @Id
    private Integer id;
    private String name;
}