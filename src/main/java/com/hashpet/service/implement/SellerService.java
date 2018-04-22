package com.hashpet.service.implement;

import com.hashpet.dao.SellerInfoMapper;
import com.hashpet.pojo.SellerInfo;
import com.hashpet.pojo.User;
import com.hashpet.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sellerService")
public class SellerService implements ISellerService{
    @Autowired
    private SellerInfoMapper sellerDao;

    public void newSeller(SellerInfo seller) {
        sellerDao.insert(seller);
    }

    public SellerInfo querySellerById(Integer sellerId){
        return sellerDao.selectByPrimaryKey(sellerId);
    }

    public SellerInfoMapper getSellerDao() {
        return sellerDao;
    }

    public void setSellerDao(SellerInfoMapper sellerDao) {
        this.sellerDao = sellerDao;
    }

    public SellerInfo getSellerInfoByUser(User user) {
        return sellerDao.selectByUserId(user.getUserid());
    }
}
