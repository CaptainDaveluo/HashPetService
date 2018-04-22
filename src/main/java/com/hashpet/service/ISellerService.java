package com.hashpet.service;

import com.hashpet.pojo.SellerInfo;
import com.hashpet.pojo.User;

public interface ISellerService {
    public void newSeller(SellerInfo seller);
    public SellerInfo getSellerInfoByUser(User user);
    public SellerInfo querySellerById(Integer sellerId);
}
