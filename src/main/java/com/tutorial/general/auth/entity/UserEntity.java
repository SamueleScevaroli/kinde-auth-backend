package com.tutorial.general.auth.entity;

import com.tutorial.general.auth.model.User;
import com.tutorial.general.util.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "auth_user")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequenceGenerator")
    @SequenceGenerator(name = "userSequenceGenerator", sequenceName = "user_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "public_id")
    private UUID publicId;

    @Column(name = "last_seen")
    private Instant lastSeen;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")}
    )
    private Set<AuthorityEntity> authorities = new HashSet<>();

    public void updateFromUser(User user) {
        this.email = user.getEmail();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.imageUrl = user.getImageUrl();
        this.lastSeen = user.getLastSeen();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(publicId, that.publicId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(publicId);
    }
}