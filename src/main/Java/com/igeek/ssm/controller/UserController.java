package com.igeek.ssm.controller;

import com.igeek.ssm.pojo.User;
import com.igeek.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/login.action")
    public String Login(Integer id, String username, Model model, HttpSession session){
        User user = service.login(id, username);
        if(user!=null){
            session.setAttribute("user",user);
            return "../main.jsp";
        }
        else{
            model.addAttribute("msg","用户名或密码错误，请重新登录");
            model.addAttribute("username",username);
            model.addAttribute("id",id);
            return "../login.jsp";
        }
    }

    @RequestMapping("/loginout.action")
    public String loginout(Model model,HttpSession session){
        session.invalidate();
        model.addAttribute("msg","用户已登出!");
        return "../login.jsp";
    }

    @RequestMapping("/validate.action")
    @ResponseBody//将值转换成json数据传递至页面
    public Boolean validate(String username){
        boolean b=service.validateName(username);
        return b;
    }

    @RequestMapping("/regist.action")
    public String regist(User user, Model model, MultipartFile user_pic) throws IOException {
        if(user_pic!=null){
            String oldName = user_pic.getOriginalFilename();
            if(oldName!=null && oldName.length()>0){
                String newName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));
                user_pic.transferTo(new File("E:/IDEA/igeek/day03/temp/"+newName));
                user.setPic("/pic/"+newName);
            }
        }
        boolean flag=service.regist(user);
        if(flag){
            model.addAttribute("username",user.getUsername());
            return "../login.jsp";
        }else {

            return "../regist.jsp";
        }
    }
}
