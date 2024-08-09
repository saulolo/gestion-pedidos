package com.gestion_pedidos.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @NotBlank(message = "El username es obligatorio")
    @Size(max = 50, message = "El username no puede tener más de 50 caracteres")
    @Column(name = "username")
    String username;

    @NotBlank(message = "El password es obligatorio")
    @Size(max = 200, message = "El password no puede tener más de 50 caracteres")
    @Column(name = "password")
    String password;

    @Column(name = "enabled")
    boolean enabled;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    List<Rol> roles;
}
