package com.broadview.coupon.template.controller;

import com.broadview.coupon.template.beans.TemplateRequest;
import com.broadview.coupon.template.entity.CouponTemplate;
import com.broadview.coupon.template.service.intf.CouponTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(CouponTemplateController.class)
public class CouponTemplateControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CouponTemplateService service;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {
        String name="testTemp";
        TemplateRequest t1 = new TemplateRequest();
        t1.setName(name);
        CouponTemplate c1=new CouponTemplate();
        c1.setName(name);
        given(service.createTemplate(t1)).willReturn(c1);

        mvc.perform(post("/template/add")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)));
    }
    public double meth(byte x, double y){
        return (short)x/y*2;
    }

}
