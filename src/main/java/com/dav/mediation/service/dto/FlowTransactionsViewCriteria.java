package com.dav.mediation.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;
import io.github.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.dav.mediation.domain.FlowTransactionsView} entity. This class is used
 * in {@link com.dav.mediation.web.rest.FlowTransactionsViewResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /flow-transactions-views?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FlowTransactionsViewCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private UUIDFilter flowId;

    private StringFilter flowName;

    private StringFilter flowType;

    private StringFilter senderIs;

    private StringFilter senderProtocol;

    private StringFilter sourceInstance;

    private StringFilter receiverIs;

    private StringFilter receiverProtocol;

    private StringFilter targetInstance;

    private StringFilter filePayload;

    private InstantFilter flowDateTime;

    private StringFilter status;

    public FlowTransactionsViewCriteria() {
    }

    public FlowTransactionsViewCriteria(FlowTransactionsViewCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.flowId = other.flowId == null ? null : other.flowId.copy();
        this.flowName = other.flowName == null ? null : other.flowName.copy();
        this.flowType = other.flowType == null ? null : other.flowType.copy();
        this.senderIs = other.senderIs == null ? null : other.senderIs.copy();
        this.senderProtocol = other.senderProtocol == null ? null : other.senderProtocol.copy();
        this.sourceInstance = other.sourceInstance == null ? null : other.sourceInstance.copy();
        this.receiverIs = other.receiverIs == null ? null : other.receiverIs.copy();
        this.receiverProtocol = other.receiverProtocol == null ? null : other.receiverProtocol.copy();
        this.targetInstance = other.targetInstance == null ? null : other.targetInstance.copy();
        this.filePayload = other.filePayload == null ? null : other.filePayload.copy();
        this.flowDateTime = other.flowDateTime == null ? null : other.flowDateTime.copy();
        this.status = other.status == null ? null : other.status.copy();
    }

    @Override
    public FlowTransactionsViewCriteria copy() {
        return new FlowTransactionsViewCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public UUIDFilter getFlowId() {
        return flowId;
    }

    public void setFlowId(UUIDFilter flowId) {
        this.flowId = flowId;
    }

    public StringFilter getFlowName() {
        return flowName;
    }

    public void setFlowName(StringFilter flowName) {
        this.flowName = flowName;
    }

    public StringFilter getFlowType() {
        return flowType;
    }

    public void setFlowType(StringFilter flowType) {
        this.flowType = flowType;
    }

    public StringFilter getSenderIs() {
        return senderIs;
    }

    public void setSenderIs(StringFilter senderIs) {
        this.senderIs = senderIs;
    }

    public StringFilter getSenderProtocol() {
        return senderProtocol;
    }

    public void setSenderProtocol(StringFilter senderProtocol) {
        this.senderProtocol = senderProtocol;
    }

    public StringFilter getSourceInstance() {
        return sourceInstance;
    }

    public void setSourceInstance(StringFilter sourceInstance) {
        this.sourceInstance = sourceInstance;
    }

    public StringFilter getReceiverIs() {
        return receiverIs;
    }

    public void setReceiverIs(StringFilter receiverIs) {
        this.receiverIs = receiverIs;
    }

    public StringFilter getReceiverProtocol() {
        return receiverProtocol;
    }

    public void setReceiverProtocol(StringFilter receiverProtocol) {
        this.receiverProtocol = receiverProtocol;
    }

    public StringFilter getTargetInstance() {
        return targetInstance;
    }

    public void setTargetInstance(StringFilter targetInstance) {
        this.targetInstance = targetInstance;
    }

    public StringFilter getFilePayload() {
        return filePayload;
    }

    public void setFilePayload(StringFilter filePayload) {
        this.filePayload = filePayload;
    }

    public InstantFilter getFlowDateTime() {
        return flowDateTime;
    }

    public void setFlowDateTime(InstantFilter flowDateTime) {
        this.flowDateTime = flowDateTime;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FlowTransactionsViewCriteria that = (FlowTransactionsViewCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(flowId, that.flowId) &&
            Objects.equals(flowName, that.flowName) &&
            Objects.equals(flowType, that.flowType) &&
            Objects.equals(senderIs, that.senderIs) &&
            Objects.equals(senderProtocol, that.senderProtocol) &&
            Objects.equals(sourceInstance, that.sourceInstance) &&
            Objects.equals(receiverIs, that.receiverIs) &&
            Objects.equals(receiverProtocol, that.receiverProtocol) &&
            Objects.equals(targetInstance, that.targetInstance) &&
            Objects.equals(filePayload, that.filePayload) &&
            Objects.equals(flowDateTime, that.flowDateTime) &&
            Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        flowId,
        flowName,
        flowType,
        senderIs,
        senderProtocol,
        sourceInstance,
        receiverIs,
        receiverProtocol,
        targetInstance,
        filePayload,
        flowDateTime,
        status
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowTransactionsViewCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (flowId != null ? "flowId=" + flowId + ", " : "") +
                (flowName != null ? "flowName=" + flowName + ", " : "") +
                (flowType != null ? "flowType=" + flowType + ", " : "") +
                (senderIs != null ? "senderIs=" + senderIs + ", " : "") +
                (senderProtocol != null ? "senderProtocol=" + senderProtocol + ", " : "") +
                (sourceInstance != null ? "sourceInstance=" + sourceInstance + ", " : "") +
                (receiverIs != null ? "receiverIs=" + receiverIs + ", " : "") +
                (receiverProtocol != null ? "receiverProtocol=" + receiverProtocol + ", " : "") +
                (targetInstance != null ? "targetInstance=" + targetInstance + ", " : "") +
                (filePayload != null ? "filePayload=" + filePayload + ", " : "") +
                (flowDateTime != null ? "flowDateTime=" + flowDateTime + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
            "}";
    }

}
