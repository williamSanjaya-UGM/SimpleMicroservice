package com.example.customerservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid")
    @Column(name = "id")
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 2, message = "Nama harus lebih dari 2 karakter")
    private String username;

    @NotEmpty
    @NotNull
    @Email
    @Column(unique = true,nullable = false)
    private String email;

    @NotNull
    @NotEmpty
    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @NotNull
    @NotEmpty
    @Size(min = 6, message = "password harus lebih dari 6 karakter")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",nullable = false,updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date updated_at;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Address address;
}
