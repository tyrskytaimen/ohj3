package fi.tuni.prog3.round8.jsoncountries;

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CountryData {

    public static List<Country> readFromJsons(String areaFile, String populationFile, String gdpFile) throws FileNotFoundException {
        List<Country> countries = new ArrayList<>();
        countries = readAreaFile(areaFile, countries);
        countries = readPopulationFile(populationFile, countries);
        countries = readGdpFile(gdpFile, countries);

        return countries;
    }

    public static List<Country> readAreaFile(String areaFile, List<Country> countries){

        JsonObject jsonObject;

        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(areaFile));
            jsonObject = jsonElement.getAsJsonObject();

            JsonArray record = jsonObject.getAsJsonObject("Root")
                    .getAsJsonObject("data")
                    .getAsJsonArray("record");

            Iterator<JsonElement> it = record.iterator();

            while (it.hasNext()) {

                JsonElement next = it.next().getAsJsonObject().get("field");
                JsonArray nextArray = next.getAsJsonArray();

                String name = nextArray.get(0).getAsJsonObject().get("value").getAsString();
                String area = nextArray.get(2).getAsJsonObject().get("value").getAsString();


                boolean exists = false;
                for(Country x : countries){
                    if(name.equals(x.getName())){
                        exists = true;
                        x.setArea(Double.parseDouble(area));
                    }
                }
                if(!exists){
                    Country x = new Country(name);
                    x.setArea(Double.parseDouble(area));
                    countries.add(x);
                }

            }

        } catch (FileNotFoundException e) {

        }

        return countries;
    }

    public static List<Country> readPopulationFile(String populationFile, List<Country> lista){
        List<Country> newCountries = lista;

        JsonObject jsonObject = new JsonObject();

        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(populationFile));
            jsonObject = jsonElement.getAsJsonObject();

            JsonArray record = jsonObject.getAsJsonObject("Root")
                    .getAsJsonObject("data")
                    .getAsJsonArray("record");

            Iterator<JsonElement> it = record.iterator();

            while (it.hasNext()) {

                JsonElement next = it.next().getAsJsonObject().get("field");
                JsonArray nextArray = next.getAsJsonArray();

                String name = nextArray.get(0).getAsJsonObject().get("value").getAsString();
                String pop = nextArray.get(2).getAsJsonObject().get("value").getAsString();


                boolean exists = false;
                for(Country x : newCountries){
                    if(name.equals(x.getName())){
                        exists = true;
                        x.setPopulation(Long.parseLong(pop));
                    }
                }
                if(!exists){
                    Country x = new Country(name);
                    x.setPopulation(Long.parseLong(pop));
                    newCountries.add(x);

                }

            }

        } catch (FileNotFoundException e) {

        }

        return newCountries;
    }

    public static List<Country> readGdpFile(String gdpFile, List<Country> lista){
        List<Country> newCountries = lista;

        JsonObject jsonObject = new JsonObject();

        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(gdpFile));
            jsonObject = jsonElement.getAsJsonObject();

            JsonArray record = jsonObject.getAsJsonObject("Root")
                    .getAsJsonObject("data")
                    .getAsJsonArray("record");

            Iterator<JsonElement> it = record.iterator();

            while (it.hasNext()) {

                JsonElement next = it.next().getAsJsonObject().get("field");
                JsonArray nextArray = next.getAsJsonArray();

                String name = nextArray.get(0).getAsJsonObject().get("value").getAsString();
                String gdp = nextArray.get(2).getAsJsonObject().get("value").getAsString();


                boolean exists = false;
                for(Country x : newCountries){
                    if(name.equals(x.getName())){
                        exists = true;
                        x.setGdp(Double.parseDouble(gdp));
                    }
                }
                if(!exists){
                    Country x = new Country(name);
                    x.setGdp(Double.parseDouble(gdp));
                    newCountries.add(x);

                }

            }

        } catch (FileNotFoundException e) {

        }

        return newCountries;
    }


    public static void writeToJson(List<Country> countries, String countryFile) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        Writer writer = new FileWriter(countryFile);

        gson.toJson(countries,writer);
        writer.close();

    }
}
