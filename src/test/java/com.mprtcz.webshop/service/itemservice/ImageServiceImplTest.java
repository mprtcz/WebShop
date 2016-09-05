package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.model.itemmodel.Item;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Azet on 2016-09-04.
 */
public class ImageServiceImplTest {
    private static final String[] urls =
            {"http://previews.123rf.com/images/Krisdog/Krisdog1302/krisdog130200016/17682869-Illustration-of-a-chess-business-concept-A-business-team-on-one-side-of-the-chess-board-playing-agai-Stock-Vector.jpg",
            "https://scontent.xx.fbcdn.net/v/t1.0-1/s200x200/13892225_1056616641060219_6845637507512979586_n.jpg?oh=4987969a00c7c6ef50c7dbed2795a573&oe=5882F2F6",
            "http://i.imgur.com/cjQgbPm.jpg",
            "http://image.shutterstock.com/z/stock-photo-chess-one-pawn-against-all-6098371.jpg",
            "http://cdn.sci-news.com/images/enlarge3/image_4125e-Honey.jpg",
            "https://pbs.twimg.com/media/CrhYZiQWgAAsSQq.jpg"};


    @Test
    public void saveLinkedImageTest(){
        ImageService imageService = new ImageServiceImpl();
        for(String url : urls){
            System.out.println("For url :" +url);
            assertTrue(imageService.saveLinkedImage(url, new Item()));
        }
    }


    public static void main(String[] args) {
        String RESOURCES_LOCATION = ImageServiceImpl.class.getResource("/").getPath().substring(1);
        String UPLOAD_LOCATION = RESOURCES_LOCATION + "com/mprtcz/webshop/resources/itemspics/";
        System.out.println(System.getProperty("catalina.base"));
    }
}
