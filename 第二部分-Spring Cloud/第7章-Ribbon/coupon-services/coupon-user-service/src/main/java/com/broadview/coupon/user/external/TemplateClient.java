package com.broadview.coupon.user.external;

import com.broadview.coupon.shared.beans.TemplateInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

/**
 * 券模板Feign接口
 */
@FeignClient(value = "coupon-template-service")
public interface TemplateClient {

    @RequestMapping(value = "/template/getBatch", method = RequestMethod.GET)
    Map<Long, TemplateInfo> getTemplateBatch(@RequestParam("ids") Collection<Long> ids);

    @GetMapping("/template/get")
    TemplateInfo getTemplate(@RequestParam("id") Long id);

}
