package com.redcampo.app.Entity.Support;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class temporalOrder{
    Long quantity;
    Long storeKey;
    Long productKey;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(Long storeKey) {
        this.storeKey = storeKey;
    }

    public Long getProductKey() {
        return productKey;
    }

    public void setProductKey(Long productKey) {
        this.productKey = productKey;
    }
}
