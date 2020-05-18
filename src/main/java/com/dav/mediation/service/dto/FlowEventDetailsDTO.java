package com.dav.mediation.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.dav.mediation.domain.FlowEventDetails} entity.
 */
@ApiModel(description = "Class representing flowEventDetails")
public class FlowEventDetailsDTO implements Serializable {
    
    private Long id;

    @NotNull
    private UUID eventId;

    @NotNull
    @Size(max = 100)
    private String eventName;

    @NotNull
    private Instant transactionDate;

    @NotNull
    @Size(max = 100)
    private String parameters;

    @NotNull
    @Size(max = 100)
    private String status;

    @NotNull
    @Size(max = 100)
    private String errorCode;

    @NotNull
    @Size(max = 250)
    private String errorInfo;

    @NotNull
    private String errorCodeRetriable;


    private Long flowIdId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorCodeRetriable() {
        return errorCodeRetriable;
    }

    public void setErrorCodeRetriable(String errorCodeRetriable) {
        this.errorCodeRetriable = errorCodeRetriable;
    }

    public Long getFlowIdId() {
        return flowIdId;
    }

    public void setFlowIdId(Long flowDetailsId) {
        this.flowIdId = flowDetailsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlowEventDetailsDTO)) {
            return false;
        }

        return id != null && id.equals(((FlowEventDetailsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowEventDetailsDTO{" +
            "id=" + getId() +
            ", eventId='" + getEventId() + "'" +
            ", eventName='" + getEventName() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", parameters='" + getParameters() + "'" +
            ", status='" + getStatus() + "'" +
            ", errorCode='" + getErrorCode() + "'" +
            ", errorInfo='" + getErrorInfo() + "'" +
            ", errorCodeRetriable='" + getErrorCodeRetriable() + "'" +
            ", flowIdId=" + getFlowIdId() +
            "}";
    }
}
