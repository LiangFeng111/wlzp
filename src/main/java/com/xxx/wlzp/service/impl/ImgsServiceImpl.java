package com.xxx.wlzp.service.impl;

import com.xxx.wlzp.entity.Imgs;
import com.xxx.wlzp.mapper.ImgsMapper;
import com.xxx.wlzp.service.ImgsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service //设置自动注入业务层的bean
public class ImgsServiceImpl implements ImgsService {

    @Resource
    ImgsMapper imgsMapper;

    @Override
    public List<Imgs> findByQQ(String qq) {
        return imgsMapper.findByQQ(qq);
    }
}
