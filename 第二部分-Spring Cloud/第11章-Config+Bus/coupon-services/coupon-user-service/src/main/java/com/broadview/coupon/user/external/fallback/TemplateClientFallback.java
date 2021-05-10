package com.broadview.coupon.user.external.fallback;

import com.broadview.coupon.shared.beans.TemplateInfo;
import com.broadview.coupon.user.external.TemplateClient;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * TemplateClient的降级类
 */
@Slf4j
@Component
public class TemplateClientFallback implements TemplateClient {

    public Map<Long, TemplateInfo> getTemplateBatch(Collection<Long> ids) {
        log.info("fallback logic, ids={}", ids);
        return Maps.newHashMap();
    }

    public TemplateInfo getTemplate(Long id) {
        log.info("fallback logic, id={}", id);
        return null;
    }
}
