package com.example.demo.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import javax.persistence.SequenceGenerator;
import java.util.Collection;
import java.util.Collections;

@Getter
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {
    //guardamos
    @Id
    @SequenceGenerator(name="user_sequence",sequenceName ="user_sequence", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String nombre;
    private String username;
    private String email;
    private String password;

    //agregamos el otro user
    //mapeamos el enum y le avisamos a hibernate
    @Enumerated(EnumType.STRING)
    private AppUsuarioRoles appUsuarioRoles;

    public AppUser(String nombre, String username, String email, String password, AppUsuarioRoles appUsuarioRoles) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appUsuarioRoles = appUsuarioRoles;
    }

    //en app user le agregamos un metodo



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAppUsuarioRoles(AppUsuarioRoles appUsuarioRoles) {
        this.appUsuarioRoles = appUsuarioRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       //clase que nos ayuda manejar a roles
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUsuarioRoles.name());


        return Collections.singletonList(grantedAuthority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
