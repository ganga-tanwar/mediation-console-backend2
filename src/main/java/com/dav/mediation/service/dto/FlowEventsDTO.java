package com.dav.mediation.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.dav.mediation.domain.FlowEvents} entity.
 */
@ApiModel(description = "Class representing FlowEvents")
public class FlowEventsDTO implements Serializable {
    
    private Long id;

    @Size(max = 50)
    private String remarks;

    @NotNull
    @Size(max = 50)
    private String errorCode;


    private Long flowTransactionsId;

    private Long eventId;

    private Long statusId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Long getFlowTransactionsId() {
        return flowTransactionsId;
    }

    public void setFlowTransactionsId(Long flowStatusId) {
        this.flowTransactionsId = flowStatusId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventMasterId) {
        this.eventId = eventMasterId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long eventStatusMasterId) {
        this.statusId = eventStatusMasterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlowEventsDTO)) {
            return false;
        }

        return id != null && id.equals(((FlowEventsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowEventsDTO{" +
            "id=" + getId() +
            ", remarks='" + getRemarks() + "'" +
            ", errorCode='" + getErrorCode() + "'" +
            ", flowTransactionsId=" + getFlowTransactionsId() +
            ", eventId=" + getEventId() +
            ", statusId=" + getStatusId() +
            "}";
    }
}
