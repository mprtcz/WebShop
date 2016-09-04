package com.mprtcz.webshop.validators;

import com.mprtcz.webshop.model.itemmodel.FileBucket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Azet on 2016-09-04.
 */
@Component
public class FileValidator implements Validator {
    private static final String[] EXTENSIONS = {".jpg", ".png", ".tiff"};

    public boolean supports(Class<?> clazz) {
        return FileBucket.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        FileBucket file = (FileBucket) obj;

        System.out.println(", isEmpty=" + file.getFile().isEmpty());

        if (file.getFile() != null) {
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "File is missing");
            }
///*
            if(!containsExtension(file.getFile().getOriginalFilename())){
                System.out.println("wrong.extension");
                errors.rejectValue("file", "Wrong extension!");
            }
            //*/
        }
    }

    private boolean containsExtension(String name) {
        for (String s : EXTENSIONS) {
            System.out.println("FILENAME: " + name + " EXTENSION: " + s);
            if (name.toLowerCase().contains(s)) {
                return true;
            }
        }
        return false;
    }
}
