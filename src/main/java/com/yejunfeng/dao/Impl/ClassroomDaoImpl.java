package com.yejunfeng.dao.Impl;

import com.yejunfeng.PO.ClassroomPO;
import com.yejunfeng.dao.ClassroomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClassroomDaoImpl implements ClassroomDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void saveClassroom(ClassroomPO classroomPO) {
        hibernateTemplate.save(classroomPO);
    }

    public void deleteClassroom(String roomId) {
        hibernateTemplate.delete(searchById(roomId));
    }

    public void updateClassroom(ClassroomPO classroomPO) {
        hibernateTemplate.update(classroomPO);
    }

    public ClassroomPO searchById(String roomId) {
        return hibernateTemplate.get(ClassroomPO.class,roomId);
    }

    public List<ClassroomPO> searchAll() {
        String hql = "from ClassroomPO cr";
        return (List<ClassroomPO>) hibernateTemplate.find(hql);
    }
}
