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
 * Criteria class for the {@link com.dav.mediation.domain.FlowDetails} entity. This class is used
 * in {@link com.dav.mediation.web.rest.FlowDetailsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /flow-details?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FlowDetailsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private UUIDFilter flowId;

    private StringFilter flowName;

    private StringFilter mediationSystem;

    private StringFilter source;

    private StringFilter destination;

    private StringFilter fileName;

    private InstantFilter transactionDate;

    private UUIDFilter transactionId;

    private StringFilter format;

    private StringFilter sourceEndpointInterface;

    private StringFilter destinationEndpointInterface;

    private StringFilter acknowledgmentExpected;

    private StringFilter acknowledgmentReceived;

    public FlowDetailsCriteria() {
    }

    public FlowDetailsCriteria(FlowDetailsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.flowId = other.flowId == null ? null : other.flowId.copy();
        this.flowName = other.flowName == null ? null : other.flowName.copy();
        this.mediationSystem = other.mediationSystem == null ? null : other.mediationSystem.copy();
        this.source = other.source == null ? null : other.source.copy();
        this.destination = other.destination == null ? null : other.destination.copy();
        this.fileName = other.fileName == null ? null : other.fileName.copy();
        this.transactionDate = other.transactionDate == null ? null : other.transactionDate.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
        this.format = other.format == null ? null : other.format.copy();
        this.sourceEndpointInterface = other.sourceEndpointInterface == null ? null : other.sourceEndpointInterface.copy();
        this.destinationEndpointInterface = other.destinationEndpointInterface == null ? null : other.destinationEndpointInterface.copy();
        this.acknowledgmentExpected = other.acknowledgmentExpected == null ? null : other.acknowledgmentExpected.copy();
        this.acknowledgmentReceived = other.acknowledgmentReceived == null ? null : other.acknowledgmentReceived.copy();
    }

    @Override
    public FlowDetailsCriteria copy() {
        return new FlowDetailsCriteria(this);
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

    public StringFilter getMediationSystem() {
        return mediationSystem;
    }

    public void setMediationSystem(StringFilter mediationSystem) {
        this.mediationSystem = mediationSystem;
    }

    public StringFilter getSource() {
        return source;
    }

    public void setSource(StringFilter source) {
        this.source = source;
    }

    public StringFilter getDestination() {
        return destination;
    }

    public void setDestination(StringFilter destination) {
        this.destination = destination;
    }

    public StringFilter getFileName() {
        return fileName;
    }

    public void setFileName(StringFilter fileName) {
        this.fileName = fileName;
    }

    public InstantFilter getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(InstantFilter transactionDate) {
        this.transactionDate = transactionDate;
    }

    public UUIDFilter getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUIDFilter transactionId) {
        this.transactionId = transactionId;
    }

    public StringFilter getFormat() {
        return format;
    }

    public void setFormat(StringFilter format) {
        this.format = format;
    }

    public StringFilter getSourceEndpointInterface() {
        return sourceEndpointInterface;
    }

    public void setSourceEndpointInterface(StringFilter sourceEndpointInterface) {
        this.sourceEndpointInterface = sourceEndpointInterface;
    }

    public StringFilter getDestinationEndpointInterface() {
        return destinationEndpointInterface;
    }

    public void setDestinationEndpointInterface(StringFilter destinationEndpointInterface) {
        this.destinationEndpointInterface = destinationEndpointInterface;
    }

    public StringFilter getAcknowledgmentExpected() {
        return acknowledgmentExpected;
    }

    public void setAcknowledgmentExpected(StringFilter acknowledgmentExpected) {
        this.acknowledgmentExpected = acknowledgmentExpected;
    }

    public StringFilter getAcknowledgmentReceived() {
        return acknowledgmentReceived;
    }

    public void setAcknowledgmentReceived(StringFilter acknowledgmentReceived) {
        this.acknowledgmentReceived = acknowledgmentReceived;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FlowDetailsCriteria that = (FlowDetailsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(flowId, that.flowId) &&
            Objects.equals(flowName, that.flowName) &&
            Objects.equals(mediationSystem, that.mediationSystem) &&
            Objects.equals(source, that.source) &&
            Objects.equals(destination, that.destination) &&
            Objects.equals(fileName, that.fileName) &&
            Objects.equals(transactionDate, that.transactionDate) &&
            Objects.equals(transactionId, that.transactionId) &&
            Objects.equals(format, that.format) &&
            Objects.equals(sourceEndpointInterface, that.sourceEndpointInterface) &&
            Objects.equals(destinationEndpointInterface, that.destinationEndpointInterface) &&
            Objects.equals(acknowledgmentExpected, that.acknowledgmentExpected) &&
            Objects.equals(acknowledgmentReceived, that.acknowledgmentReceived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        flowId,
        flowName,
        mediationSystem,
        source,
        destination,
        fileName,
        transactionDate,
        transactionId,
        format,
        sourceEndpointInterface,
        destinationEndpointInterface,
        acknowledgmentExpected,
        acknowledgmentReceived
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowDetailsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (flowId != null ? "flowId=" + flowId + ", " : "") +
                (flowName != null ? "flowName=" + flowName + ", " : "") +
                (mediationSystem != null ? "mediationSystem=" + mediationSystem + ", " : "") +
                (source != null ? "source=" + source + ", " : "") +
                (destination != null ? "destination=" + destination + ", " : "") +
                (fileName != null ? "fileName=" + fileName + ", " : "") +
                (transactionDate != null ? "transactionDate=" + transactionDate + ", " : "") +
                (transactionId != null ? "transactionId=" + transactionId + ", " : "") +
                (format != null ? "format=" + format + ", " : "") +
                (sourceEndpointInterface != null ? "sourceEndpointInterface=" + sourceEndpointInterface + ", " : "") +
                (destinationEndpointInterface != null ? "destinationEndpointInterface=" + destinationEndpointInterface + ", " : "") +
                (acknowledgmentExpected != null ? "acknowledgmentExpected=" + acknowledgmentExpected + ", " : "") +
                (acknowledgmentReceived != null ? "acknowledgmentReceived=" + acknowledgmentReceived + ", " : "") +
            "}";
    }

}
