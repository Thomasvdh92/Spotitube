package services;

import org.json.JSONObject;

import java.util.Random;

public class test {

    public static void main(String[] args) {
        Random r = new Random();
        String query = "INSERT INTO Track (title, performer, duration, album, playcount, publicationDate, description, offlineAvailable)" +
                " VALUES ";
        for (int i = 0; i < 30; i++) {
            query += "(";
            query += generateRandomString(r);
            query += generateRandomString(r);
            query += "'" + r.nextInt(4000) + "',";
            query += generateRandomString(r);
            query += "'" + r.nextInt(4000) + "',";
            query += "'" + "12-12-1992" + "',";
            query += generateRandomString(r);
            query += "'" + r.nextInt(2) + "'),";
        }
        query = query.substring(0, query.length() - 1);
        query += ";";
        System.out.println(query);
    }

    private static String generateRandomString(Random r) {
        String s = "'";
        for (int j = 0; j < 20; j++) {
            char c = (char)(r.nextInt(26) + 'a');
            s += c;
        }
        s += "',";
        return s;
    }
}
