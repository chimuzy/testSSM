package com.igeek.ssm.controller;

import com.igeek.ssm.pojo.Items;
import com.igeek.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService service;

    @RequestMapping("/findAll.action")
    public String findAll(String query, Model model){
        List<Items> itemsList = service.findAll(query);
        model.addAttribute("itemsList",itemsList);
        System.out.println("items/itemsList.jsp");
        return "itemsList.jsp";
    }
}
