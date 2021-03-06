package com.igeek.ssm.controller;

import com.igeek.ssm.pojo.Items;
import com.igeek.ssm.service.ItemsService;
import com.igeek.ssm.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService service;

    @RequestMapping("/findAll.action")
    public String findAll(String query, Integer pageNow,Model model){
        PageVO pageVO = service.findAll(query,pageNow);
        model.addAttribute("pageVO",pageVO);
        return "itemsList.jsp";
    }

    @RequestMapping("/add.action")
    public String add(Items items, MultipartFile file) throws IOException {
        if(file!=null){
            String oldName = file.getOriginalFilename();
            if(oldName!=null&&oldName.length()>0){
                String newName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));
                file.transferTo(new File("E:/IDEA/igeek/day03/temp/"+newName));
                items.setPic("/pic/"+newName);
            }
        }
        service.add(items);
        return "redirect:findAll.action";
    }

    @RequestMapping("/findOne.action")
    public String findOne(Integer id,Model model){
        Items items=service.findOne(id);
        model.addAttribute("items",items);
        return "editItem.jsp";
    }

    @RequestMapping("/updata.action")
    public String updata(Items items,MultipartFile file) throws IOException {
        if(file!=null){
            String oldName = file.getOriginalFilename();
            if(oldName!=null && oldName.length()>0){
                String newName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));
                file.transferTo(new File("E:/IDEA/igeek/day03/temp/"+newName));
                items.setPic("/pic/"+newName);
            }
        }
        String itemspic=items.getPic();
        if(itemspic==null){
            String pic = service.findOne(items.getId()).getPic();
            items.setPic(pic);
        }
        service.update(items);
        return "redirect:findAll.action";
    }

    @RequestMapping("/delete.action")
    public String delete(@RequestParam("id") Integer[] ids){
        service.delete(ids);
        return "redirect:findAll.action";
    }
}