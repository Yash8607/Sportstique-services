package com.hcltech.sportique.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Organization_Details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organization implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String organization_name;
    private String organization_location;
    @Email(message = "Email address is not valid !!")
    @Column
    private String organizationEmail;
    private String organization_link;


    @NotEmpty
    private String password;
    private String organization_description;
    @NotEmpty
    @Size(min=10, message="Please enter valid phone number")
    @Pattern(regexp = "^[0-9]{10}$")
    private String organization_phoneNumber;
    private String organization_adminName;
    @Column(unique = true)
    private String organization_uniqueId;

    @NotEmpty
    @Size(min=10, message="Please enter valid phone number")
    @Pattern(regexp = "^[0-9]{10}$")
    private String organization_adminNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return organizationEmail;
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
