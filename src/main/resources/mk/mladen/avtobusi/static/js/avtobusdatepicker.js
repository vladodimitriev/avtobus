function initJQDatepicker(inputId, countryIsoCode, errorMessage, errorMessage1, errorMessage2, errorMessage3) {
	var localizedArray = $.datepicker.regional[countryIsoCode];
	localizedArray['dateFormat'] = 'dd/mm/yy';
	localizedArray['numberOfMonths'] = 2;
	localizedArray['showCurrentAtPos'] = 0;

	var departureDate = $("#departureDate");
	var departurePlace = $("#departurePlace");
	var destinationPlace = $("#destinationPlace");
	departureDate.datepicker(localizedArray);	
	departureDate.on('change', function() {
	    var ds = departureDate.val();

        var str1 = ds.substr(0,2);
        var str2 = ds.substr(3,2);
        var str3 = ds.substr(6);
        var str = str2 + "/" + str1 + "/" + str3;

        var _myDate = new Date(str);
        var newDate = $.now();

        if (_myDate.getTime() < newDate) {
        	departureDate.get(0).setCustomValidity(errorMessage);
        	departureDate.datepicker("destroy");
        } else {
        	departureDate.get(0).setCustomValidity("");
        }
    });	
	
	departureDate.on('click', function() {
		departureDate.datepicker(localizedArray);
		
		departureDate.get(0).setCustomValidity("");
		destinationPlace.get(0).setCustomValidity("");
		departurePlace.get(0).setCustomValidity("");
	});
	
	departurePlace.on('invalid', function() {
		departurePlace.get(0).setCustomValidity(errorMessage2);
	});
	
	destinationPlace.on('invalid', function() {
		destinationPlace.get(0).setCustomValidity(errorMessage3);
	});
	
	departurePlace.on('click', function() {je
		departureDate.get(0).setCustomValidity("");
		destinationPlace.get(0).setCustomValidity("");
		departurePlace.get(0).setCustomValidity("");
	});
	
	destinationPlace.on('click', function() {
		departureDate.get(0).setCustomValidity("");
		destinationPlace.get(0).setCustomValidity("");
		departurePlace.get(0).setCustomValidity("");
	});
	
	
};

function initCalendar(localizedArray){
	 localizedArray['changeMonth']= true;
	 localizedArray['changeYear']= true;
};
