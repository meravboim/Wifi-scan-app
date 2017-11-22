import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileCsv fe = new FileCsv();
		fe.readForCsv("C:\\Users\\yitzhak\\Desktop\\Simple_1");
		    
		FileKml fl = new FileKml();
        fl.readFromCsvToKml("hadar.csv");

	}

}
