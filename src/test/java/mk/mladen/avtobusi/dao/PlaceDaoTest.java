package mk.mladen.avtobusi.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import mk.mladen.avtobusi.ApplicationTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.Assert.*;

@DatabaseSetup("PlaceSampleData.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationTestConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class PlaceDaoTest {

    @Autowired
    private PlaceDao placeDao;
    
    @Test
    public void getAllPlacesCyrillicNamesByName() {
    	String name = " Gra";
    	List<String> result = placeDao.getAllPlacesCyrillicNamesByName("MK", name);
    	assertNotNull(result);
        assertFalse(result.isEmpty());
        for(String s : result) {
        	System.out.println(s);
        }
        assertEquals(1, result.size());
    }

    @Test
    public void getPlacesWithCapitalLetterTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("EN", "S");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(4, result.size());
    }

    @Test
    public void getPlacesWithLowerLetterTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("EN", "s");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(4, result.size());
    }

    @Test
    public void getPlacesWith3LettersTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("EN", "Str");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void getPlacesWith2LettersTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("EN", "St");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
    }

    @Test
    public void getPlacesWith4LettersTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("EN", "Stru");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void getPlacesWithKLetterTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("EN", "k");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(6, result.size());
    }

    @Test
    public void getPlacesWithKrLettersTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("EN", "Kr");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }

    @Test
    public void getPlacesWithEmptyInputTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("EN", "");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(10, result.size());
        assertEquals("Skopje", result.get(0));
    }

    @Test
    public void getPlacesWithMiddleInputTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("EN", "gotin");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getDojranTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("EN", "Dojran");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
