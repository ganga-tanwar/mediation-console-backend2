package com.dav.mediation.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * Class representing FlowNameTypeView
 */
@Entity
@Table(name = "flow_name_type_view")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FlowNameTypeView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "flow_name", length = 50, nullable = false)
    private String flowName;

    @NotNull
    @Size(max = 50)
    @Column(name = "flow_type", length = 50, nullable = false)
    private String flowType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowName() {
        return flowName;
    }

    public FlowNameTypeView flowName(String flowName) {
        this.flowName = flowName;
        return this;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowType() {
        return flowType;
    }

    public FlowNameTypeView flowType(String flowType) {
        this.flowType = flowType;
        return this;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlowNameTypeView)) {
            return false;
        }
        return id != null && id.equals(((FlowNameTypeView) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowNameTypeView{" +
            "id=" + getId() +
            ", flowName='" + getFlowName() + "'" +
            ", flowType='" + getFlowType() + "'" +
            "}";
    }
}
