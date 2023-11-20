package adressbuchWithUI;
import java.util.LinkedList;

public class data {
	//List inizialisierung um die Adressen zu speichern	
	LinkedList<Datastructure> index = new LinkedList<>();
	
	int createAdress() {
		Datastructure tempObj = new Datastructure();
		index.addLast(tempObj);
		return index.indexOf(tempObj);
		}
	String getAdressData(int AdressIndex, int AdressDataIndex) {
		return index.get(AdressIndex).getData(AdressDataIndex);
	}
	
	void modifyAdress(int AdressIndex, int DataIndex, String Value) {
		index.get(AdressIndex).editData(DataIndex, Value);
	}
	
	void deleteAdress(int AdressIndex) {
		index.remove(AdressIndex);
	}
	int getLength() {
		return index.size();
	}
	int search(int dataIndex, String Value) {
		for (int i = 0; i < index.size(); i++) {
			String tmpVal = index.get(i).getData(dataIndex);
			if (tmpVal.equals(Value)) {
				return i;
			}
		}
		return -1;
	}
		
}


