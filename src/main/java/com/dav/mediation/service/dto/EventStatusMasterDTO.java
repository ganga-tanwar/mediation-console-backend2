package com.dav.mediation.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.dav.mediation.domain.EventStatusMaster} entity.
 */
@ApiModel(description = "Class representing EventMaster")
public class EventStatusMasterDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer statusId;

    @NotNull
    @Size(max = 50)
    private String status;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventStatusMasterDTO)) {
            return false;
        }

        return id != null && id.equals(((EventStatusMasterDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventStatusMasterDTO{" +
            "id=" + getId() +
            ", statusId=" + getStatusId() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
