package com.example.myapplication.Model;

public class XuatXe {
    private String id;
    private String tenNhan;
    private String tenXe;
    private String sdt;
    private String diaChi;
    private int tienPhi;
    private String ngayXuat;
    private byte[] image;
    private String category;

    public XuatXe(String id, String tenNhan, String tenXe, String sdt, String diaChi, int tienPhi, String ngayXuat, byte[] image, String category) {
        this.id = id;
        this.tenNhan = tenNhan;
        this.tenXe = tenXe;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.tienPhi = tienPhi;
        this.ngayXuat = ngayXuat;
        this.image = image;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenNhan() {
        return tenNhan;
    }

    public void setTenNhan(String tenNhan) {
        this.tenNhan = tenNhan;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTienPhi() {
        return tienPhi;
    }

    public void setTienPhi(int tienPhi) {
        this.tienPhi = tienPhi;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
