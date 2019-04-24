package com.baizhi.service.impl;

import com.baizhi.dao.MasterDao;
import com.baizhi.entity.Master;
import com.baizhi.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MasterServiceImpl implements MasterService {


    @Autowired
    MasterDao masterDao;


    @Override
    public List<Master> SelectAllMaster() {

        return masterDao.selectAll();
    }

    @Override
    public void insertMaster(Master master) {
        masterDao.insertSelective(master);
    }

    @Override
    public void deleteMaster(int id) {
        masterDao.deleteByPrimaryKey(id);
    }

    @Override
    public void updateMaster(Master master) {
        masterDao.updateByPrimaryKeySelective(master);
    }
}
