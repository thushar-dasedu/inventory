package com.InventoryManagement.entity;


import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "file_data")
@Builder
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private  int id;
private String name;
private  String type;
@Column(name = "file_path")
private String filePath;

    public FileData(String name, String type, String filePath) {
        this.name=name;
        this.type=type;
        this.filePath=filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileData() {
    }



    public FileData(int id, String name, String type, String filePath) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
