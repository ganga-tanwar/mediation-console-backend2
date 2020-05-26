package com.dav.mediation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowEventsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowEventsDTO.class);
        FlowEventsDTO flowEventsDTO1 = new FlowEventsDTO();
        flowEventsDTO1.setId(1L);
        FlowEventsDTO flowEventsDTO2 = new FlowEventsDTO();
        assertThat(flowEventsDTO1).isNotEqualTo(flowEventsDTO2);
        flowEventsDTO2.setId(flowEventsDTO1.getId());
        assertThat(flowEventsDTO1).isEqualTo(flowEventsDTO2);
        flowEventsDTO2.setId(2L);
        assertThat(flowEventsDTO1).isNotEqualTo(flowEventsDTO2);
        flowEventsDTO1.setId(null);
        assertThat(flowEventsDTO1).isNotEqualTo(flowEventsDTO2);
    }
}
