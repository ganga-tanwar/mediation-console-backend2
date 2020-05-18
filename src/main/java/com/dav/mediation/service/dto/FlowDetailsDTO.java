package com.dav.mediation.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.dav.mediation.domain.FlowDetails} entity.
 */
@ApiModel(description = "Class representing flows")
public class FlowDetailsDTO implements Serializable {
    
    private Long id;

    @NotNull
    private UUID flowId;

    @NotNull
    @Size(max = 100)
    private String flowName;

    @NotNull
    @Size(max = 100)
    private String mediationSystem;

    @NotNull
    @Size(max = 100)
    private String source;

    @NotNull
    @Size(max = 100)
    private String destination;

    private String fileName;

    @NotNull
    private Instant transactionDate;

    @NotNull
    private UUID transactionId;

    private String format;

    @NotNull
    @Size(max = 100)
    private String sourceEndpointInterface;

    @NotNull
    @Size(max = 100)
    private String destinationEndpointInterface;

    @NotNull
    @Size(max = 100)
    private String acknowledgmentExpected;

    @Size(max = 100)
    private String acknowledgmentReceived;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getFlowId() {
        return flowId;
    }

    public void setFlowId(UUID flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getMediationSystem() {
        return mediationSystem;
    }

    public void setMediationSystem(String mediationSystem) {
        this.mediationSystem = mediationSystem;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSourceEndpointInterface() {
        return sourceEndpointInterface;
    }

    public void setSourceEndpointInterface(String sourceEndpointInterface) {
        this.sourceEndpointInterface = sourceEndpointInterface;
    }

    public String getDestinationEndpointInterface() {
        return destinationEndpointInterface;
    }

    public void setDestinationEndpointInterface(String destinationEndpointInterface) {
        this.destinationEndpointInterface = destinationEndpointInterface;
    }

    public String getAcknowledgmentExpected() {
        return acknowledgmentExpected;
    }

    public void setAcknowledgmentExpected(String acknowledgmentExpected) {
        this.acknowledgmentExpected = acknowledgmentExpected;
    }

    public String getAcknowledgmentReceived() {
        return acknowledgmentReceived;
    }

    public void setAcknowledgmentReceived(String acknowledgmentReceived) {
        this.acknowledgmentReceived = acknowledgmentReceived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlowDetailsDTO)) {
            return false;
        }

        return id != null && id.equals(((FlowDetailsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowDetailsDTO{" +
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
