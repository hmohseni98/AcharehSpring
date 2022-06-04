package com.spring.achareh.model;

import com.spring.achareh.model.base.BaseEntity;
import com.spring.achareh.model.enumration.Role;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
@Entity
public abstract class User extends BaseEntity<Integer> implements UserDetails {
    @Column(name = "first_name", columnDefinition = "varchar(30)")
    private String firstName;
    @Column(name = "last_name", columnDefinition = "varchar(50)")
    private String lastName;
    @Column(name = "email", unique = true, columnDefinition = "varchar(50)")
    private String email;
    private String password;
    @CreationTimestamp
    @Column(name = "register_date_time")
    private LocalDateTime registerDataTime;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean expired;
    private Boolean locked;
    private Boolean credentialsExpired;
    private Boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
