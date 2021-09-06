package edu.innopolis.task_2;

public class Homework02 {
    public static void main(String[] args) {
        final String TEXT_SEPARATOR = "-------";

        System.out.println(TEXT_SEPARATOR);
        System.out.println("");
        System.out.println("Creating ArrayList with String elements and initial array size 10");
        ArrayList<String> stringArrayList = new ArrayList<>(10);
        stringArrayList.add("Some String 1");
        stringArrayList.add("Some String 2");
        stringArrayList.add("Some String 3");
        stringArrayList.add("Some String 4");
        stringArrayList.add("Some String 5");
        stringArrayList.add("Some String 6");
        System.out.println("ArrayList with String elements: " + stringArrayList);

        System.out.println(TEXT_SEPARATOR);
        System.out.println("");

        System.out.println("Creating ArrayList with Integer elements and default initial array size(5)");
        ArrayList<Integer> intArrayList = new ArrayList<>();
        intArrayList.add(1);
        intArrayList.add(2);
        intArrayList.add(3);
        intArrayList.add(4);
        intArrayList.add(5);
        intArrayList.add(6);
        intArrayList.add(7);

        System.out.println("ArrayList with Integer elements: " + intArrayList);

        System.out.println(TEXT_SEPARATOR);
        System.out.println("");
        System.out.println("Getting elements by index: " +
                "\nelement of stringArrayList with 5 index: " + stringArrayList.get(5) +
                "\nelement of intArrayList with 3 index: " + intArrayList.get(3));

        System.out.println("Getting elements with out of bound index: ");
        System.out.println( intArrayList.get(7));

        System.out.println(TEXT_SEPARATOR);
        System.out.println("");
        System.out.println("Creating Iterator for String ArrayList. " +
                "\nPrinting all elements of ArrayList by Iterator:");

        Iterator <String> stringIterator = stringArrayList.iterator();
        while (stringIterator.hasNext()) {
            System.out.println(stringIterator.next());
        }

        System.out.println(TEXT_SEPARATOR);
        System.out.println("");
        System.out.println("Creating Iterator for Integer ArrayList. " +
                "\nPrinting all elements of ArrayList by Iterator:");

        Iterator <Integer> integerIterator = intArrayList.iterator();
        while (integerIterator.hasNext()) {
            System.out.println(integerIterator.next());
        }

    }
}
