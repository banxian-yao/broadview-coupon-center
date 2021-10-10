package com.broadview.coupon.template.controller;

import com.broadview.coupon.shared.beans.rules.CalculationRule;
import com.broadview.coupon.template.beans.TemplateRequest;
import com.broadview.coupon.template.entity.CouponTemplate;
import com.broadview.coupon.template.service.intf.CouponTemplateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CouponTemplateController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class CouponTemplateControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CouponTemplateService service;

    @Test
    public void testAddTemplate() throws Exception {
        String name="testTemp";
        TemplateRequest t1 = new TemplateRequest();
        t1.setName(name);
        t1.setDesc("test desc");
        t1.setAvailable(true);
        t1.setExpired(false);
        t1.setTotal(123);
        t1.setRule(new CalculationRule());
        t1.setType("test");
        CouponTemplate c1=new CouponTemplate();
        c1.setName(name);
        given(service.createTemplate(t1)).willReturn(c1);

        mvc.perform(post("/template/add").content(asJsonString(t1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)));
    }
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
