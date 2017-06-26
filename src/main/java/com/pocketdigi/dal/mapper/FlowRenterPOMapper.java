package com.pocketdigi.dal.mapper;

import com.pocketdigi.dal.po.FlowRenterPO;
import com.pocketdigi.dal.po.FlowRenterPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FlowRenterPOMapper {
    long countByExample(FlowRenterPOExample example);

    int deleteByExample(FlowRenterPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FlowRenterPO record);

    int insertSelective(FlowRenterPO record);

    FlowRenterPO selectOneByExample(FlowRenterPOExample example);

    List<FlowRenterPO> selectByExamplePaging(@Param("example") FlowRenterPOExample example,
        @Param("offset") int offset, @Param("limit") int limit);

    List<FlowRenterPO> selectByExample(FlowRenterPOExample example);

    FlowRenterPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FlowRenterPO record,
        @Param("example") FlowRenterPOExample example);

    int logicallyDeleteByPrimaryKey(Integer id);

    int logicallyDeleteByExample(FlowRenterPOExample example);

    int updateByExample(@Param("record") FlowRenterPO record,
        @Param("example") FlowRenterPOExample example);

    int updateByPrimaryKeySelective(FlowRenterPO record);

    int updateByPrimaryKey(FlowRenterPO record);
}