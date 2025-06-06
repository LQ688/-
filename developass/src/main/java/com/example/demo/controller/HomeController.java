package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.example.demo.mapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class HomeController {
    
    @Autowired
    private ContactMapper contactMapper;
    
    @GetMapping("/")
    public String home(Model model) {
        // 联系人总数
        long total = contactMapper.selectCount(null);

        // 客户类型统计
        String[] groupTypes = {"VIP客户", "重要客户", "普通客户", "潜在客户"};
        Map<String, Long> groupCountMap = new LinkedHashMap<>();
        for (String type : groupTypes) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("group_type", type);
            groupCountMap.put(type, contactMapper.selectCount(wrapper));
        }

        // 行业分布统计
        String[] industries = {"IT", "制造业", "教育", "金融", "其他"};
        Map<String, Long> industryCountMap = new LinkedHashMap<>();
        for (String industry : industries) {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("industry", industry);
            industryCountMap.put(industry, contactMapper.selectCount(wrapper));
        }

        model.addAttribute("total", total);
        model.addAttribute("groupCountMap", groupCountMap);
        model.addAttribute("industryCountMap", industryCountMap);

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
} 