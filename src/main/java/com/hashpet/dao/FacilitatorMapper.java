package com.hashpet.dao;

import com.hashpet.pojo.Facilitator;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilitatorMapper {
    int deleteByPrimaryKey(Integer shopId);

    int insert(Facilitator record);

    int insertSelective(Facilitator record);

    Facilitator selectByPrimaryKey(Integer shopId);

    List<Facilitator> selectAll();

    int updateByPrimaryKeySelective(Facilitator record);

    int updateByPrimaryKeyWithBLOBs(Facilitator record);

    int updateByPrimaryKey(Facilitator record);
}