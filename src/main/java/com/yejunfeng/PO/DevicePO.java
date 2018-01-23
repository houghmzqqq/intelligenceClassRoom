package com.yejunfeng.PO;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="device")
public class DevicePO {
    @Id @Column(name="dev_id")
    private String deviceId;
    @Column(name="dev_name")
    private String deviceName;
    @Column(name="dev_byname")
    private String deviceByname;
    @Column(name="create_date")
    private Timestamp createDate;
    private String zigbee;
    private int status;

    @ManyToOne(targetEntity = DeviceTypePO.class)
    @JoinColumn(name = "dev_type_id")
    private DeviceTypePO deviceTypePO;

    public DevicePO() {
    }

    public DevicePO(String deviceId, String deviceName, String deviceByname, Timestamp createDate, String zigbee, DeviceTypePO deviceTypePO) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceByname = deviceByname;
        this.createDate = createDate;
        this.zigbee = zigbee;
        this.deviceTypePO = deviceTypePO;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceByname() {
        return deviceByname;
    }

    public void setDeviceByname(String deviceByname) {
        this.deviceByname = deviceByname;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getZigbee() {
        return zigbee;
    }

    public void setZigbee(String zigbee) {
        this.zigbee = zigbee;
    }

    public DeviceTypePO getDeviceTypePO() {
        return deviceTypePO;
    }

    public void setDeviceTypePO(DeviceTypePO deviceTypePO) {
        this.deviceTypePO = deviceTypePO;
    }
}
