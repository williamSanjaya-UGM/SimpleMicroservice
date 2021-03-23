package com.example.rediscacheservice.entity.ProductDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse implements Serializable {

    @Id
    private long id;

    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String city;

    @NotEmpty
    @NotNull
    private String address;

    @NotNull
    private int zipCode;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date created_at;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date updated_at;
}
