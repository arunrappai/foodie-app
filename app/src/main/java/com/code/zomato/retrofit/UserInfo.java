package com.code.zomato.retrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("ApprovarCount")
    @Expose
    private Integer approvarCount;
    @SerializedName("LocLatitude")
    @Expose
    private String locLatitude;
    @SerializedName("LocLongitude")
    @Expose
    private String locLongitude;
    @SerializedName("LocOfficeName")
    @Expose
    private String locOfficeName;
    @SerializedName("OTP")
    @Expose
    private String oTP;
    @SerializedName("geoLocOfficeAddress")
    @Expose
    private String geoLocOfficeAddress;
    @SerializedName("locCode")
    @Expose
    private String locCode;
    @SerializedName("locCount")
    @Expose
    private String locCount;
    @SerializedName("muhCode")
    @Expose
    private String muhCode;
    @SerializedName("muhESSP")
    @Expose
    private String muhESSP;
    @SerializedName("muhEmpCode")
    @Expose
    private String muhEmpCode;
    @SerializedName("muhHR")
    @Expose
    private String muhHR;
    @SerializedName("muhIsEmployee")
    @Expose
    private String muhIsEmployee;
    @SerializedName("muhMIS")
    @Expose
    private String muhMIS;
    @SerializedName("muhMessage")
    @Expose
    private String muhMessage;
    @SerializedName("muhMobile")
    @Expose
    private String muhMobile;
    @SerializedName("muhName")
    @Expose
    private String muhName;
    @SerializedName("muhPassword")
    @Expose
    private String muhPassword;
    @SerializedName("muhUserRole")
    @Expose
    private String muhUserRole;
    @SerializedName("myRegion")
    @Expose
    private int myRegion;

    public int getMyRegion() {
        return myRegion;
    }

    public void setMyRegion(int myRegion) {
        this.myRegion = myRegion;
    }

    public Integer getApprovarCount() {
        return approvarCount;
    }

    public void setApprovarCount(Integer approvarCount) {
        this.approvarCount = approvarCount;
    }

    public String getLocLatitude() {
        return locLatitude;
    }

    public void setLocLatitude(String locLatitude) {
        this.locLatitude = locLatitude;
    }

    public String getLocLongitude() {
        return locLongitude;
    }

    public void setLocLongitude(String locLongitude) {
        this.locLongitude = locLongitude;
    }

    public String getLocOfficeName() {
        return locOfficeName;
    }

    public void setLocOfficeName(String locOfficeName) {
        this.locOfficeName = locOfficeName;
    }

    public String getoTP() {
        return oTP;
    }

    public void setoTP(String oTP) {
        this.oTP = oTP;
    }

    public String getGeoLocOfficeAddress() {
        return geoLocOfficeAddress;
    }

    public void setGeoLocOfficeAddress(String geoLocOfficeAddress) {
        this.geoLocOfficeAddress = geoLocOfficeAddress;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }

    public String getLocCount() {
        return locCount;
    }

    public void setLocCount(String locCount) {
        this.locCount = locCount;
    }

    public String getMuhCode() {
        return muhCode;
    }

    public void setMuhCode(String muhCode) {
        this.muhCode = muhCode;
    }

    public String getMuhESSP() {
        return muhESSP;
    }

    public void setMuhESSP(String muhESSP) {
        this.muhESSP = muhESSP;
    }

    public String getMuhEmpCode() {
        return muhEmpCode;
    }

    public void setMuhEmpCode(String muhEmpCode) {
        this.muhEmpCode = muhEmpCode;
    }

    public String getMuhHR() {
        return muhHR;
    }

    public void setMuhHR(String muhHR) {
        this.muhHR = muhHR;
    }

    public String getMuhIsEmployee() {
        return muhIsEmployee;
    }

    public void setMuhIsEmployee(String muhIsEmployee) {
        this.muhIsEmployee = muhIsEmployee;
    }

    public String getMuhMIS() {
        return muhMIS;
    }

    public void setMuhMIS(String muhMIS) {
        this.muhMIS = muhMIS;
    }

    public String getMuhMessage() {
        return muhMessage;
    }

    public void setMuhMessage(String muhMessage) {
        this.muhMessage = muhMessage;
    }

    public String getMuhMobile() {
        return muhMobile;
    }

    public void setMuhMobile(String muhMobile) {
        this.muhMobile = muhMobile;
    }

    public String getMuhName() {
        return muhName;
    }

    public void setMuhName(String muhName) {
        this.muhName = muhName;
    }

    public String getMuhPassword() {
        return muhPassword;
    }

    public void setMuhPassword(String muhPassword) {
        this.muhPassword = muhPassword;
    }

    public String getMuhUserRole() {
        return muhUserRole;
    }

    public void setMuhUserRole(String muhUserRole) {
        this.muhUserRole = muhUserRole;
    }
}
