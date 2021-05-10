package com.broadview.coupon.template.converter;

import com.alibaba.fastjson.JSON;
import com.broadview.coupon.shared.beans.rules.CalculationRule;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RuleConverter implements AttributeConverter<CalculationRule, String> {

    @Override
    public String convertToDatabaseColumn(CalculationRule rule) {
        return JSON.toJSONString(rule);
    }

    @Override
    public CalculationRule convertToEntityAttribute(String rule) {
        return JSON.parseObject(rule, CalculationRule.class);
    }
}
