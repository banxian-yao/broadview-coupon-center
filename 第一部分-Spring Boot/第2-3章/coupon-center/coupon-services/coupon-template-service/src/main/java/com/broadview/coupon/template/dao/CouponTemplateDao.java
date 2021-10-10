package com.broadview.coupon.template.dao;

import com.broadview.coupon.template.entity.CouponTemplate;
import com.broadview.coupon.template.entity.CouponTemplateRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
@Repository
public class CouponTemplateDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly=true)
    public List<CouponTemplate> findAll() {
        return jdbcTemplate.query("select * from coupon_template",
                new CouponTemplateRowMapper());
    }

    @Transactional
    public void updateTemplate(Long id,String name){
        jdbcTemplate.update("update coupon_template set name=? where id=?", name,id);
    }
}*/
