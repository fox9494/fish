package org.chen.fish.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.chen.fish.entity.City;

@Mapper
public interface CityMapper {

    @Select("SELECT id, name, state, country FROM city WHERE state = #{state} and name=#{name}")
    City findByState(@Param(value ="state")String state, @Param(value = "name") String name);
}
