package com.yejunfeng.dao.Impl;

import com.yejunfeng.PO.AttendanceRecordPO;
import com.yejunfeng.PO.ClassesPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.VO.StudentRecordVO;
import com.yejunfeng.dao.ClassesDao;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassesDaoImpl implements ClassesDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private DividePageVO dividePage;

    public void addClasses(ClassesPO classesPO) {
        hibernateTemplate.save(classesPO);
    }

    public void deleteClasses(String classId) {
        hibernateTemplate.delete(searchById(classId));
    }

    public void updateClasses(ClassesPO classesPO) {
        hibernateTemplate.update(classesPO);
    }

    public ClassesPO searchById(String classId) {
        return hibernateTemplate.get(ClassesPO.class,classId);
    }

    public List<ClassesPO> searchAll() {
        String hql = "from ClassesPO cla";
        return (List<ClassesPO>) hibernateTemplate.find(hql);
    }

    @Override
    public DividePageVO searchForDivide(int thisPage, int rowOfEachPage) {
        String hql = "select count(*) from ClassesPO cla";
        int pageCount = Integer.parseInt(String.valueOf((Long) hibernateTemplate.find(hql).listIterator().next()));
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ClassesPO.class);
        List<ClassesPO> classesPOS = (List<ClassesPO>) hibernateTemplate.findByCriteria(detachedCriteria,(thisPage-1)*rowOfEachPage,rowOfEachPage);

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
        dividePage.setClassesPOS(classesPOS);
        System.out.println(dividePage.getPageCount());
        return dividePage;
    }
}
