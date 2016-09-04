package com.mprtcz.webshop.model.itemmodel;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Azet on 2016-09-04.
 */
public class FileBucket {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }


    @Override
    public String toString() {
        return "FileBucket{" +
                "file=" + file +
                '}';
    }
}
