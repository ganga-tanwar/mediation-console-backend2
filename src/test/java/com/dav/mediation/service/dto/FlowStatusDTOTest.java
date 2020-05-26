package com.dav.mediation.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowStatusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowStatusDTO.class);
        FlowStatusDTO flowStatusDTO1 = new FlowStatusDTO();
        flowStatusDTO1.setId(1L);
        FlowStatusDTO flowStatusDTO2 = new FlowStatusDTO();
        assertThat(flowStatusDTO1).isNotEqualTo(flowStatusDTO2);
        flowStatusDTO2.setId(flowStatusDTO1.getId());
        assertThat(flowStatusDTO1).isEqualTo(flowStatusDTO2);
        flowStatusDTO2.setId(2L);
        assertThat(flowStatusDTO1).isNotEqualTo(flowStatusDTO2);
        flowStatusDTO1.setId(null);
        assertThat(flowStatusDTO1).isNotEqualTo(flowStatusDTO2);
    }
}
