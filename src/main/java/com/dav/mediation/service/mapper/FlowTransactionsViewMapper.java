package com.dav.mediation.service.mapper;


import com.dav.mediation.domain.*;
import com.dav.mediation.service.dto.FlowTransactionsViewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FlowTransactionsView} and its DTO {@link FlowTransactionsViewDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FlowTransactionsViewMapper extends EntityMapper<FlowTransactionsViewDTO, FlowTransactionsView> {



    default FlowTransactionsView fromId(Long id) {
        if (id == null) {
            return null;
        }
        FlowTransactionsView flowTransactionsView = new FlowTransactionsView();
        flowTransactionsView.setId(id);
        return flowTransactionsView;
    }
}
