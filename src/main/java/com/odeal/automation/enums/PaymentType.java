package com.odeal.automation.enums;

public enum PaymentType {
	
	KREDIKARTI_TEMASLI("Kredi Kartı - Temaslı"),KREDIKARTI_TEMASSIZ("Kredi Kartı - Temassız"),
	NAKIT_KAGIT("Nakit İle - Kağıt"),NAKIT_BOZUK("Nakit İle - Bozuk");
	
	private String result;

	PaymentType(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
