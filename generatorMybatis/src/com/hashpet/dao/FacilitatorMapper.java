package com.hashpet.dao;

import com.hashpet.pojo.Facilitator;

public interface FacilitatorMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(Facilitator record);

    int insertSelective(Facilitator record);

    Facilitator selectByPrimaryKey(Integer shopId);

    int updateByPrimaryKeySelective(Facilitator record);

    int updateByPrimaryKeyWithBLOBs(Facilitator record);

    int updateByPrimaryKey(Facilitator record);
}