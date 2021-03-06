package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.model.itemmodel.FileBucket;
import com.mprtcz.webshop.model.itemmodel.Item;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Azet on 2016-09-04.
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {
    private static final String PROJECT_LOCATION = "C:/Users/Azet/Documents/SpringTutorial/WebShop";
    private static final String RESOURCES_LOCATION = ImageServiceImpl.class.getResource("/").getPath().substring(1);
    private static final String UPLOAD_LOCATION = PROJECT_LOCATION + "/IMAGES/";
    private static final String TARGET_UPLOAD_LOCATION = RESOURCES_LOCATION + "com/mprtcz/webshop/resources/itemspics/";
    private static final String DEFAULT_IMAGE_LOCATION = "C:/Users/Azet/Documents/SpringTutorial/WebShop/IMAGES/null.png";

    @Override
    public void saveUploadedImage(FileBucket fileBucket, Item item) throws IOException {
        if (!fileBucket.getFile().isEmpty()) {
            if (!Files.exists(Paths.get(UPLOAD_LOCATION))) {
                File dir = new File(UPLOAD_LOCATION);
                dir.mkdirs();
            }
            String fileName = item.getId() + "." + (fileBucket.getFile().getOriginalFilename().split("\\.")[1]);
            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileName));
        }
    }

    @Override
    public boolean saveLinkedImage(String link, Item item) {
        URL url = null;
        if (item.getId() == null) {
            item.setId(1);
        }
        String writePath = UPLOAD_LOCATION + String.valueOf(item.getId()) + ".png";
        if (!Files.exists(Paths.get(UPLOAD_LOCATION))) {
            File dir = new File(UPLOAD_LOCATION);
            dir.mkdirs();
        }
        try {

            url = new URL(link);
            BufferedImage c = ImageIO.read(url);
            File file = new File(writePath);

            file.createNewFile();
            ImageIO.write(c, "png", file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteImage(Integer id) {
        File folder = new File(UPLOAD_LOCATION);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null && listOfFiles.length > 0) {
            for (File file1 : listOfFiles) {
                if (file1.isFile()) {
                    String name = id + ".";
                    if (file1.getName().contains(name)) {
                        File file = new File(file1.getPath());
                        file1.delete();
                        break;
                    }
                }
            }
        }
    }

    @Override
    public File getImageByItemId(Integer id) {
        return getFileByName(String.valueOf(id));
    }

    @Override
    public String getImagePathByItemId(Integer id) {
        return getImageByItemId(id).getAbsolutePath();
    }


    private File getFileByName(String name) {

        File folder = new File(UPLOAD_LOCATION);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null && listOfFiles.length > 0) {
            for (File file1 : listOfFiles) {
                if (file1.isFile()) {
                    String fileName = file1.getName().split("\\.")[0];
                    if (fileName.equals(name)) {
                        return new File(file1.getPath());
                    }
                }
            }
        }
        String filePath = UPLOAD_LOCATION + "null.png";

        return new File(filePath);
    }

}
