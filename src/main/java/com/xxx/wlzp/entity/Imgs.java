package com.xxx.wlzp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor //无参构造
@AllArgsConstructor //全参构造
@ToString
public class Imgs {
    private String qq;
    private String img;
    private int delete;
    private String time;
}
