package com.igeek.ssm.service;

import com.igeek.ssm.dao.UserMapper;
import com.igeek.ssm.pojo.User;
import com.igeek.ssm.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Transactional(readOnly = true)
    public User login(Integer id,String username){
        UserExample example=new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andUsernameEqualTo(username);
        List<User> users = mapper.selectByExample(example);
        User user=null;
        for(User u:users){
            user=u;
        }
        return user;
    }

    @Transactional(readOnly = true)
    public Boolean validateName(String username){
        UserExample example=new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = mapper.selectByExample(example);
        if(users.size()==0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean regist(User user){
        if(this.validateName(user.getUsername())){
            mapper.insertSelective(user);
            return true;
        }else {
            return false;
        }
    }
}