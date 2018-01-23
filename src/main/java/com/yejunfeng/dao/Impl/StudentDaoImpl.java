package com.yejunfeng.dao.Impl;

import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.dao.StudentDao;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private DividePageVO dividePage;

    public void addStudent(StudentPO studentPO) {
        hibernateTemplate.saveOrUpdate(studentPO);
    }

    public void deleteStudent(String studentId) {
        hibernateTemplate.delete(searchById(studentId));
    }

    public void updateStudent(StudentPO studentPO) {
        hibernateTemplate.update(studentPO);
    }

    public StudentPO searchById(String studentId) {
        return hibernateTemplate.get(StudentPO.class,studentId);
    }

    public List<StudentPO> searchByClassId(String classId) {
        String hql = "from StudentPO stu where stu.classesPO.classId=?";
        List<StudentPO> studentPOS = (List<StudentPO>) hibernateTemplate.find(hql,classId);
        return studentPOS;
    }

    @Override
    public List<StudentPO> searchByStuName(String stuName) {
        String hql = "from StudentPO stu where stu.studentName=?";
        return (List<StudentPO>) hibernateTemplate.find(hql,stuName);
    }

    public DividePageVO searchByDivide(int thisPage, int rowOfEachPage) {
        String hql = "select count(*) from StudentPO stu";
        int pageCount = Integer.parseInt(String.valueOf((Long) hibernateTemplate.find(hql).listIterator().next()));
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StudentPO.class);
        List<StudentPO> studentPOS = (List<StudentPO>) hibernateTemplate.findByCriteria(detachedCriteria,(thisPage-1)*rowOfEachPage,rowOfEachPage);

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
        dividePage.setStudentPOS(studentPOS);
//        System.out.println(pageCount);
//        System.out.println(dividePage.getLastPage());
        return dividePage;
    }

    public DividePageVO searchByDivide(int thisPage, int rowOfEachPage, String classId) {
        String hql = "from StudentPO stu where stu.classesPO.classId=:classId";
        Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("classId",classId);
        query.setFirstResult((thisPage-1)*rowOfEachPage);
        query.setMaxResults(rowOfEachPage);
        List<StudentPO> studentPOS = query.list();

        hql = "select count(*) from StudentPO stu where stu.classesPO.classId=?";
        int pageCount = Integer.parseInt(String.valueOf((Long) hibernateTemplate.find(hql,classId).listIterator().next()));

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
        dividePage.setStudentPOS(studentPOS);

        return dividePage;
    }
}
