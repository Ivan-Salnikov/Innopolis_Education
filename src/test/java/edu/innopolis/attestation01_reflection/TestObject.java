package edu.innopolis.attestation01_reflection;


import java.util.ArrayList;
import java.util.Map;

public class TestObject {
    private Integer objectInteger;
    private ArrayList<String> objectArrayList;
    private Map <String, Integer> objectMap;
    private String someString;
    private long primitiveLong;
    private byte primitiveByte;
    private double primitiveDouble;
    private int primitiveInt;
    private boolean primitiveBoolean;
    private float primitiveFloat;
    private char primitiveChar;
    private short primitiveShort;

    public Integer getObjectInteger() {
        return objectInteger;
    }

    public ArrayList<String> getObjectArrayList() {
        return objectArrayList;
    }

    public Map<String, Integer> getObjectMap() {
        return objectMap;
    }

    public String getSomeString() {
        return someString;
    }

    public long getPrimitiveLong() {
        return primitiveLong;
    }

    public byte getPrimitiveByte() {
        return primitiveByte;
    }

    public double getPrimitiveDouble() {
        return primitiveDouble;
    }

    public int getPrimitiveInt() {
        return primitiveInt;
    }

    public boolean getPrimitiveBoolean() {
        return primitiveBoolean;
    }

    public float getPrimitiveFloat() {
        return primitiveFloat;
    }

    public char getPrimitiveChar() {
        return primitiveChar;
    }

    public short getPrimitiveShort() {
        return primitiveShort;
    }

    public TestObject(Integer objectInteger, ArrayList<String> objectArrayList, Map<String, Integer> objectMap,
                      String someString, long primitiveLong, byte primitiveByte, double primitiveDouble,
                      int primitiveInt, boolean primitiveBoolean, float primitiveFloat, char primitiveChar,
                      short primitiveShort) {
        this.objectInteger = objectInteger;
        this.objectArrayList = objectArrayList;
        this.objectMap = objectMap;
        this.someString = someString;
        this.primitiveLong = primitiveLong;
        this.primitiveByte = primitiveByte;
        this.primitiveDouble = primitiveDouble;
        this.primitiveInt = primitiveInt;
        this.primitiveBoolean = primitiveBoolean;
        this.primitiveFloat = primitiveFloat;
        this.primitiveChar = primitiveChar;
        this.primitiveShort = primitiveShort;
    }


    @Override
    public String toString() {
        return "TestObject{" +
                "objectInteger=" + objectInteger +
                ", objectArrayList=" + objectArrayList +
                ", objectMap=" + objectMap +
                ", someString='" + someString + '\'' +
                ", primitiveLong=" + primitiveLong +
                ", primitiveByte=" + primitiveByte +
                ", primitiveDouble=" + primitiveDouble +
                ", primitiveInt=" + primitiveInt +
                ", primitiveBoolean=" + primitiveBoolean +
                ", primitiveFloat=" + primitiveFloat +
                ", primitiveChar=" + primitiveChar +
                ", primitiveShort=" + primitiveShort +
                '}';
    }
}
