package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    /**
     * este metodo es para obtener los datos del csv y almacenarlos en una lista de User
     * User es una clase que se utiliza como como modelo para los datos
     * @param br: BufferedReader que almacena el contenido del csv
     * @return lista de User
     * @throws IOException
     */
    public static List<User> getUsersData(BufferedReader br) throws IOException {
        List<User> usersData = new ArrayList<User>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(br);
        for(CSVRecord record : records){
            User user = new User();
            user.setUsuario(record.get(0));
            user.setPassword(record.get(1));
            usersData.add(user);
        }
        return usersData;
    }
}
