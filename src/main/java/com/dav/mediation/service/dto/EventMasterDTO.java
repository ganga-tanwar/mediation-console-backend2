package com.dav.mediation.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.dav.mediation.domain.EventMaster} entity.
 */
@ApiModel(description = "Class representing EventMaster")
public class EventMasterDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer eventId;

    @NotNull
    @Size(max = 50)
    private String eventName;

    @Size(max = 100)
    private String description;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventMasterDTO)) {
            return false;
        }

        return id != null && id.equals(((EventMasterDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventMasterDTO{" +
            "id=" + getId() +
            ", eventId=" + getEventId() +
            ", eventName='" + getEventName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
