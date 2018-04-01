package mk.mladen.avtobusi.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ibm.icu.text.Transliterator;

public class MacedonianToLatin {
	
	@Test	
	public void macedonianToLatinTest() {
		String rules=
				"A > A;" +
				"a > a;" +
		        "Б > B;" +
		        "б > b;" +
		        "В > V;" +
		        "в > v;" +
		        "Г > G;" +
		        "г > g;" +
		        "Д > D;" +
		        "д > d;" +
		        "Ѓ > GI;" +
		        "ѓ > gi;" +
		        "Е > E;" +
		        "е > e;" +
		        "Ж > Z;" +
		        "ж > z;" +
		        "З > Z;" +
		        "з > z;" +
		        "Ѕ > Z;" +
		        "ѕ > z;" +
		        "И > I;" +
		        "и > i;" +
		        "Ј > J;" +
		        "ј > j;" +
		        "К > K;" +
		        "к > k;" +
		        "Л > L;" +
		        "л > l;" +
		        "Љ > L;" +
		        "љ > l;" +
		        "М > M;" +
		        "м > m;" +
		        "Н > N;" +
		        "н > n;" +
		        "Њ > N;" +
		        "њ > n;" +
		        "О > O;" +
		        "о > o;" +
		        "П > P;" +
		        "п > p;" +
		        "Р > R;" +
		        "р > r;" +
		        "С > S;" +
		        "с > s;" +
		        "Т > T;" +
		        "т > t;" +
		        "Ќ > K;" +
		        "ќ > k;" +
		        "У > U;" +
		        "у > u;" +
 		        "Ф > F;" +
		        "ф > f;" +
		        "Х > H;" +
		        "х > h;" +
		        "Ц > C;" +
		        "ц > c;" +
		        "Ч > CH;" +
		        "ч > ch;" +
		        "Џ > G;" +
		        "џ > g;" +
		        "Ш > SH;" +
		        "ш > sh"; 
		
		String mkString = "Джокович";
		Transliterator mkdToLatin = Transliterator.createFromRules("temp", rules, Transliterator.FORWARD);
        String result = mkdToLatin.transliterate(mkString);
        String result2 = mkdToLatin.transform(mkString);
        
        assertNotNull(result);
        assertNotNull(result2);
        
        mkString = "ПЕЛАГОНИЈА ТРАНС А.Д - ПРИЛЕП";
        result = mkdToLatin.transliterate(mkString);
        assertNotNull(result);
        
        mkString = "ЃОКО ГЛИГОР ШОНТЕВСКИ-БЕРОВО";
        result = mkdToLatin.transliterate(mkString);
        assertNotNull(result);
        
        mkString = "МАРЈАН ТУРС ДООЕЛ-ПРОБИШТИП";
        result = mkdToLatin.transliterate(mkString);
        assertNotNull(result);
        
        mkString = "АНДОН КОМПАНИ ДОО - ПЕХЧЕВО";
        result = mkdToLatin.transliterate(mkString);
        assertNotNull(result);
	}

}
