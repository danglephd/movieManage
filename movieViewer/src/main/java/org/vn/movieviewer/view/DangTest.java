package org.vn.movieviewer.view;

import com.google.gson.JsonElement;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.vn.movieviewer.config.JsonUtils;
import org.vn.movieviewer.controller.APIRequest;

public class DangTest {

    String html
            = "<html><body>"
            + "<img src='"
            + "https://images-na.ssl-images-amazon.com/images/M/MV5BMzMxMTFlMDYtNjIyNS00YzQ4LWJlMDAtNGQwY2RlZGJiMmM1XkEyXkFqcGdeQXVyNzEyMTA5MTU@._V1_SY1000_SX700_AL_.jpg"
            + "' > ";

    public DangTest() {
        String[] cols = {"COL", "COL", "COL"};
        String[][] data = {
            {"Hello", "Hello", "Hello"},
            {"Hello", "Hello", "Hello"},
            {"Hello", "Hello", "Hello"}
        };
        DefaultTableModel model = new DefaultTableModel(data, cols);
        JTable table = new JTable(model) {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent) c;
                    jc.setToolTipText(html + "<br/>"
                            + getValueAt(row, column).toString()
                            + ":  row, col (" + row + ", " + column + ")"
                            + "</body></html>");
                }
                return c;
            }
        };

        JFrame frame = new JFrame();
        frame.add(new JScrollPane(table));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                new DangTest();

                try {
//                    String strtotran = "I gave my coffee guy a scented candle this year";
////                    //1. Cách dùng gói Jafregle để truy cập vào thông qua link http://translate.google.com/translate_a/
////                    Jafregle jafregle = new Jafregle(Language.ENGLISH, Language.VIETNAM);
////                    String result = jafregle.translate(strtotran);//Hey! Shut off the hose!");
////                    System.out.println(result);
//
//                    //2. Cách dùng service của google translate, mà ngặt nỗi ko có API Key
////                    Translate t = new Translate.Builder(
////                            com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport(),
////                            GsonFactory.getDefaultInstance(), null)
////                            //Need to update this to your App-Name
////                            .setApplicationName("Stackoverflow-Example")
////                            .build();
////                    Translate.Translations.List list = t.new Translations().list(
////                            Arrays.asList(
////                                    //Pass in list of strings to be translated
////                                    "Hello World",
////                                    "How to use Google Translate from Java"),
////                            //Target language   
////                            "ES");
////                    //Set your API-Key from https://console.developers.google.com/
////                    list.setKey("you-need-your-own-api-key");
////                    TranslationsListResponse response = list.execute();
////                    for (TranslationsResource tr : response.getTranslations()) {
////                        System.out.println(tr.getTranslatedText());
////                    }
//                    //3. Gọi URL dùng phương thức GET vào thẳng trang google translate
//                    //=> lỗi 403
//                    //4. Dùng GoogleTranslator
//                    GoogleTranslator translator = new GoogleTranslator();
//                    translator.setSrcLang(LANGUAGE.ENGLISH);
//                    translator.setDestLang(LANGUAGE.VIETNAMESE);
//                    String data = translator.sendGet(strtotran);
////                    String data = translator.translate(strtotran);
//                    System.out.println(data);
//                    String iMDB_APIKey = "faeb0cf9";
//                    IMDbAPI imdapi = new IMDbAPI();
////                    MovieIMDbDto movieIMDbDto = imdapi.searchByTitle("Peter Rabbit", 2018, "short");
//                    IMDbResponseSearchDto searchIMDbDto = imdapi.search("Peter", IMDbAPI.TYPE_MOVIE, 0, 0);
                    
                    getLstPopulationCountry();

//                    System.out.println(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private static void getLstPopulationCountry(){
        try {
            APIRequest request = new APIRequest();
            String worldPopulationUrl = "http://api.population.io/1.0/";
            request.setsURLRequest(worldPopulationUrl);
            Map<String, Object> params = new HashMap<>();
            params.put("countries", "");
            params.put("?format=", "json");
            JsonElement jsonElement = request.callAPIbyGET(params);
            Map<String, ArrayList<String>> jsonObject = JsonUtils.jSon2Object(jsonElement, Map.class);
//            JsonArray elementArray = jsonElement.getAsString();
            List<String> countries = jsonObject.get("countries");
            for (Iterator<String> iterator = countries.iterator(); iterator.hasNext();) {
                String next = iterator.next();
                System.out.println("-"+ next);
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
