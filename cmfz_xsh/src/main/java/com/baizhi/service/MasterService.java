package com.baizhi.service;

import com.baizhi.entity.Master;

import java.util.List;

public interface MasterService {

    List<Master> SelectAllMaster();

    void insertMaster(Master master);

    void deleteMaster(int id);

    void updateMaster(Master master);




}
