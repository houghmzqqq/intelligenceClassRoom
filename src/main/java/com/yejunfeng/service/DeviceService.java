package com.yejunfeng.service;

import com.yejunfeng.PO.DevicePO;
import com.yejunfeng.PO.DeviceTypePO;
import com.yejunfeng.VO.DividePageVO;
import com.yejunfeng.exception.DeviceFindException;

import java.util.List;

public interface DeviceService {
    /**
     * 分页查找设备
     * @param thisPage 当前页
     * @param rowOfEachPage 每一页的行数
     * @param typeId 设备类型
     * @return 分页信息
     */
    public DividePageVO getDividePageView(int thisPage, int rowOfEachPage, String typeId) throws DeviceFindException;

    /**
     * 查找所有设备类型
     * @return 设备类型集合
     */
    public List<DeviceTypePO> getAllDevType();

    /**
     * 查找全部设备
     * @return 设备列表
     */
    public List<DevicePO> getAllDev();

    /**
     * 添加设备
     * @param devicePO 设备实例
     */
    public void addDevice(DevicePO devicePO);

    public void updateDev(DevicePO devicePO);

    public DevicePO getByDevId(String devId);

    /**
     * 打开设备
     * @param devId 设备id
     */
    public void doOpen(String devId);

    /**
     * 关闭设备
     * @param devId 设备id
     */
    public void doClose(String devId);
}
