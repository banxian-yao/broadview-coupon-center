package com.broadview.coupon.user.external;

import com.broadview.coupon.shared.beans.TemplateInfo;
import com.broadview.coupon.user.external.fallback.TemplateClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

/**
 * 券模板Feign接口
 */
@FeignClient(value = "coupon-template-service",
        // 指定降级接口
        fallback = TemplateClientFallback.class)
public interface TemplateClient {

    @RequestMapping(value = "/template/getBatch", method = RequestMethod.GET)
    Map<Long, TemplateInfo> getTemplateBatch(@RequestParam("ids") Collection<Long> ids);

    @GetMapping("/template/get")
    TemplateInfo getTemplate(@RequestParam("id") Long id);

    @PutMapping("/template/inactive")
    public void inactiveCoupon(@RequestParam("id") Long id);

}
