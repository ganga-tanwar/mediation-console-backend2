package com.dav.mediation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowDetails.class);
        FlowDetails flowDetails1 = new FlowDetails();
        flowDetails1.setId(1L);
        FlowDetails flowDetails2 = new FlowDetails();
        flowDetails2.setId(flowDetails1.getId());
        assertThat(flowDetails1).isEqualTo(flowDetails2);
        flowDetails2.setId(2L);
        assertThat(flowDetails1).isNotEqualTo(flowDetails2);
        flowDetails1.setId(null);
        assertThat(flowDetails1).isNotEqualTo(flowDetails2);
    }
}
