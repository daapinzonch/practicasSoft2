package com.redcampo.app.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Farmer")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true, ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Farmer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NonNull
    private Long farmerId;

    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JsonBackReference(value="usr-farmer")
    private User user;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*@OneToMany(mappedBy = "campesino",fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JsonManagedReference(value="cos-camp")
    private List<Cosecha> cosechas = new ArrayList<>();

    @OneToMany(mappedBy = "campesino",fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JsonManagedReference(value="ven-camp")
    private List<Venta> ventas = new ArrayList<>();*/
}
