package com.yejunfeng.dao;

import com.yejunfeng.PO.DevicePO;
import com.yejunfeng.VO.DividePageVO;

import java.util.List;

public interface DeviceDao {
    /**
     * 添加设备
     * @param devicePO 设备实例
     */
    public void addDevice(DevicePO devicePO);

    /**
     * 删除设备
     * @param devId 设备id
     */
    public void deleteDevice(String devId);

    /**
     * 更新设备
     * @param devicePO 设备实例
     */
    public void updateDevice(DevicePO devicePO);

    /**
     * 查找所有设备
     * @return 设备列表
     */
    public List<DevicePO> searchAllDev();

    /**
     * 查找单个设备
     * @param devId 设备id
     * @return 设备实例
     */
    public DevicePO searchById(String devId);

    /**
     * 查找某个类型的设备
     * @param typeId 设备类型
     * @return 设备实例的list集合
     */
    public List<DevicePO> searchByTypeId(String typeId);

    /**
     * 分页查询设备
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页的行数
     * @return 分页信息
     */
    public DividePageVO searchByDivide(int thisPage, int rowOfEachPage);

    /**
     * 分页查询设备
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页的行数
     * @param typeId 设备id
     * @return 分页信息
     */
    public DividePageVO searchByDivide(int thisPage,int rowOfEachPage, String typeId);
}
