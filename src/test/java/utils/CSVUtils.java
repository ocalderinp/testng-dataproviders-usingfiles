package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

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
