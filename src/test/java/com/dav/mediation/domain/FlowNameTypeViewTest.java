package com.dav.mediation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowNameTypeViewTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowNameTypeView.class);
        FlowNameTypeView flowNameTypeView1 = new FlowNameTypeView();
        flowNameTypeView1.setId(1L);
        FlowNameTypeView flowNameTypeView2 = new FlowNameTypeView();
        flowNameTypeView2.setId(flowNameTypeView1.getId());
        assertThat(flowNameTypeView1).isEqualTo(flowNameTypeView2);
        flowNameTypeView2.setId(2L);
        assertThat(flowNameTypeView1).isNotEqualTo(flowNameTypeView2);
        flowNameTypeView1.setId(null);
        assertThat(flowNameTypeView1).isNotEqualTo(flowNameTypeView2);
    }
}
