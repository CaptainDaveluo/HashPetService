package com.hashpet.dao;

import com.hashpet.pojo.PetInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PetInfoMapper {
    int deleteByPrimaryKey(Integer petId);

    int insert(PetInfo record);

    int insertSelective(PetInfo record);

    PetInfo selectByPrimaryKey(Integer petId);

    int updateByPrimaryKeySelective(PetInfo record);

    int updateByPrimaryKey(PetInfo record);
}