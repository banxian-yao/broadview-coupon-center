package com.broadview.coupon.user.external;

import com.broadview.coupon.shared.beans.TemplateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 券模板接口，不能使用Feign
 */
@Component
public class TemplateClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("broadview.templateserivce.url.batch")
    private String batchUrl;

    @Value("broadview.templateserivce.url.single")
    private String singleUrl;

    public Map<Long, TemplateInfo> getTemplateBatch(@RequestParam("ids") Collection<Long> ids){
        ResponseEntity re = restTemplate.getForEntity(batchUrl,Map.class, Collections.singletonMap("ids", ids));
        return (Map<Long, TemplateInfo>)re.getBody();
    }

    public TemplateInfo getTemplate(@RequestParam("id") Long id){
        return restTemplate.getForEntity(singleUrl,TemplateInfo.class, Collections.singletonMap("id", id)).getBody();
    }

}
