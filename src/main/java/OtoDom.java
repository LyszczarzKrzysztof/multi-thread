import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.*;
import java.net.URL;
import java.util.*;

public class OtoDom {

    public static void main(String[] args) throws Exception {

        URL otodom = new URL("https://www.otodom.pl/sprzedaz/mieszkanie/podkarpackie/");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(otodom.openStream()));

        String inputLine;

        StringBuilder stringBuilder = new StringBuilder(); // mozna jeszcze StringBuffer, StringBuilder jest wolniejszy
        // ale "odporny" na wielowątkowość


        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append(System.lineSeparator()); // dodaje znak nowej linii
        }
        in.close();

        Set<String> links = new TreeSet<>();
        //List<String> links = new ArrayList<>();
        String content = stringBuilder.toString();

        for (int i = 0; i < content.length(); i++) {
            i = content.indexOf("https://www.otodom.pl/oferta/", i);
            if (i < 0)
                break;
            String[] split = content.substring(i).split(".html");
            String link = split[0];
            links.add(link);
            readWebsite(link,i+".html");
        }
    }

    public static void readWebsite(String link, String fileName) throws IOException {
        URL otodom = new URL(link);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(otodom.openStream()));

        String inputLine;

        StringBuilder stringBuilder = new StringBuilder(); // mozna jeszcze StringBuffer, StringBuilder jest wolniejszy
        // ale "odporny" na wielowątkowość


        while ((inputLine = in.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append(System.lineSeparator()); // dodaje znak nowej linii
        }
        in.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false));
        bw.write(stringBuilder.toString());
        bw.close();
    }
}
