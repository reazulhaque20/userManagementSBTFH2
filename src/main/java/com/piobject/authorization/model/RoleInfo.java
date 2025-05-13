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
@Table(name = "ROLE_DETAILS")
public class RoleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name", nullable = false)
    @NotNull(message = "Role name should not be empty")
    private String roleName;

    @Column(name = "role_description", nullable = false)
    @NotNull(message = "role description should not be null")
    private String roleDescription;

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

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    private Set<UserInfo> users;
}
