package com.dav.mediation.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.dav.mediation.domain.FlowTransactionsView} entity.
 */
@ApiModel(description = "Class representing flow transactions")
public class FlowTransactionsViewDTO implements Serializable {
    
    private Long id;

    @NotNull
    private UUID flowId;

    @NotNull
    @Size(max = 50)
    private String flowName;

    @NotNull
    @Size(max = 50)
    private String flowType;

    @NotNull
    @Size(max = 50)
    private String senderIs;

    @NotNull
    @Size(max = 50)
    private String senderProtocol;

    @NotNull
    @Size(max = 50)
    private String sourceInstance;

    @NotNull
    @Size(max = 50)
    private String receiverIs;

    @NotNull
    @Size(max = 50)
    private String receiverProtocol;

    @NotNull
    @Size(max = 50)
    private String targetInstance;

    private String filePayload;

    @NotNull
    private Instant flowDateTime;

    @NotNull
    @Size(max = 50)
    private String status;

    
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

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getSenderIs() {
        return senderIs;
    }

    public void setSenderIs(String senderIs) {
        this.senderIs = senderIs;
    }

    public String getSenderProtocol() {
        return senderProtocol;
    }

    public void setSenderProtocol(String senderProtocol) {
        this.senderProtocol = senderProtocol;
    }

    public String getSourceInstance() {
        return sourceInstance;
    }

    public void setSourceInstance(String sourceInstance) {
        this.sourceInstance = sourceInstance;
    }

    public String getReceiverIs() {
        return receiverIs;
    }

    public void setReceiverIs(String receiverIs) {
        this.receiverIs = receiverIs;
    }

    public String getReceiverProtocol() {
        return receiverProtocol;
    }

    public void setReceiverProtocol(String receiverProtocol) {
        this.receiverProtocol = receiverProtocol;
    }

    public String getTargetInstance() {
        return targetInstance;
    }

    public void setTargetInstance(String targetInstance) {
        this.targetInstance = targetInstance;
    }

    public String getFilePayload() {
        return filePayload;
    }

    public void setFilePayload(String filePayload) {
        this.filePayload = filePayload;
    }

    public Instant getFlowDateTime() {
        return flowDateTime;
    }

    public void setFlowDateTime(Instant flowDateTime) {
        this.flowDateTime = flowDateTime;
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
        if (!(o instanceof FlowTransactionsViewDTO)) {
            return false;
        }

        return id != null && id.equals(((FlowTransactionsViewDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowTransactionsViewDTO{" +
            "id=" + getId() +
            ", flowId='" + getFlowId() + "'" +
            ", flowName='" + getFlowName() + "'" +
            ", flowType='" + getFlowType() + "'" +
            ", senderIs='" + getSenderIs() + "'" +
            ", senderProtocol='" + getSenderProtocol() + "'" +
            ", sourceInstance='" + getSourceInstance() + "'" +
            ", receiverIs='" + getReceiverIs() + "'" +
            ", receiverProtocol='" + getReceiverProtocol() + "'" +
            ", targetInstance='" + getTargetInstance() + "'" +
            ", filePayload='" + getFilePayload() + "'" +
            ", flowDateTime='" + getFlowDateTime() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
