package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.controller.ItemController;
import com.mprtcz.webshop.model.itemmodel.FileBucket;
import com.mprtcz.webshop.model.itemmodel.Item;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by Azet on 2016-09-04.
 */
@Service("imageService")
public class ImageService {
    private static final String RESOURCES_LOCATION = ItemController.class.getResource("/").getPath().substring(1);
    private static final String UPLOAD_LOCATION = RESOURCES_LOCATION + "com/mprtcz/webshop/resources/itemspics/";

    public void saveImage(FileBucket fileBucket, Item item) throws IOException {
        if (!fileBucket.getFile().isEmpty()) {
            if(!Files.exists(Paths.get(UPLOAD_LOCATION))){
                File dir = new File(UPLOAD_LOCATION);
                dir.mkdirs();
            }
            String fileName = item.getId() + "." +(fileBucket.getFile().getOriginalFilename().split("\\.")[1]);
            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileName));
        }
    }

    public void deleteImage(Integer id){
        File folder = new File(UPLOAD_LOCATION);
        File[] listOfFiles = folder.listFiles();
        System.out.println(Arrays.toString(listOfFiles));

        if(listOfFiles!= null && listOfFiles.length > 0) {
            for (File file1 : listOfFiles) {
                if (file1.isFile()) {
                    String name = id+".";
                    System.out.println("Name: " +name);
                    if (file1.getName().contains(name)) {
                        System.out.println("CONTAINS!");
                        File file = new File(file1.getPath());
                        System.out.println("FILEPATH " + file.getAbsolutePath());
                        file1.delete();
                        break;
                    }
                }
            }
        }
    }
}
