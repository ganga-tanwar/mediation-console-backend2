package com.dav.mediation.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dav.mediation.web.rest.TestUtil;

public class FlowTransactionsViewTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FlowTransactionsView.class);
        FlowTransactionsView flowTransactionsView1 = new FlowTransactionsView();
        flowTransactionsView1.setId(1L);
        FlowTransactionsView flowTransactionsView2 = new FlowTransactionsView();
        flowTransactionsView2.setId(flowTransactionsView1.getId());
        assertThat(flowTransactionsView1).isEqualTo(flowTransactionsView2);
        flowTransactionsView2.setId(2L);
        assertThat(flowTransactionsView1).isNotEqualTo(flowTransactionsView2);
        flowTransactionsView1.setId(null);
        assertThat(flowTransactionsView1).isNotEqualTo(flowTransactionsView2);
    }
}
