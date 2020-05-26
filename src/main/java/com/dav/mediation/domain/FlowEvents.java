package com.dav.mediation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * Class representing FlowEvents
 */
@Entity
@Table(name = "flow_events")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FlowEvents implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 50)
    @Column(name = "remarks", length = 50)
    private String remarks;

    @NotNull
    @Size(max = 50)
    @Column(name = "error_code", length = 50, nullable = false)
    private String errorCode;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "flowEvents", allowSetters = true)
    private FlowStatus flowTransactions;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "flowEvents", allowSetters = true)
    private EventMaster event;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "flowEvents", allowSetters = true)
    private EventStatusMaster status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public FlowEvents remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public FlowEvents errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public FlowStatus getFlowTransactions() {
        return flowTransactions;
    }

    public FlowEvents flowTransactions(FlowStatus flowStatus) {
        this.flowTransactions = flowStatus;
        return this;
    }

    public void setFlowTransactions(FlowStatus flowStatus) {
        this.flowTransactions = flowStatus;
    }

    public EventMaster getEvent() {
        return event;
    }

    public FlowEvents event(EventMaster eventMaster) {
        this.event = eventMaster;
        return this;
    }

    public void setEvent(EventMaster eventMaster) {
        this.event = eventMaster;
    }

    public EventStatusMaster getStatus() {
        return status;
    }

    public FlowEvents status(EventStatusMaster eventStatusMaster) {
        this.status = eventStatusMaster;
        return this;
    }

    public void setStatus(EventStatusMaster eventStatusMaster) {
        this.status = eventStatusMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlowEvents)) {
            return false;
        }
        return id != null && id.equals(((FlowEvents) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowEvents{" +
            "id=" + getId() +
            ", remarks='" + getRemarks() + "'" +
            ", errorCode='" + getErrorCode() + "'" +
            "}";
    }
}
