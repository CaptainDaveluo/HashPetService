package com.hashpet.service.implement;

import com.hashpet.dao.PetInfoMapper;
import com.hashpet.pojo.PetInfo;
import com.hashpet.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("petService")
public class PetService implements IPetService{
    @Autowired
    private PetInfoMapper petInfoDao;

    public PetInfoMapper getPetInfoDao() {
        return petInfoDao;
    }

    public void setPetInfoDao(PetInfoMapper petInfoDao) {
        this.petInfoDao = petInfoDao;
    }

    public void createNewPet(PetInfo petInfo) {
        petInfoDao.insert(petInfo);
    }
}
