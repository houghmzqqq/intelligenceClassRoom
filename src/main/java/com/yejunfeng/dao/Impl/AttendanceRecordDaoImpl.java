package com.yejunfeng.dao.Impl;

import com.yejunfeng.PO.AttendanceRecordPO;
import com.yejunfeng.PO.StudentPO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.VO.StudentRecordVO;
import com.yejunfeng.dao.AttendanceRecordDao;
import org.dozer.DozerBeanMapper;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AttendanceRecordDaoImpl implements AttendanceRecordDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private DividePageVO dividePage;
    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public void addAttenRecord(AttendanceRecordPO arPO) {
        hibernateTemplate.save(arPO);
    }

    @Override
    public void deleteAttenRecord(int arId) {
        hibernateTemplate.delete(searchById(arId));
    }

    @Override
    public void updateAttenRecord(AttendanceRecordPO arPO) {
        hibernateTemplate.update(arPO);
    }

    @Override
    public AttendanceRecordPO searchById(int arId) {
        return hibernateTemplate.get(AttendanceRecordPO.class,arId);
    }

    @Override
    public DividePageVO searchByStuId(int thisPage, int rowOfEachPage, String stuName) {
        String hql = "from AttendanceRecordPO ar where ar.studentPO.studentName=:studentName";
        Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("studentName",stuName);
        query.setFirstResult((thisPage-1)*rowOfEachPage);
        query.setMaxResults(rowOfEachPage);
        List<AttendanceRecordPO> attendanceRecordPOS = query.list();

        List<StudentRecordVO> studentRecordVOS = new ArrayList<>();
        for(AttendanceRecordPO arPO : attendanceRecordPOS){
            StudentRecordVO studentRecordVO = mapper.map(arPO,StudentRecordVO.class);
            studentRecordVO.setDate(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(arPO.getAttenDate()).toString());
            if(arPO.getStatus()==1){
                studentRecordVO.setStatus("正常");
            }else if(arPO.getStatus()==2){
                studentRecordVO.setStatus("迟到");
            }else if(arPO.getStatus()==3){
                studentRecordVO.setStatus("缺勤");
            }
            studentRecordVOS.add(studentRecordVO);
        }

        hql = "select count(*) from AttendanceRecordPO ar where ar.studentPO.studentName=?";
        int pageCount = Integer.parseInt(String.valueOf((Long) hibernateTemplate.find(hql,stuName).listIterator().next()));

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
        dividePage.setStudentRecordVOS(studentRecordVOS);

        return dividePage;
    }

    @Override
    public DividePageVO searchByStuId(int thisPage, int rowOfEachPage) {
        String hql = "select count(*) from AttendanceRecordPO ar";
        int pageCount = Integer.parseInt(String.valueOf((Long) hibernateTemplate.find(hql).listIterator().next()));
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AttendanceRecordPO.class);
        List<AttendanceRecordPO> attendanceRecordPOS = (List<AttendanceRecordPO>) hibernateTemplate.findByCriteria(detachedCriteria,(thisPage-1)*rowOfEachPage,rowOfEachPage);

        List<StudentRecordVO> studentRecordVOS = new ArrayList<>();
        for(AttendanceRecordPO arPO :attendanceRecordPOS){
            StudentRecordVO studentRecordVO = mapper.map(arPO,StudentRecordVO.class);
//            new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(arPO.getAttenDate()).toString();
            studentRecordVO.setDate(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(arPO.getAttenDate()).toString());
            if(arPO.getStatus()==1){
                studentRecordVO.setStatus("正常");
            }else if(arPO.getStatus()==2){
                studentRecordVO.setStatus("迟到");
            }else if(arPO.getStatus()==3){
                studentRecordVO.setStatus("缺勤");
            }
            studentRecordVOS.add(studentRecordVO);
        }

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
        dividePage.setStudentRecordVOS(studentRecordVOS);
//        System.out.println(pageCount);
//        System.out.println(dividePage.getLastPage());
        return dividePage;
    }

    @Override
    public DividePageVO searchByClassName(int thisPage, int rowOfEachPage) {

        String hql = "";

        return null;
    }

    @Override
    public DividePageVO searchByClassName(int thisPage, int rowOfEachPage, String className) {
        return null;
    }
}
