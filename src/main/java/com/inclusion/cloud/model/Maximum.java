package com.inclusion.cloud.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "maximums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Maximum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "x can not be null")
    private Long x;

    @NotNull(message = "y can not be null")
    private Long y;

    @NotNull(message = "n can not be null")
    private Long n;

    @NotNull(message = "result can not be null")
    private Long result;
}
