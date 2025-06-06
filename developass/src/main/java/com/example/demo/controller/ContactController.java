package com.example.demo.controller;

import com.example.demo.entity.Contact;
import com.example.demo.mapper.ContactMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Controller
public class ContactController {

    @Autowired
    private ContactMapper contactMapper;

    @GetMapping("/list")
    public String list(Model model, HttpSession session,
                      @RequestParam(defaultValue = "1") Integer page,
                      @RequestParam(required = false) String keyword,
                      @RequestParam(required = false) String groupType) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        QueryWrapper<Contact> queryWrapper = new QueryWrapper<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            queryWrapper.lambda()
                .like(Contact::getName, keyword)
                .or().like(Contact::getPhone, keyword)
                .or().like(Contact::getEmail, keyword)
                .or().like(Contact::getCompany, keyword)
                .or().like(Contact::getGroupType, keyword)
                .or().like(Contact::getIndustry, keyword);
        }
        if (groupType != null && !groupType.trim().isEmpty()) {
            queryWrapper.eq("group_type", groupType);
            model.addAttribute("groupType", groupType);
        }
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Contact> contactPage = contactMapper.selectPage(
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(page, 5), queryWrapper);
        model.addAttribute("contacts", contactPage.getRecords());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contactPage.getPages());
        model.addAttribute("keyword", keyword);
        return "list";
    }

    @GetMapping("/add")
    public String showAddPage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "add";
    }

    @PostMapping("/add")
    public String addContact(@RequestParam String name,
                             @RequestParam String phone,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String company,
                             @RequestParam(required = false) String groupType,
                             @RequestParam(required = false) String industry,
                             HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Contact contact = new Contact();
        contact.setName(name);
        contact.setPhone(phone);
        contact.setEmail(email);
        contact.setCompany(company);
        contact.setGroupType(groupType);
        contact.setIndustry(industry);
        contactMapper.insert(contact);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Contact contact = contactMapper.selectById(id);
        model.addAttribute("contact", contact);
        return "edit";
    }

    @PostMapping("/edit")
    public String editContact(@RequestParam Long id,
                              @RequestParam String name,
                              @RequestParam String phone,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String company,
                              @RequestParam(required = false) String groupType,
                              @RequestParam(required = false) String industry,
                              HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setPhone(phone);
        contact.setEmail(email);
        contact.setCompany(company);
        contact.setGroupType(groupType);
        contact.setIndustry(industry);
        contactMapper.updateById(contact);
        return "redirect:/list";
    }

    @RequestMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        contactMapper.deleteById(id);
        return "redirect:/list";
    }

    @GetMapping("/export")
    public void exportExcel(HttpSession session, HttpServletResponse response) throws Exception {
        if (session.getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("联系人列表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        List<Contact> contacts = contactMapper.selectList(null);
        EasyExcel.write(response.getOutputStream(), Contact.class).sheet("联系人").doWrite(contacts);
    }
} 