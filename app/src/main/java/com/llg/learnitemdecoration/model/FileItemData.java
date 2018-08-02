package com.llg.learnitemdecoration.model;

import java.util.Date;

/**
 * create by lilugen on 2018/8/2
 */
public class FileItemData {
    private String fileName;//文件名
    private String filePath;//文件路径
    private String coverPath;//封面路径
    private boolean selected;//是否被选中
    private String createDate;//被创建时间
    private String fileEx;//文件名后缀



    public FileItemData(String fileName,String filePath){
        this.fileName = fileName;
        this.filePath = filePath;
    }



    public FileItemData(String fileName, String filePath, String createDate) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.createDate = createDate;
    }

    public FileItemData(String fileName, String filePath, String coverPath, String createDate) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.coverPath = coverPath;
        this.createDate = createDate;
    }


    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFileEx() {
        return fileEx;
    }
}
