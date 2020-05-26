package com.dav.mediation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowStatus.class);
        FlowStatus flowStatus1 = new FlowStatus();
        flowStatus1.setId(1L);
        FlowStatus flowStatus2 = new FlowStatus();
        flowStatus2.setId(flowStatus1.getId());
        assertThat(flowStatus1).isEqualTo(flowStatus2);
        flowStatus2.setId(2L);
        assertThat(flowStatus1).isNotEqualTo(flowStatus2);
        flowStatus1.setId(null);
        assertThat(flowStatus1).isNotEqualTo(flowStatus2);
    }
}
