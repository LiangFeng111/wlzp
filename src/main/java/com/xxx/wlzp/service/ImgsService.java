package com.xxx.wlzp.service;

import com.xxx.wlzp.entity.Imgs;

import java.util.List;

public interface ImgsService {
    List<Imgs> findByQQ(String qq);
}
