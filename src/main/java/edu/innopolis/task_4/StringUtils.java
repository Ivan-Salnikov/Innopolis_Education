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
        for (int i = 0; i < strToArray.length; i++) {
            if(stringSequencesMap.containsKey(strToArray[i])) {
                stringSequencesMap.put(strToArray[i], stringSequencesMap.get(strToArray[i])+1);
            } else {
                stringSequencesMap.put(strToArray[i], 1);
            }
        }
    }

    public String getMapToString () {
        return stringSequencesMap.toString();
    }


}
