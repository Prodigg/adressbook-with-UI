package adressbuchWithUI;

public class Datastructure {
	String name = "";
	String Strasse = "";
	String Ort = "";
	String Postleitzahl = "";
	String Telefonnummer = "";
	
	void editData(int Dataindex, String value) {
		switch (Dataindex) {
			case 0:
				name = value;
			return;
			case 1: 
				Strasse = value;
			return;
			case 2: 
				Ort = value;
				return;
			case 3:
				Postleitzahl = value;
				return;
			case 4:
				Telefonnummer = value;
				return;
		}
	}
	
	String getData(int Dataindex) {
		switch (Dataindex) {
		case 0:
			return name;
		case 1: 
			return Strasse;
		case 2: 
			return Ort;
		case 3:
			return Postleitzahl;
		case 4:
			return Telefonnummer;
	}
		return "NULL";
	}
	
}

