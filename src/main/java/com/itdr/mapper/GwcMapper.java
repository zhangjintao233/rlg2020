package com.itdr.mapper;

import com.itdr.pojo.Gwc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GwcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gwc record);

    int insertSelective(Gwc record);

    Gwc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gwc record);

    int updateByPrimaryKey(Gwc record);

    List<Gwc> selectByUserId(Integer id);

    Gwc selectByuseridAndProductId(@Param("uid") Integer id,
                                   @Param("pid") Integer productId);

    int updateByQuantiy(Gwc g);

    int deleteByUserIdAndProductId(@Param("uid") Integer id,
                                   @Param("pid") Integer productId);

    int updateByChecked(@Param("uid") Integer id,
                        @Param("pid") Integer productId,
                        @Param("type") Integer type);

    int deleteByuserid(Integer uid);
}