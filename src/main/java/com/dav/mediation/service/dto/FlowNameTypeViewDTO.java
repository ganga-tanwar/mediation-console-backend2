package com.dav.mediation.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.dav.mediation.domain.FlowNameTypeView} entity.
 */
@ApiModel(description = "Class representing FlowNameTypeView")
public class FlowNameTypeViewDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 50)
    private String flowName;

    @NotNull
    @Size(max = 50)
    private String flowType;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlowNameTypeViewDTO)) {
            return false;
        }

        return id != null && id.equals(((FlowNameTypeViewDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlowNameTypeViewDTO{" +
            "id=" + getId() +
            ", flowName='" + getFlowName() + "'" +
            ", flowType='" + getFlowType() + "'" +
            "}";
    }
}
