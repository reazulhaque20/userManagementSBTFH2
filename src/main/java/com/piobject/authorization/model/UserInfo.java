package com.piobject.authorization.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USER_DETAILS")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", nullable = false)
    @NotNull(message = "User Name should not be empty")
    private String userName;

    @Column(name = "full_name", nullable = false)
    @NotNull(message = "Full name should not be empty")
    private String fullName;

    @Column(name = "password", nullable = false)
    @NotNull(message = "Password should not be empty")
    private String password;

    @Column(name = "email", nullable = false)
    @NotNull(message = "Email should not be empty")
    private String email;

    @Column(name = "is_active", nullable = false)
    @NotNull(message = "Active status should not be empty")
    private Boolean isActive;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role", // mapping table
            joinColumns = @JoinColumn(name = "user_id"), // owning side FK
            inverseJoinColumns = @JoinColumn(name = "role_id") // target FK
    )
    private Set<RoleInfo> roleInfo = new HashSet<>();
}
