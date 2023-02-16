package fi.tuni.prog3;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Search {
    public static void main(String args[]) throws IOException {
        String fileName = args[0];

        SevenZFile archiveFile = new SevenZFile(new File(fileName));
        SevenZArchiveEntry entry;
        try {
            // Go through all entries
            while ((entry = archiveFile.getNextEntry()) != null) {
                // Maybe filter by name. Name can contain a path.
                String name = entry.getName();
                ArrayList<String> lista = new ArrayList<>();

                if (entry.isDirectory() || !name.contains(".txt")) {
                    //System.out.println(String.format("Found directory entry %s", name));
                } else {
                    // If this is a file, we read the file content into a
                    // ByteArrayOutputStream ...
//                    System.out.println(String.format("Unpacking %s ...", name));
                    System.out.println(name);
                    lista.add(name);

                    ByteArrayOutputStream contentBytes = new ByteArrayOutputStream();

                    // ... using a small buffer byte array.
                    byte[] buffer = new byte[2048];
                    int bytesRead;
                    while ((bytesRead = archiveFile.read(buffer)) != -1) {
                        contentBytes.write(buffer, 0, bytesRead);
                    }

                    // Assuming the content is a UTF-8 text file we can interpret the
                    // bytes as a string.
                    String content = contentBytes.toString("UTF-8");
                    String[] rivit = content.split("\\n");
                    int num = 1;
                    for (String x : rivit) {
                        lista.add(num + ": " + x);
                        num++;
                    }

                    String haettuSana = args[1]; //korvaa ARGS[1]
                    haettuSana = haettuSana.toLowerCase();

                    for (String x : lista) {
                        if (x.toLowerCase().contains(haettuSana)) {  //korvaa args[1]
                            String[] split = x.split(" ");
                            int x1 = 0;
                            while (x1 < split.length) {
                                if (split[x1].toLowerCase().equals(haettuSana)) { //korvaa args[1]
                                    split[x1] = split[x1].toUpperCase();
                                } else if (split[x1].toLowerCase().contains(haettuSana)) { //korvaa args[1]
                                    int pos = split[x1].toLowerCase().indexOf(haettuSana); //korvaa args[1]
                                    String temp = split[x1].substring(pos, pos + haettuSana.length()).toUpperCase();
                                    split[x1] = split[x1].substring(0, pos) + temp + split[x1].substring(pos + haettuSana.length(), split[x1].length());
                                }
                                x1++;
                            }
                            x = String.join(" ", split);
                            System.out.println(x);
                        }

                    }

                    System.out.println("");
                }
            }
        } finally {
            archiveFile.close();
        }
    }
}

