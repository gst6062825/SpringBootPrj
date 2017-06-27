package com.flow.dal.mapper;

import com.flow.dal.po.ConfigPO;
import com.flow.dal.po.ConfigPOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConfigPOMapper {
    long countByExample(ConfigPOExample example);

    int deleteByExample(ConfigPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigPO record);

    int insertSelective(ConfigPO record);

    List<ConfigPO> selectByExample(ConfigPOExample example);

    ConfigPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigPO record, @Param("example") ConfigPOExample example);

    int updateByExample(@Param("record") ConfigPO record, @Param("example") ConfigPOExample example);

    int updateByPrimaryKeySelective(ConfigPO record);

    int updateByPrimaryKey(ConfigPO record);

    int logicallyDeleteByPrimaryKey(Integer id);

    int logicallyDeleteByExample(ConfigPOExample example);

    int insertBatch(List<ConfigPO> records);
}