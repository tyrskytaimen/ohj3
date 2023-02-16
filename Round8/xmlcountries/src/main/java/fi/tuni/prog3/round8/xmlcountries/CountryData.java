package fi.tuni.prog3.round8.xmlcountries;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountryData {
    public CountryData() {}

    public static List<Country> readFromXmls(String areaFile, String populationFile, String gdpFile) throws IOException, JDOMException {
        List<Country> countries = new ArrayList<>();

        SAXBuilder sax = new SAXBuilder();
        Document areaDoc = sax.build(new File(areaFile));
        Document populationDoc = sax.build(new File(populationFile));
        Document gdpDoc = sax.build(new File(gdpFile));


        ArrayList<String> name = new ArrayList<>();
        ArrayList<Double> area = new ArrayList<>();
        ArrayList<Long> population = new ArrayList<>();
        ArrayList<Double> gdp = new ArrayList<>();

        Element areaRoot = areaDoc.getRootElement();
        Element populationRoot = populationDoc.getRootElement();
        Element gdpRoot = gdpDoc.getRootElement();

        List<Element> areaRecords = areaRoot.getChild("data").getChildren();
        for(Element record : areaRecords) {
            List<Element> fields = record.getChildren();
            name.add(fields.get(0).getText());
            area.add(Double.parseDouble(fields.get(2).getText()));
        }

        List<Element> gdpRecords = gdpRoot.getChild("data").getChildren();
        for(Element record : gdpRecords) {
            List<Element> fields = record.getChildren();
            gdp.add(Double.parseDouble(fields.get(2).getText()));
        }

        List<Element> populationRecords = populationRoot.getChild("data").getChildren();
        for(Element record : populationRecords) {
            List<Element> fields = record.getChildren();
            population.add(Long.parseLong(fields.get(2).getText()));
        }

        for(int i = 0; i < name.size(); i++) {
            Country c = new Country(name.get(i), area.get(i), population.get(i), gdp.get(i));
            countries.add(c);
        }
        return countries;
    }

    public static void writeToXml(List<Country> countries, String countryFile) throws IOException, JDOMException {
        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(new File(countryFile));

        doc.setRootElement(new Element("countries"));
        for (Country c : countries) {
            Element country = new Element("country");
            country.addContent(new Element("name").setText(c.getName()));
            country.addContent(new Element("area").setText("" + c.getArea()));
            country.addContent(new Element("population").setText("" + c.getPopulation()));
            country.addContent(new Element("gdp").setText("" + c.getGdp()));
            doc.getRootElement().addContent(country);
        }

        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        FileOutputStream output = new FileOutputStream(countryFile);
        xmlOutputter.output(doc, output);
    }
}
