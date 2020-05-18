package com.dav.mediation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowEventDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowEventDetailsDTO.class);
        FlowEventDetailsDTO flowEventDetailsDTO1 = new FlowEventDetailsDTO();
        flowEventDetailsDTO1.setId(1L);
        FlowEventDetailsDTO flowEventDetailsDTO2 = new FlowEventDetailsDTO();
        assertThat(flowEventDetailsDTO1).isNotEqualTo(flowEventDetailsDTO2);
        flowEventDetailsDTO2.setId(flowEventDetailsDTO1.getId());
        assertThat(flowEventDetailsDTO1).isEqualTo(flowEventDetailsDTO2);
        flowEventDetailsDTO2.setId(2L);
        assertThat(flowEventDetailsDTO1).isNotEqualTo(flowEventDetailsDTO2);
        flowEventDetailsDTO1.setId(null);
        assertThat(flowEventDetailsDTO1).isNotEqualTo(flowEventDetailsDTO2);
    }
}
