package com.example.myapplication.Model;

public class Car{
    private String Id;
    private String Name;
    private String Category;
    private String DInput;
    private String Note;
    private byte[] Image;

    public Car(String id, String namePhi, Double pricePhi, String note) {
    }

    public Car(String id, String name, String category, String DInput, String note, byte[] image) {
        Id = id;
        Name = name;
        Category = category;
        this.DInput = DInput;
        Note = note;
        Image = image;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDInput() {
        return DInput;
    }

    public void setDInput(String DInput) {
        this.DInput = DInput;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }
}
