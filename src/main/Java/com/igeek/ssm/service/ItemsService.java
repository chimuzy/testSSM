package com.igeek.ssm.service;

import com.igeek.ssm.dao.ItemsMapper;
import com.igeek.ssm.pojo.Items;
import com.igeek.ssm.pojo.ItemsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemsService {

    @Autowired
    private ItemsMapper mapper;

    @Transactional(readOnly = true)
    public List<Items> findAll(String query){
        if(query==null){
            query="";
        }
        ItemsExample example=new ItemsExample();
        ItemsExample.Criteria c=example.createCriteria();
        c.andNameLike("%"+query+"%");
        List<Items> itemsList = mapper.selectByExampleWithBLOBs(example);
        return itemsList;
    }
}