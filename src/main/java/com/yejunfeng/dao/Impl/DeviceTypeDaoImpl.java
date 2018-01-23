package com.yejunfeng.dao.Impl;

import com.yejunfeng.PO.DeviceTypePO;
import com.yejunfeng.dao.DeviceTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceTypeDaoImpl implements DeviceTypeDao{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void saveDevType(DeviceTypePO devTypePO) {
        hibernateTemplate.save(devTypePO);
    }

    @Override
    public void deleteDevType(String typeId) {
        hibernateTemplate.delete(searchById(typeId));
    }

    @Override
    public void updateDevType(DeviceTypePO devTypePO) {
        hibernateTemplate.update(devTypePO);
    }

    @Override
    public DeviceTypePO searchById(String typeId) {
        return hibernateTemplate.get(DeviceTypePO.class,typeId);
    }

    @Override
    public List<DeviceTypePO> searchAll() {
        String hql = "from DeviceTypePO type";
        return (List<DeviceTypePO>) hibernateTemplate.find(hql);
    }
}
