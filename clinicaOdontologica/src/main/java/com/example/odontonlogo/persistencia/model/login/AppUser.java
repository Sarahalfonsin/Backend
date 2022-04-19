package com.example.odontonlogo.persistencia.model.login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

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
    private     AppUserRole appUserRole;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUserRole.name());

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
