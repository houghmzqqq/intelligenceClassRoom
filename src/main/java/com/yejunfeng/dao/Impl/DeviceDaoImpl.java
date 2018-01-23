package com.yejunfeng.dao.Impl;

import com.yejunfeng.PO.DevicePO;
import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.dao.DeviceDao;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceDaoImpl implements DeviceDao{

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private DividePageVO dividePage;

    @Override
    public void addDevice(DevicePO devicePO) {
        hibernateTemplate.saveOrUpdate(devicePO);
    }

    @Override
    public void deleteDevice(String devId) {
        hibernateTemplate.delete(searchById(devId));
    }

    @Override
    public void updateDevice(DevicePO devicePO) {
        hibernateTemplate.update(devicePO);
    }

    @Override
    public List<DevicePO> searchAllDev() {
        String hql = "from DevicePO dev";
        return (List<DevicePO>) hibernateTemplate.find(hql);
    }

    @Override
    public DevicePO searchById(String devId) {
        return hibernateTemplate.get(DevicePO.class,devId);
    }

    @Override
    public List<DevicePO> searchByTypeId(String typeId) {
        String hql = "from DevicePO dev where dev.deviceTypePO.devTypeId=?";
        return (List<DevicePO>) hibernateTemplate.find(hql,typeId);
    }

    @Override
    public DividePageVO searchByDivide(int thisPage, int rowOfEachPage) {
        String hql = "select count(*) from DevicePO dev";
        int pageCount = Integer.parseInt(String.valueOf((Long) hibernateTemplate.find(hql).listIterator().next()));
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DevicePO.class);
        List<DevicePO> devicePOS = (List<DevicePO>) hibernateTemplate.findByCriteria(detachedCriteria,(thisPage-1)*rowOfEachPage,rowOfEachPage);

        if((pageCount%rowOfEachPage) != 0)
        {
            dividePage.setLastPage((pageCount/rowOfEachPage)+1);
        }
        else
        {
            dividePage.setLastPage((pageCount/rowOfEachPage));
        }
        if(thisPage >= dividePage.getLastPage())
        {
            dividePage.setNextPage(thisPage);
        }
        else
        {
            dividePage.setNextPage(thisPage + 1);
        }
        dividePage.setPrePage(thisPage-1);
        dividePage.setPageCount(pageCount);
        dividePage.setThisPage(thisPage);
        dividePage.setDevicePOS(devicePOS);
//        System.out.println(pageCount);
//        System.out.println(dividePage.getLastPage());
        return dividePage;
    }

    @Override
    public DividePageVO searchByDivide(int thisPage, int rowOfEachPage, String typeId) {
        String hql = "from DevicePO dev where dev.deviceTypePO.devTypeId=:typeId";
        Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("typeId",typeId);
        query.setFirstResult((thisPage-1)*rowOfEachPage);
        query.setMaxResults(rowOfEachPage);
        List<DevicePO> devicePOS = query.list();

        hql = "select count(*) from DevicePO dev where dev.deviceTypePO.devTypeId=?";
        int pageCount = Integer.parseInt(String.valueOf((Long) hibernateTemplate.find(hql,typeId).listIterator().next()));

        if((pageCount%rowOfEachPage) != 0)
        {
            dividePage.setLastPage((pageCount/rowOfEachPage)+1);
        }
        else
        {
            dividePage.setLastPage((pageCount/rowOfEachPage));
        }
        if(thisPage >= dividePage.getLastPage())
        {
            dividePage.setNextPage(thisPage);
        }
        else
        {
            dividePage.setNextPage(thisPage + 1);
        }
        dividePage.setPrePage(thisPage - 1);
        dividePage.setPageCount(pageCount);
        dividePage.setThisPage(thisPage);
        dividePage.setDevicePOS(devicePOS);

        return dividePage;
    }
}
