package com.dav.mediation.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * Class representing flow transactions
 */
@Entity
@Table(name = "flow_transactions_view")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FlowTransactionsView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "flow_id", nullable = false)
    private UUID flowId;

    @NotNull
    @Size(max = 50)
    @Column(name = "flow_name", length = 50, nullable = false)
    private String flowName;

    @NotNull
    @Size(max = 50)
    @Column(name = "flow_type", length = 50, nullable = false)
    private String flowType;

    @NotNull
    @Size(max = 50)
    @Column(name = "sender_is", length = 50, nullable = false)
    private String senderIs;

    @NotNull
    @Size(max = 50)
    @Column(name = "sender_protocol", length = 50, nullable = false)
    private String senderProtocol;

    @NotNull
    @Size(max = 50)
    @Column(name = "source_instance", length = 50, nullable = false)
    private String sourceInstance;

    @NotNull
    @Size(max = 50)
    @Column(name = "receiver_is", length = 50, nullable = false)
    private String receiverIs;

    @NotNull
    @Size(max = 50)
    @Column(name = "receiver_protocol", length = 50, nullable = false)
    private String receiverProtocol;

    @NotNull
    @Size(max = 50)
    @Column(name = "target_instance", length = 50, nullable = false)
    private String targetInstance;

    @Column(name = "file_payload")
    private String filePayload;

    @NotNull
    @Column(name = "flow_date_time", nullable = false)
    private Instant flowDateTime;

    @NotNull
    @Size(max = 50)
    @Column(name = "status", length = 50, nullable = false)
    private String status;

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

    public FlowTransactionsView flowId(UUID flowId) {
        this.flowId = flowId;
        return this;
    }

    public void setFlowId(UUID flowId) {
        this.flowId = flowId;
    }

    public String getFlowName() {
        return flowName;
    }

    public FlowTransactionsView flowName(String flowName) {
        this.flowName = flowName;
        return this;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowType() {
        return flowType;
    }

    public FlowTransactionsView flowType(String flowType) {
        this.flowType = flowType;
        return this;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getSenderIs() {
        return senderIs;
    }

    public FlowTransactionsView senderIs(String senderIs) {
        this.senderIs = senderIs;
        return this;
    }

    public void setSenderIs(String senderIs) {
        this.senderIs = senderIs;
    }

    public String getSenderProtocol() {
        return senderProtocol;
    }

    public FlowTransactionsView senderProtocol(String senderProtocol) {
        this.senderProtocol = senderProtocol;
        return this;
    }

    public void setSenderProtocol(String senderProtocol) {
        this.senderProtocol = senderProtocol;
    }

    public String getSourceInstance() {
        return sourceInstance;
    }

    public FlowTransactionsView sourceInstance(String sourceInstance) {
        this.sourceInstance = sourceInstance;
        return this;
    }

    public void setSourceInstance(String sourceInstance) {
        this.sourceInstance = sourceInstance;
    }

    public String getReceiverIs() {
        return receiverIs;
    }

    public FlowTransactionsView receiverIs(String receiverIs) {
        this.receiverIs = receiverIs;
        return this;
    }

    public void setReceiverIs(String receiverIs) {
        this.receiverIs = receiverIs;
    }

    public String getReceiverProtocol() {
        return receiverProtocol;
    }

    public FlowTransactionsView receiverProtocol(String receiverProtocol) {
        this.receiverProtocol = receiverProtocol;
        return this;
    }

    public void setReceiverProtocol(String receiverProtocol) {
        this.receiverProtocol = receiverProtocol;
    }

    public String getTargetInstance() {
        return targetInstance;
    }

    public FlowTransactionsView targetInstance(String targetInstance) {
        this.targetInstance = targetInstance;
        return this;
    }

    public void setTargetInstance(String targetInstance) {
        this.targetInstance = targetInstance;
    }

    public String getFilePayload() {
        return filePayload;
    }

    public FlowTransactionsView filePayload(String filePayload) {
        this.filePayload = filePayload;
        return this;
    }

    public void setFilePayload(String filePayload) {
        this.filePayload = filePayload;
    }

    public Instant getFlowDateTime() {
        return flowDateTime;
    }

    public FlowTransactionsView flowDateTime(Instant flowDateTime) {
        this.flowDateTime = flowDateTime;
        return this;
    }

    public void setFlowDateTime(Instant flowDateTime) {
        this.flowDateTime = flowDateTime;
    }

    public String getStatus() {
        return status;
    }

    public FlowTransactionsView status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlowTransactionsView)) {
            return false;
        }
        return id != null && id.equals(((FlowTransactionsView) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowTransactionsView{" +
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
