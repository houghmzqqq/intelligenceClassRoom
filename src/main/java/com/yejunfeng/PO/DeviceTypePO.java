package com.yejunfeng.PO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device_type")
public class DeviceTypePO {

    @Id @Column(name="dev_type_id")
    private String devTypeId;
    @Column(name="dev_type_name")
    private String devTypeName;

    public DeviceTypePO() {
    }

    public DeviceTypePO(String devTypeId) {
        this.devTypeId = devTypeId;
    }

    public DeviceTypePO(String devTypeId, String devTypeName) {
        this.devTypeId = devTypeId;
        this.devTypeName = devTypeName;
    }

    public String getDevTypeId() {
        return devTypeId;
    }

    public void setDevTypeId(String devTypeId) {
        this.devTypeId = devTypeId;
    }

    public String getDevTypeName() {
        return devTypeName;
    }

    public void setDevTypeName(String devTypeName) {
        this.devTypeName = devTypeName;
    }
}
