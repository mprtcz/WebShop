package com.mprtcz.webshop.service.itemservice;

import com.mprtcz.webshop.dao.itemdao.ItemDao;
import com.mprtcz.webshop.dao.itemdao.ItemDaoImpl;
import com.mprtcz.webshop.model.itemmodel.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Azet on 2016-09-30.
 */
public class ItemServiceImplTest {

    @InjectMocks
    @Spy
    ItemDao itemDao = new ItemDaoImpl();

    @Mock
    SessionFactory sessionFactory;

    @Mock
    Session session;

    @InjectMocks
    private ItemService itemService = new ItemServiceImpl(itemDao, new ImageServiceImpl());

    @BeforeMethod
    private void init() {
        MockitoAnnotations.initMocks(this);
        sessionFactory = Mockito.mock(SessionFactory.class);
        session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
    }


    @Test
    public void getRandomItemsTest_positive() {
        int itemsQuantity = 5;

        //Mockito.when(sessionFactory.openSession()).thenReturn(session);
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
        List<Item> items = itemService.getRandomItems(itemsQuantity);
        assertEquals(items.size(), itemsQuantity);
    }

    @Test
    public void getRandomItemsTest_negative() {
        int itemsQuantity = 50;
        List<Item> items = itemService.getRandomItems(itemsQuantity);

        assertEquals(items.size(), itemsQuantity);
    }
}
