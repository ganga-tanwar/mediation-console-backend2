package com.dav.mediation.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.dav.mediation.domain.SenderSystemsView} entity.
 */
@ApiModel(description = "Class representing SenderSystemsView")
public class SenderSystemsViewDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 50)
    private String senderIs;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderIs() {
        return senderIs;
    }

    public void setSenderIs(String senderIs) {
        this.senderIs = senderIs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SenderSystemsViewDTO)) {
            return false;
        }

        return id != null && id.equals(((SenderSystemsViewDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SenderSystemsViewDTO{" +
            "id=" + getId() +
            ", senderIs='" + getSenderIs() + "'" +
            "}";
    }
}
