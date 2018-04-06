package mk.mladen.avtobusi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import mk.mladen.avtobusi.ApplicationTestConfig;

@Ignore
@DatabaseSetup("PlaceSampleData.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationTestConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class PlaceDaoTest {

    @Autowired
    private PlaceDao placeDao;
    
    @Ignore
    @Test
    public void getAllPlacesCyrillicNamesByName() {
    	String name = " Gra";
    	List<String> result = placeDao.getAllPlacesCyrillicNamesByName("mk", name);
    	assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
    
    @Ignore
    @Test
    public void getPlacesWithCapitalLetterTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("en", "S");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(4, result.size());
    }
    
    @Ignore
    @Test
    public void getPlacesWithLowerLetterTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("en", "s");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(4, result.size());
    }
    
    @Ignore
    @Test
    public void getPlacesWith3LettersTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("en", "Str");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }
    
    @Ignore
    @Test
    public void getPlacesWith2LettersTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("en", "St");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
    }
    
    @Ignore
    @Test
    public void getPlacesWith4LettersTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("en", "Stru");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }
    
    @Ignore
    @Test
    public void getPlacesWithKLetterTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("en", "k");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(6, result.size());
    }
    
    @Ignore
    @Test
    public void getPlacesWithKrLettersTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("en", "Kr");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
    }
    
    @Ignore
    @Test
    public void getPlacesWithEmptyInputTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("en", "");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(10, result.size());
        assertEquals("Skopje", result.get(0));
    }
    
    @Ignore
    @Test
    public void getPlacesWithMiddleInputTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("en", "gotin");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
    @Ignore
    @Test
    public void getDojranTest() {
        List<String> result = placeDao.getAllPlacesNamesByLanguageAndName("en", "Dojran");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
}
