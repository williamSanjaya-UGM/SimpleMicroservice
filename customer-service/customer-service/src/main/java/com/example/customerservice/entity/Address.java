package com.example.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name="user_id")
    private String id;

    @NotEmpty
    @NotNull
    private String city;

    @NotEmpty
    @NotNull
    private String address;

    @NotNull
    private int zipCode;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false,updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",nullable = false)
    private Date updated_at;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
