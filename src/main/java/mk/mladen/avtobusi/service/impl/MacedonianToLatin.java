package mk.mladen.avtobusi.service.impl;

import com.ibm.icu.text.Transliterator;

public class MacedonianToLatin {

	private static MacedonianToLatin instance = null;

	public static MacedonianToLatin getInstance() {
		if (instance == null) {
			instance = new MacedonianToLatin();
		}
		return instance;
	}
	
	private MacedonianToLatin() {
	}

//	private static final String rules = "A > A;" + "a > a;" + "Б > B;" + "б > b;" + "В > V;" + "в > v;" + "Г > G;"
//			+ "г > g;" + "Д > D;" + "д > d;" + "Ѓ > GI;" + "ѓ > gi;" + "Е > E;" + "е > e;" + "Ж > Z;" + "ж > z;"
//			+ "З > Z;" + "з > z;" + "Ѕ > Z;" + "ѕ > z;" + "И > I;" + "и > i;" + "Ј > J;" + "ј > j;" + "К > K;"
//			+ "к > k;" + "Л > L;" + "л > l;" + "Љ > L;" + "љ > l;" + "М > M;" + "м > m;" + "Н > N;" + "н > n;"
//			+ "Њ > N;" + "њ > n;" + "О > O;" + "о > o;" + "П > P;" + "п > p;" + "Р > R;" + "р > r;" + "С > S;"
//			+ "с > s;" + "Т > T;" + "т > t;" + "Ќ > K;" + "ќ > k;" + "У > U;" + "у > u;" + "Ф > F;" + "ф > f;"
//			+ "Х > H;" + "х > h;" + "Ц > C;" + "ц > c;" + "Ч > CH;" + "ч > ch;" + "Џ > G;" + "џ > g;" + "Ш > SH;"
//			+ "ш > sh";

	private static final String rules = 
			"Скопје > Skopje;" + 
			"Битола > Bitola;" +
			"Неготино > Negotino;" +
			"Кавадарци > Kavadarci;" +
			"Охрид > Ohrid;" +
			"Велес > Veles;" +
			"Кочани > Kochani;" +
			"Прилеп > Prilep;" +
			"Струмица > Strumica;" +
			"Гевгелија > Gevgelija;" +
			"Струга > Struga;" +
			"Штип > Shtip;" +
			"Крушево > Krushevo;" +
			"Берово > Berovo;" +
			"Стар Дојран > Star Dojran;" +
			"Ст Дојран > St Dojran;" +
			"С Дојран > S Dojran;" +
			"Дојран > Dojran;" +
			"Нов Дојран > Nov Dojran;" +
			"Н Дојран > N Dojran;" +
			"Виница > Vinica;" +
			"Тетово > Tetovo;" +
			"Куманово > Kumanovo;" +
			"Гостивар > Gostivar;" +
			"Кичево > Kichevo;" +
			"Дебар > Debar;" +
			"Делчево > Delchevo;" +
			"Демир Хисар > Demir Hisar;" +
			"Д Хисар > D Hisar;" +
			"Валандово > Valandovo;" +
			"Пехчево > Pehchevo;" +
			"Пробиштип > Probishtip;" +
			"Крива Паланка > Kriva Palanka;" +
			"Кр Паланка > Kr Palanka;" +
			"К Паланка > K Palanka;" +
			"Паланка > Palanka;" +
			"Демир Капија > Demir Kapija;" +
			"Д Капија > D Kapija;" +
			"Ресен > Resen;" +
			"Богданци > Bogdanci;" +
			"Свети Николе > Sveti Nikole;" +
			"Св Николе > Sv Nikole;" +
			"С Николе > S Nikole;" +
			"Македонски Брод > Makedonski Brod;" +
			"Мак Брод > Mak Brod;" +
			"М Брод > M Brod;" +
			"Брод > Brod;" +
			"Македонска Каменица > Makedonska Kamenica;" +
			"Мак Каменица > Mak Kamenica;" +
			"М Каменица > M Kamenica;" +
			"Каменица > Kamenica;" +
			"Радовиш > Radovish;" +
			"Кратово > Kratovo;" +
			"Градско > Gradsko;" +
			"Злетово > Zletovo;" +
			"Претор > Pretor;" +
			"Вевчани > Vevchani;" +
			"Пештани > Peshtani;" +
			"Росоман > Rosoman;" +
			"Маврово > Mavrovo;" + 
			"A > A;" + "a > a;" + "Б > B;" + "б > b;" + "В > V;" + "в > v;" + "Г > G;"
			+ "г > g;" + "Д > D;" + "д > d;" + "Ѓ > GI;" + "ѓ > gi;" + "Е > E;" + "е > e;" + "Ж > Z;" + "ж > z;"
			+ "З > Z;" + "з > z;" + "Ѕ > Z;" + "ѕ > z;" + "И > I;" + "и > i;" + "Ј > J;" + "ј > j;" + "К > K;"
			+ "к > k;" + "Л > L;" + "л > l;" + "Љ > L;" + "љ > l;" + "М > M;" + "м > m;" + "Н > N;" + "н > n;"
			+ "Њ > N;" + "њ > n;" + "О > O;" + "о > o;" + "П > P;" + "п > p;" + "Р > R;" + "р > r;" + "С > S;"
			+ "с > s;" + "Т > T;" + "т > t;" + "Ќ > K;" + "ќ > k;" + "У > U;" + "у > u;" + "Ф > F;" + "ф > f;"
			+ "Х > H;" + "х > h;" + "Ц > C;" + "ц > c;" + "Ч > CH;" + "ч > ch;" + "Џ > G;" + "џ > g;" 
			+ "Ш > SH;" + "ш > sh;";
	
	private static Transliterator mkdToLatin = Transliterator.createFromRules("temp", rules, Transliterator.FORWARD);

	public String translate(String mkd) {
		try {
			return mkdToLatin.transliterate(mkd);
		}catch(Exception e) {
			return "";
		}
	}
}
