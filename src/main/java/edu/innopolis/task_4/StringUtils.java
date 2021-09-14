package edu.innopolis.task_4;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {
    private String inputString;
    private Map<Character, Integer> stringSequencesMap;

    public StringUtils(String inputString, Map<Character, Integer> stringSequencesMap) {
        this.inputString = inputString;
        this.stringSequencesMap = stringSequencesMap;
        fillStringSequencesMap ();
    }

    public StringUtils(String inputString) {
        this(inputString, new HashMap<>());
    }

    public Map<Character, Integer> getStringSequencesMap () {
        return stringSequencesMap;
    }

    public void fillStringSequencesMap () {
        char[] strToArray = inputString.toCharArray();
        for (Character character : strToArray) {
            if(stringSequencesMap.containsKey(character)) {
                stringSequencesMap.put(character, stringSequencesMap.get(character)+1);
            } else {
                stringSequencesMap.put(character, 1);
            }
        }
    }

    public String getMapToString () {
        return stringSequencesMap.toString();
    }


}
