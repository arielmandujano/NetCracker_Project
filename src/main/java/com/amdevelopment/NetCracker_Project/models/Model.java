package com.amdevelopment.NetCracker_Project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "models")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "model_year")
    private Integer modelYear;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "car_type")
    private String carType;

    @Lob
    @Column(name = "picture")
    private String picture;

    @JsonIgnore
    @OneToMany(
            mappedBy = "modelId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Car> cars = new LinkedList<>();

}
