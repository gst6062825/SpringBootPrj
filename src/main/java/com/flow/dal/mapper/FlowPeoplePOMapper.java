package com.flow.dal.mapper;

import com.flow.dal.po.FlowPeoplePO;
import com.flow.dal.po.FlowPeoplePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FlowPeoplePOMapper {
    long countByExample(FlowPeoplePOExample example);

    int deleteByExample(FlowPeoplePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FlowPeoplePO record);

    int insertSelective(FlowPeoplePO record);

    FlowPeoplePO selectOneByExample(FlowPeoplePOExample example);

    List<FlowPeoplePO> selectByExamplePaging(@Param("example") FlowPeoplePOExample example,
        @Param("offset") int offset, @Param("limit") int limit);

    List<FlowPeoplePO> selectByExample(FlowPeoplePOExample example);

    FlowPeoplePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FlowPeoplePO record,
        @Param("example") FlowPeoplePOExample example);

    int logicallyDeleteByPrimaryKey(Integer id);

    int logicallyDeleteByExample(FlowPeoplePOExample example);

    int updateByExample(@Param("record") FlowPeoplePO record,
        @Param("example") FlowPeoplePOExample example);

    int updateByPrimaryKeySelective(FlowPeoplePO record);

    int updateByPrimaryKey(FlowPeoplePO record);
}