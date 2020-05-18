package com.dav.mediation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowEventDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowEventDetails.class);
        FlowEventDetails flowEventDetails1 = new FlowEventDetails();
        flowEventDetails1.setId(1L);
        FlowEventDetails flowEventDetails2 = new FlowEventDetails();
        flowEventDetails2.setId(flowEventDetails1.getId());
        assertThat(flowEventDetails1).isEqualTo(flowEventDetails2);
        flowEventDetails2.setId(2L);
        assertThat(flowEventDetails1).isNotEqualTo(flowEventDetails2);
        flowEventDetails1.setId(null);
        assertThat(flowEventDetails1).isNotEqualTo(flowEventDetails2);
    }
}
