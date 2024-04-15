package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonGenerator {
    private Gson gson;
    private FileWriter writer;
    private List<Cep> cepList;
    CepRecord cepRecord;

    public JsonGenerator() throws IOException {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        this.writer = new FileWriter("ceps.json");
        this.cepList = new ArrayList<>();
    }

    public void createItem(String json) {
        // creates record object. prints api's returned json if successful
        cepRecord = gson.fromJson(json, CepRecord.class);
        if (!"true".equals(cepRecord.erro())){
            System.out.println(json);
        }
        Cep cep = new Cep(cepRecord);
        cepList.add(cep);
    }

    public void saveItens() throws IOException {
        writer.write(gson.toJson(cepList));
        writer.close();
    }
}
