package com.dav.mediation.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.dav.mediation.domain.ReceiverSyetemsView} entity.
 */
@ApiModel(description = "Class representing ReceiverSyetemsView")
public class ReceiverSyetemsViewDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 50)
    private String receiverIs;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceiverIs() {
        return receiverIs;
    }

    public void setReceiverIs(String receiverIs) {
        this.receiverIs = receiverIs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReceiverSyetemsViewDTO)) {
            return false;
        }

        return id != null && id.equals(((ReceiverSyetemsViewDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReceiverSyetemsViewDTO{" +
            "id=" + getId() +
            ", receiverIs='" + getReceiverIs() + "'" +
            "}";
    }
}
