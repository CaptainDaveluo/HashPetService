package com.hashpet.service.implement;

import com.hashpet.dao.FacilitatorMapper;
import com.hashpet.pojo.Facilitator;
import com.hashpet.service.IFacilitatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("facService")
public class FacilitatorService implements IFacilitatorService{

    @Autowired
    private FacilitatorMapper facMapper;

    public List<Facilitator> getAllFacilitators() {
        return facMapper.selectAll();
    }
}
