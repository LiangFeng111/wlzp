package com.xxx.wlzp.mapper;

import com.xxx.wlzp.entity.Imgs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper //设置自动注入数据层的bean
public interface ImgsMapper {
    @Select("select * from imgs where qq=#{qq}")
    List<Imgs> findByQQ(@Param("qq") String qq);

    @Insert("insert into imgs values (null, #{qq} ,#{img},null,null)")
    int addImg(@Param("qq") String qq,@Param("img") String imgUrl);
}
