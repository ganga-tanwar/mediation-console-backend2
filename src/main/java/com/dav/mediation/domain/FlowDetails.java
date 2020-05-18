package com.dav.mediation.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * Class representing flows
 */
@Entity
@Table(name = "flow_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FlowDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "flow_id", nullable = false)
    private UUID flowId;

    @NotNull
    @Size(max = 100)
    @Column(name = "flow_name", length = 100, nullable = false)
    private String flowName;

    @NotNull
    @Size(max = 100)
    @Column(name = "mediation_system", length = 100, nullable = false)
    private String mediationSystem;

    @NotNull
    @Size(max = 100)
    @Column(name = "source", length = 100, nullable = false)
    private String source;

    @NotNull
    @Size(max = 100)
    @Column(name = "destination", length = 100, nullable = false)
    private String destination;

    @Column(name = "file_name")
    private String fileName;

    @NotNull
    @Column(name = "transaction_date", nullable = false)
    private Instant transactionDate;

    @NotNull
    @Column(name = "transaction_id", nullable = false)
    private UUID transactionId;

    @Column(name = "format")
    private String format;

    @NotNull
    @Size(max = 100)
    @Column(name = "source_endpoint_interface", length = 100, nullable = false)
    private String sourceEndpointInterface;

    @NotNull
    @Size(max = 100)
    @Column(name = "destination_endpoint_interface", length = 100, nullable = false)
    private String destinationEndpointInterface;

    @NotNull
    @Size(max = 100)
    @Column(name = "acknowledgment_expected", length = 100, nullable = false)
    private String acknowledgmentExpected;

    @Size(max = 100)
    @Column(name = "acknowledgment_received", length = 100)
    private String acknowledgmentReceived;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getFlowId() {
        return flowId;
    }

    public FlowDetails flowId(UUID flowId) {
        this.flowId = flowId;
        return this;
    }

    public void setFlowId(UUID flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public FlowDetails flowName(String flowName) {
        this.flowName = flowName;
        return this;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getMediationSystem() {
        return mediationSystem;
    }

    public FlowDetails mediationSystem(String mediationSystem) {
        this.mediationSystem = mediationSystem;
        return this;
    }

    public void setMediationSystem(String mediationSystem) {
        this.mediationSystem = mediationSystem;
    }

    public String getSource() {
        return source;
    }

    public FlowDetails source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public FlowDetails destination(String destination) {
        this.destination = destination;
        return this;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFileName() {
        return fileName;
    }

    public FlowDetails fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public FlowDetails transactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public FlowDetails transactionId(UUID transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public String getFormat() {
        return format;
    }

    public FlowDetails format(String format) {
        this.format = format;
        return this;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSourceEndpointInterface() {
        return sourceEndpointInterface;
    }

    public FlowDetails sourceEndpointInterface(String sourceEndpointInterface) {
        this.sourceEndpointInterface = sourceEndpointInterface;
        return this;
    }

    public void setSourceEndpointInterface(String sourceEndpointInterface) {
        this.sourceEndpointInterface = sourceEndpointInterface;
    }

    public String getDestinationEndpointInterface() {
        return destinationEndpointInterface;
    }

    public FlowDetails destinationEndpointInterface(String destinationEndpointInterface) {
        this.destinationEndpointInterface = destinationEndpointInterface;
        return this;
    }

    public void setDestinationEndpointInterface(String destinationEndpointInterface) {
        this.destinationEndpointInterface = destinationEndpointInterface;
    }

    public String getAcknowledgmentExpected() {
        return acknowledgmentExpected;
    }

    public FlowDetails acknowledgmentExpected(String acknowledgmentExpected) {
        this.acknowledgmentExpected = acknowledgmentExpected;
        return this;
    }

    public void setAcknowledgmentExpected(String acknowledgmentExpected) {
        this.acknowledgmentExpected = acknowledgmentExpected;
    }

    public String getAcknowledgmentReceived() {
        return acknowledgmentReceived;
    }

    public FlowDetails acknowledgmentReceived(String acknowledgmentReceived) {
        this.acknowledgmentReceived = acknowledgmentReceived;
        return this;
    }

    public void setAcknowledgmentReceived(String acknowledgmentReceived) {
        this.acknowledgmentReceived = acknowledgmentReceived;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlowDetails)) {
            return false;
        }
        return id != null && id.equals(((FlowDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowDetails{" +
            "id=" + getId() +
            ", flowId='" + getFlowId() + "'" +
            ", flowName='" + getFlowName() + "'" +
            ", mediationSystem='" + getMediationSystem() + "'" +
            ", source='" + getSource() + "'" +
            ", destination='" + getDestination() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", transactionId='" + getTransactionId() + "'" +
            ", format='" + getFormat() + "'" +
            ", sourceEndpointInterface='" + getSourceEndpointInterface() + "'" +
            ", destinationEndpointInterface='" + getDestinationEndpointInterface() + "'" +
            ", acknowledgmentExpected='" + getAcknowledgmentExpected() + "'" +
            ", acknowledgmentReceived='" + getAcknowledgmentReceived() + "'" +
            "}";
    }
}
