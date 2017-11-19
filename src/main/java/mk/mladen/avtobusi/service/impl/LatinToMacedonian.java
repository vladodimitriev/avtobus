package mk.mladen.avtobusi.service.impl;

import com.ibm.icu.text.Transliterator;

public class LatinToMacedonian {

	private static LatinToMacedonian instance = null;

	public static LatinToMacedonian getInstance() {
		if (instance == null) {
			instance = new LatinToMacedonian();
		}
		return instance;
	}
	
	private LatinToMacedonian() {
	}

	private static final String rules = 
			"Skopje > Скопје;" + 
			"Bitola > Битола;" +
			"Negotino > Неготино;" +
			"Kavadarci > Кавадарци;" +
			"Ohrid > Охрид;" +
			"Veles > Велес;" +
			"Kochani > Кочани;" +
			"Prilep > Прилеп;" +
			"Strumica > Струмица;" +
			"Gevgelija > Гевгелија;" +
			"Struga > Струга;" +
			"Shtip > Штип;" +
			"Krushevo > Крушево;" +
			"Berovo > Берово;" +
			"Star Dojran > Стар Дојран;" +
			"St Dojran > Ст Дојран;" +
			"S Dojran > С Дојран;" +
			"Dojran > Дојран;" +
			"Nov Dojran > Нов Дојран;" +
			"N Dojran > Н Дојран;" +
			"Vinica > Виница;" +
			"Tetovo > Тетово;" +
			"Kumanovo > Куманово;" +
			"Gostivar > Гостивар;" +
			"Kichevo > Кичево;" +
			"Debar > Дебар;" +
			"Delchevo > Делчево;" +
			"Demir Hisar > Демир Хисар;" +
			"D Hisar > Д Хисар;" +
			"Valandovo > Валандово;" +
			"Pehchevo > Пехчево;" +
			"Probishtip > Пробиштип;" +
			"Kriva Palanka > Крива Паланка;" +
			"Kr Palanka > Кр Паланка;" +
			"K Palanka > К Паланка;" +
			"Palanka > Паланка;" +
			"Demir Kapija > Демир Капија;" +
			"D Kapija > Д Капија;" +
			"Resen > Ресен;" +
			"Bogdanci > Богданци;" +
			"Sveti Nikole > Свети Николе;" +
			"Sv Nikole > Св Николе;" +
			"S Nikole > С Николе;" +
			"Makedonski Brod > Македонски Брод;" +
			"Mak Brod > Мак Брод;" +
			"M Brod > М Брод;" +
			"Brod > Брод;" +
			"Makedonska Kamenica > Македонска Каменица;" +
			"Mak Kamenica > Мак Каменица;" +
			"M Kamenica > М Каменица;" +
			"Kamenica > Каменица;" +
			"Radovish > Радовиш;" +
			"Kratovo > Кратово;" +
			"Gradsko > Градско;" +
			"Zletovo > Злетово;" +
			"Pretor > Претор;" +
			"Vevchani > Вевчани;" +
			"Peshtani > Пештани;" +
			"Rosoman > Росоман;" +
			"Mavrovo > Маврово;";

	private static Transliterator latinToMkd = Transliterator.createFromRules("temp", rules, Transliterator.FORWARD);

	public String translate(String mkd) {
		try {
			return latinToMkd.transliterate(mkd);
		}catch(Exception e) {
			return "";
		}
	}
}
