package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.model.itemmodel.FileBucket;
import com.mprtcz.webshop.model.itemmodel.Item;

import java.io.File;
import java.io.IOException;

/**
 * Created by Azet on 2016-09-04.
 */
public interface ImageService {
    void saveUploadedImage(FileBucket fileBucket, Item item) throws IOException;

    boolean saveLinkedImage(String link, Item item);

    void deleteImage(Integer id);

    File getImagesByItemId(Integer id);
}
