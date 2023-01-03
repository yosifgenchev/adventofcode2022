package me.yosif;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CalorieCountingApp {

    public static void main(String[] args) {
        String input = args[0];
        System.out.println("Reading data from: " + input);

        List<String> lines = Util.readLinesOfFile(input);

        int elfWithMostCalories = 1;
        int currentElfNumber = 1;

        int maxCalories = 0;
        int currentElfCalories = 0;

//        for (String calorieLine : lines) {
//            if (calorieLine.isBlank()) {
//                currentElfCalories = 0;
//                currentElfNumber++;
//                continue;
//            }
//
//            currentElfCalories+= Integer.parseInt(calorieLine);
//
//            if (currentElfCalories > maxCalories) {
//                elfWithMostCalories = currentElfNumber;
//                maxCalories = currentElfCalories;
//            }
//        }

        List<Elf> elves = new ArrayList<>();

        for (String calorieLine : lines) {
            if (calorieLine.isBlank()) {
                elves.add(new Elf(currentElfNumber, currentElfCalories));
                currentElfCalories = 0;
                currentElfNumber++;
                continue;
            }

            currentElfCalories += Integer.parseInt(calorieLine);
        }

        Collections.sort(elves);
        Collections.reverse(elves);

        System.out.println(elves.subList(0, 3).stream().map(Elf::getCalories).reduce(Integer::sum).get());


//        System.out.printf("The elf with most calories:\nElf number: %s, Calories: %s%n", elfWithMostCalories, maxCalories);
    }

    static class Elf implements Comparable {
        private int number;
        private int calories;

        public Elf(int number, int calories) {
            this.number = number;
            this.calories = calories;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(this.calories, ((Elf) o).getCalories());
        }

        public int getCalories() {
            return calories;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Elf elf = (Elf) o;
            return number == elf.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }

        @Override
        public String toString() {
            return "Elf{" +
                    "number=" + number +
                    ", calories=" + calories +
                    '}';
        }
    }
}
