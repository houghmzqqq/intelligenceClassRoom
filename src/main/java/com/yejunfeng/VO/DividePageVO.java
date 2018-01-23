package com.yejunfeng.VO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yejunfeng.PO.ClassesPO;
import com.yejunfeng.PO.DevicePO;
import com.yejunfeng.PO.StudentPO;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.Field;

@Component
public class DividePageVO implements Serializable 
{
	private int rowOfEachPage = 5;//每一页行数
	private int maxPage;//总页数
	private int pageCount;
	private int thisPage = 1;//当前页号
	private int prePage;
	private int nextPage;
	private int lastPage;//最后一页

	private List<StudentPO> studentPOS = new ArrayList<StudentPO>();
	private List<DevicePO> devicePOS = new ArrayList<>();
	private List<StudentRecordVO> studentRecordVOS = new ArrayList<>();
	private List<ClassesPO> classesPOS = new ArrayList<>();

	public List<ClassesPO> getClassesPOS() {
		return classesPOS;
	}
	public void setClassesPOS(List<ClassesPO> classesPOS) {
		this.classesPOS = classesPOS;
	}
	public List<StudentRecordVO> getStudentRecordVOS() {
		return studentRecordVOS;
	}
	public void setStudentRecordVOS(List<StudentRecordVO> studentRecordVOS) {
		this.studentRecordVOS = studentRecordVOS;
	}
	public List<DevicePO> getDevicePOS() {
		return devicePOS;
	}
	public void setDevicePOS(List<DevicePO> devicePOS) {
		this.devicePOS = devicePOS;
	}
	public List<StudentPO> getStudentPOS() {
		return studentPOS;
	}
	public void setStudentPOS(List<StudentPO> studentPOS) {
		this.studentPOS = studentPOS;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getRowOfEachPage() {
		return rowOfEachPage;
	}
	public void setRowOfEachPage(int rowOfEachPage) {
		this.rowOfEachPage = rowOfEachPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getThisPage() {
		return thisPage;
	}
	public void setThisPage(int thisPage) {
		this.thisPage = thisPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	
}
