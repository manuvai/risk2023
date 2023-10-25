package res.model.loader;


import res.model.Continent;
import res.model.Territoire;
import res.model.exceptions.ImpossibleToExtractLinesException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class MainLoader {

    public static final String SEMI_COLON_SPLIT_STRING = ";";
    public static final String COMA_SPLIT_STRING = ",";

    public static void main(String[] args) {
        MainLoader loader = new MainLoader();

        System.out.println(loader.getContinentList());
        System.out.println(loader.getTerritoireList());
    }

    public List<Continent> getContinentList() {
        Map<Integer, Continent> continentMap = getContinents();
        Map<Integer, Territoire> territoireMap = getTerritoires();

        return linkToContinents(continentMap, territoireMap);
    }

    public List<Territoire> getTerritoireList() {
        return getContinentList().parallelStream()
                .map(Continent::getTerritories)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public Map<Integer, Continent> getContinents() {
        List<List<String>> continentsLines = extractTileString(extractLines("assets/continents.csv"));

        Map<Integer, Continent> continentMap = new HashMap<>();
        for (int i = 1; i < continentsLines.size(); i++) {
            List<String> line = continentsLines.get(i);
            Continent continent = new Continent(line.get(1), Integer.parseInt(line.get(2)));

            continentMap.put(Integer.parseInt(line.get(0)), continent);
        }

        return continentMap;
    }

    public List<Continent> linkToContinents(Map<Integer, Continent> continentMap, Map<Integer, Territoire> territoireMap) {

        List<List<String>> linkLines = extractTileString(extractLines("assets/terr_cont.csv"));
        for (int i = 1; i < linkLines.size(); i++) {
            List<String> line = linkLines.get(i);

            int idContinent = Integer.parseInt(line.get(0));
            int idTerritoire = Integer.parseInt(line.get(1));

            continentMap.get(idContinent)
                    .addTerritoire(territoireMap.get(idTerritoire));
        }

        return new ArrayList<>(continentMap.values());
    }

    public Map<Integer, Territoire> getTerritoires() {
        Map<Integer, Territoire> territoireMap = extractTerritoiresFromCsv();

        List<List<String>> linkLines = extractTileString(extractLines("assets/terr_voisins.csv"));

        for (int i = 1; i < linkLines.size(); i++) {
            List<String> line = linkLines.get(i);

            int idTerritoire = Integer.parseInt(line.get(0));
            List<Integer> listIdVoisins = Arrays.stream(line.get(1).split(COMA_SPLIT_STRING))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            List<Territoire> voisins = new ArrayList<>();
            
            for (Integer idVoisin : listIdVoisins) {
                voisins.add(territoireMap.get(idVoisin));
            }
                    
            territoireMap.get(idTerritoire).setVoisins(voisins);
        }

        return territoireMap;
    }

    private Map<Integer, Territoire> extractTerritoiresFromCsv() {
        List<List<String>> territoiresLines = extractTileString(extractLines("assets/territoires.csv"));

        Map<Integer, Territoire> territoireMap = new HashMap<>();
        for (int i = 1; i < territoiresLines.size(); i++) {
            List<String> line = territoiresLines.get(i);
            Territoire territoire = new Territoire(line.get(1));

            territoireMap.put(Integer.parseInt(line.get(0)), territoire);
        }
        return territoireMap;
    }

    private List<String> extractLines(String file) {
        InputStream inputStream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(file);
        List<String> mapStrings;

        try {
            mapStrings = readFromInputStream(inputStream);
        } catch (IOException e) {
            throw new ImpossibleToExtractLinesException();
        }
        return mapStrings;
    }

    /**
     * Extrait une liste de lignes de liste de caractères
     *
     * @param mapStrings
     * @return
     */
    private List<List<String>> extractTileString(List<String> mapStrings) {
        return extractTileString(mapStrings, SEMI_COLON_SPLIT_STRING);
    }

    /**
     * Extrait une liste de lignes de liste de caractères séparés par un caractère passé en paramètre
     *
     * @param mapStrings
     * @param splitString
     * @return
     */
    private List<List<String>> extractTileString(List<String> mapStrings, String splitString) {
        return mapStrings.stream()
                .map(line -> Arrays.asList(line.split(splitString)))
                .collect(Collectors.toList());
    }


    /**
     * Permet de lire le contenu d'un fichier contenu dans le InputStream
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private List<String> readFromInputStream(InputStream inputStream)
            throws IOException {

        List<String> responseLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream)
        )) {
            String line;
            while ((line = br.readLine()) != null) {
                responseLines.add(line);
            }
        }
        return responseLines;
    }
}
