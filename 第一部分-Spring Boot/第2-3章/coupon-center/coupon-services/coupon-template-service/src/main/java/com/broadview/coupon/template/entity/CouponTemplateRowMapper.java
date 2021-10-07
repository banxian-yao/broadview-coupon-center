package com.broadview.coupon.template.entity;

import com.broadview.coupon.template.converter.CouponTypeConverter;
import com.broadview.coupon.template.converter.RuleConverter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CouponTemplateRowMapper implements RowMapper<CouponTemplate> {

    @Override
    public CouponTemplate mapRow(ResultSet resultSet, int i) throws SQLException {
        CouponTemplate template=new CouponTemplate();
        template.setId(resultSet.getLong("id"));
        template.setName(resultSet.getString("name"));
        template.setAvailable(resultSet.getBoolean("available"));
        template.setCategory(new CouponTypeConverter().convertToEntityAttribute(resultSet.getString("type")));
        template.setCreatedTime(resultSet.getDate("created_time"));
        template.setDescription(resultSet.getString("description"));
        template.setExpired(resultSet.getBoolean("expired"));
        template.setRule(new RuleConverter().convertToEntityAttribute(resultSet.getString("rule")));
        template.setShopId(resultSet.getLong("shop_id"));
        template.setTotal(resultSet.getInt("total"));
        return template;
    }
}
