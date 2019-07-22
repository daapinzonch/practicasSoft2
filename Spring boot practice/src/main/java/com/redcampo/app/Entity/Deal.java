package com.redcampo.app.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Deal")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
@NoArgsConstructor
@AllArgsConstructor
public class Deal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long dealId;

    private Long dealQuantity;

    private Long dealPrice;

    private boolean dealStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonBackReference(value="prod-deal")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JsonBackReference(value="store-deal")
    private Product product;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public Long getDealQuantity() {
        return dealQuantity;
    }

    public void setDealQuantity(Long dealQuantity) {
        this.dealQuantity = dealQuantity;
    }

    public Long getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(Long dealPrice) {
        this.dealPrice = dealPrice;
    }

    public boolean isStatus() {
        return dealStatus;
    }

    public void setStatus(boolean status) {
        this.dealStatus = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}

