package com.charge.model;

import java.io.Serializable;
import java.util.Date;

public class Charge implements Serializable {
    private Long id;

    /**编码*/
    private String chargeNo;
    /**创建时间*/
    private Date createTime;
    /**修改时间*/
    private Date updateTime;
    /**充电点名称*/
    private String name;
    /**所属区域*/
    private String area;
    /**地址*/
    private String address;
    /**交流电已建数量*/
    private Integer acBuilded;
    /**交流电在建数量*/
    private Integer acBuilding;
    /**直流电已建数量*/
    private Integer dcBuilded;
    /**直流电在建数量*/
    private Integer dcBuilding;
    /**经度*/
    private Double longitude;
    /**纬度*/
    private Double latitude;
    /**开发时间*/
    private String beginTime;
    /**服务电话*/
    private String tel;
    /**使用标准*/
    private Integer standard;
    /**使用标准名称*/
    private String standardName;
    /**收费标准*/
    private String feeStandard;
    /**详情*/
    private String detail;
    /**建设单位*/
    private String depart;



    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getAcBuilded() {
        return acBuilded;
    }

    public void setAcBuilded(Integer acBuilded) {
        this.acBuilded = acBuilded;
    }

    public Integer getAcBuilding() {
        return acBuilding;
    }

    public void setAcBuilding(Integer acBuilding) {
        this.acBuilding = acBuilding;
    }

    public Integer getDcBuilded() {
        return dcBuilded;
    }

    public void setDcBuilded(Integer dcBuilded) {
        this.dcBuilded = dcBuilded;
    }

    public Integer getDcBuilding() {
        return dcBuilding;
    }

    public void setDcBuilding(Integer dcBuilding) {
        this.dcBuilding = dcBuilding;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName == null ? null : standardName.trim();
    }

    public String getFeeStandard() {
        return feeStandard;
    }

    public void setFeeStandard(String feeStandard) {
        this.feeStandard = feeStandard == null ? null : feeStandard.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart == null ? null : depart.trim();
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo == null ? null : chargeNo.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Charge other = (Charge) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getAcBuilded() == null ? other.getAcBuilded() == null : this.getAcBuilded().equals(other.getAcBuilded()))
            && (this.getAcBuilding() == null ? other.getAcBuilding() == null : this.getAcBuilding().equals(other.getAcBuilding()))
            && (this.getDcBuilded() == null ? other.getDcBuilded() == null : this.getDcBuilded().equals(other.getDcBuilded()))
            && (this.getDcBuilding() == null ? other.getDcBuilding() == null : this.getDcBuilding().equals(other.getDcBuilding()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getBeginTime() == null ? other.getBeginTime() == null : this.getBeginTime().equals(other.getBeginTime()))
            && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
            && (this.getStandard() == null ? other.getStandard() == null : this.getStandard().equals(other.getStandard()))
            && (this.getStandardName() == null ? other.getStandardName() == null : this.getStandardName().equals(other.getStandardName()))
            && (this.getFeeStandard() == null ? other.getFeeStandard() == null : this.getFeeStandard().equals(other.getFeeStandard()))
            && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
            && (this.getDepart() == null ? other.getDepart() == null : this.getDepart().equals(other.getDepart()))
            && (this.getChargeNo() == null ? other.getChargeNo() == null : this.getChargeNo().equals(other.getChargeNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getAcBuilded() == null) ? 0 : getAcBuilded().hashCode());
        result = prime * result + ((getAcBuilding() == null) ? 0 : getAcBuilding().hashCode());
        result = prime * result + ((getDcBuilded() == null) ? 0 : getDcBuilded().hashCode());
        result = prime * result + ((getDcBuilding() == null) ? 0 : getDcBuilding().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getBeginTime() == null) ? 0 : getBeginTime().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getStandard() == null) ? 0 : getStandard().hashCode());
        result = prime * result + ((getStandardName() == null) ? 0 : getStandardName().hashCode());
        result = prime * result + ((getFeeStandard() == null) ? 0 : getFeeStandard().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getDepart() == null) ? 0 : getDepart().hashCode());
        result = prime * result + ((getChargeNo() == null) ? 0 : getChargeNo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", name=").append(name);
        sb.append(", area=").append(area);
        sb.append(", address=").append(address);
        sb.append(", acBuilded=").append(acBuilded);
        sb.append(", acBuilding=").append(acBuilding);
        sb.append(", dcBuilded=").append(dcBuilded);
        sb.append(", dcBuilding=").append(dcBuilding);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", tel=").append(tel);
        sb.append(", standard=").append(standard);
        sb.append(", standardName=").append(standardName);
        sb.append(", feeStandard=").append(feeStandard);
        sb.append(", detail=").append(detail);
        sb.append(", depart=").append(depart);
        sb.append(", chargeNo=").append(chargeNo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}