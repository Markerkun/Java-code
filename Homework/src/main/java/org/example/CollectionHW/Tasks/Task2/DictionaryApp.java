package org.example.CollectionHW.Tasks.Task2;

import java.util.*;

class WordEntry {
    String word;
    Set<String> translations = new HashSet<>();
    int lookupCount = 0;

    public WordEntry(String word, List<String> initialTranslations) {
        this.word = word;
        this.translations.addAll(initialTranslations);
    }
}

public class DictionaryApp {
    private static Map<String, WordEntry> dictionary = new HashMap<>();

    public static void main(String[] args) {
        // Початковий ввід даних
        addWord("cat", List.of("кіт", "кішка"));
        addWord("dog", List.of("собака", "пес"));
        addWord("code", List.of("код", "шифр"));

        // Відображення перекладу з підрахунком популярості
        getTranslations("cat");
        getTranslations("cat");
        getTranslations("dog");

        printTop10Popular(true);  // Топ популярних
        printTop10Popular(false); // Топ непопулярних
    }

    public static void addWord(String word, List<String> translations) {
        dictionary.put(word.toLowerCase(), new WordEntry(word, translations));
    }

    public static void getTranslations(String word) {
        WordEntry entry = dictionary.get(word.toLowerCase());
        if (entry != null) {
            entry.lookupCount++;
            System.out.println(word + " -> " + entry.translations);
        } else {
            System.out.println("Слово не знайдено!");
        }
    }

    public static void printTop10Popular(boolean mostPopular) {
        List<WordEntry> list = new ArrayList<>(dictionary.values());
        list.sort(Comparator.comparingInt((WordEntry w) -> w.lookupCount));
        if (mostPopular) Collections.reverse(list);

        System.out.println(mostPopular ? "\n--- Топ-10 популярних слів ---" : "\n--- Топ-10 непопулярних слів ---");
        list.stream().limit(10).forEach(w -> System.out.println(w.word + " (запитів: " + w.lookupCount + ")"));
    }
}