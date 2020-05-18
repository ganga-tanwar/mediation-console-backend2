package com.dav.mediation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * Class representing flowEventDetails
 */
@Entity
@Table(name = "flow_event_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FlowEventDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "event_id", nullable = false)
    private UUID eventId;

    @NotNull
    @Size(max = 100)
    @Column(name = "event_name", length = 100, nullable = false)
    private String eventName;

    @NotNull
    @Column(name = "transaction_date", nullable = false)
    private Instant transactionDate;

    @NotNull
    @Size(max = 100)
    @Column(name = "parameters", length = 100, nullable = false)
    private String parameters;

    @NotNull
    @Size(max = 100)
    @Column(name = "status", length = 100, nullable = false)
    private String status;

    @NotNull
    @Size(max = 100)
    @Column(name = "error_code", length = 100, nullable = false)
    private String errorCode;

    @NotNull
    @Size(max = 250)
    @Column(name = "error_info", length = 250, nullable = false)
    private String errorInfo;

    @NotNull
    @Column(name = "error_code_retriable", nullable = false)
    private String errorCodeRetriable;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "flowEventDetails", allowSetters = true)
    private FlowDetails flowId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getEventId() {
        return eventId;
    }

    public FlowEventDetails eventId(UUID eventId) {
        this.eventId = eventId;
        return this;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public FlowEventDetails eventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public FlowEventDetails transactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getParameters() {
        return parameters;
    }

    public FlowEventDetails parameters(String parameters) {
        this.parameters = parameters;
        return this;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getStatus() {
        return status;
    }

    public FlowEventDetails status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public FlowEventDetails errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public FlowEventDetails errorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
        return this;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getErrorCodeRetriable() {
        return errorCodeRetriable;
    }

    public FlowEventDetails errorCodeRetriable(String errorCodeRetriable) {
        this.errorCodeRetriable = errorCodeRetriable;
        return this;
    }

    public void setErrorCodeRetriable(String errorCodeRetriable) {
        this.errorCodeRetriable = errorCodeRetriable;
    }

    public FlowDetails getFlowId() {
        return flowId;
    }

    public FlowEventDetails flowId(FlowDetails flowDetails) {
        this.flowId = flowDetails;
        return this;
    }

    public void setFlowId(FlowDetails flowDetails) {
        this.flowId = flowDetails;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlowEventDetails)) {
            return false;
        }
        return id != null && id.equals(((FlowEventDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowEventDetails{" +
            "id=" + getId() +
            ", eventId='" + getEventId() + "'" +
            ", eventName='" + getEventName() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", parameters='" + getParameters() + "'" +
            ", status='" + getStatus() + "'" +
            ", errorCode='" + getErrorCode() + "'" +
            ", errorInfo='" + getErrorInfo() + "'" +
            ", errorCodeRetriable='" + getErrorCodeRetriable() + "'" +
            "}";
    }
}
