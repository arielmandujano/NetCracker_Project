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
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Car implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "car_id")
    private Integer carId;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "model_id",
            nullable = false
    )
    private Model modelId;

    @Column(name = "car_registration")
    private String carRegistration;

    @Column(name = "price_per_day")
    private BigDecimal pricePerDay;

    @JsonIgnore
    @OneToMany(
            mappedBy = "carId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Reservation> reservations = new LinkedList<>();

}
