package com.dav.mediation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowEventsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowEvents.class);
        FlowEvents flowEvents1 = new FlowEvents();
        flowEvents1.setId(1L);
        FlowEvents flowEvents2 = new FlowEvents();
        flowEvents2.setId(flowEvents1.getId());
        assertThat(flowEvents1).isEqualTo(flowEvents2);
        flowEvents2.setId(2L);
        assertThat(flowEvents1).isNotEqualTo(flowEvents2);
        flowEvents1.setId(null);
        assertThat(flowEvents1).isNotEqualTo(flowEvents2);
    }
}
