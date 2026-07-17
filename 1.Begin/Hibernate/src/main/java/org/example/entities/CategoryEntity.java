package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tblCategories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    public CategoryEntity() {
        this.dateCreated = LocalDateTime.now();
    }
    public CategoryEntity(String name) {
//        super();
        this.dateCreated = LocalDateTime.now();
        this.name = name;
    }


}
