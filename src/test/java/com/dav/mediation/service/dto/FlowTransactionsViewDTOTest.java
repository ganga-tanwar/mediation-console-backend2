package com.dav.mediation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowTransactionsViewDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowTransactionsViewDTO.class);
        FlowTransactionsViewDTO flowTransactionsViewDTO1 = new FlowTransactionsViewDTO();
        flowTransactionsViewDTO1.setId(1L);
        FlowTransactionsViewDTO flowTransactionsViewDTO2 = new FlowTransactionsViewDTO();
        assertThat(flowTransactionsViewDTO1).isNotEqualTo(flowTransactionsViewDTO2);
        flowTransactionsViewDTO2.setId(flowTransactionsViewDTO1.getId());
        assertThat(flowTransactionsViewDTO1).isEqualTo(flowTransactionsViewDTO2);
        flowTransactionsViewDTO2.setId(2L);
        assertThat(flowTransactionsViewDTO1).isNotEqualTo(flowTransactionsViewDTO2);
        flowTransactionsViewDTO1.setId(null);
        assertThat(flowTransactionsViewDTO1).isNotEqualTo(flowTransactionsViewDTO2);
    }
}
