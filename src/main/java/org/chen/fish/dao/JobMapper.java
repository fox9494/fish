package org.chen.fish.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.chen.fish.entity.City;
import org.chen.fish.entity.Job;

@Mapper
public interface JobMapper {

    @Select("SELECT id, name, status,version,node,timestamp FROM job WHERE status = #{status}")
    Job findByState(@Param(value = "status") Integer status);

    @Update("update job set status=#{newStatus},version=#{newVersion},node=#{node} where status=#{oldStatus} and version=#{oldVersion}")
    int updateByVersion(@Param(value = "newStatus")Integer newStatus,@Param(value = "newVersion")Integer newVersion,
                        @Param(value = "oldStatus")Integer oldStatus,@Param(value = "oldVersion")Integer oldVersion,
                        @Param(value = "node")String node);
}
