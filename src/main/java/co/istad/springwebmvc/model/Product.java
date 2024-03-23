package co.istad.springwebmvc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)

    private String uuid;
    @Column(nullable = false)

    private String name;
    @Column(nullable = false)

    private Double price;
    @Column(nullable = false)

    private Integer qty;
    private LocalDateTime importedDate;
    private Boolean status;

}
