package com.yejunfeng.dao;

import com.yejunfeng.PO.DevicePO;
import com.yejunfeng.PO.DeviceTypePO;

import java.util.List;

public interface DeviceTypeDao {
    /**
     * 添加设备类型
     * @param devTypePO 设备类型实例
     */
    public void saveDevType(DeviceTypePO devTypePO);

    /**
     * 删除设备类型
     * @param typeId 设备类型id
     */
    public void deleteDevType(String typeId);

    /**
     * 更新设备类型
     * @param devTypePO 设备类型实例
     */
    public void updateDevType(DeviceTypePO devTypePO);

    /**
     * 查找单个设备类型
     * @param typeId 设备类型id
     * @return 设备累心实例
     */
    public DeviceTypePO searchById(String typeId);

    /**
     * 查找全部设备类型
     * @return 设备类型实例的list集合
     */
    public List<DeviceTypePO> searchAll();
}
