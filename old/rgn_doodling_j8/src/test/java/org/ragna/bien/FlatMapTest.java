package org.ragna.bien;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * Created by ragnarokkrr on 1/14/16.
 */
public class FlatMapTest {

    @Test
    public void flatMap() {
        List<Developer> team = new ArrayList<>();
        Developer polyglot = new Developer("esoteric");
        polyglot.addLanguage("clojure");
        polyglot.addLanguage("scala");
        polyglot.addLanguage("groovy");
        polyglot.addLanguage("go");

        Developer busy = new Developer("pragmatic");
        busy.addLanguage("java");
        busy.addLanguage("javascript");

        team.add(polyglot);
        team.add(busy);


        final List<String> teamLanguages = team.stream()
                .map(d -> d.getLanguages())
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());

        assertTrue(teamLanguages.containsAll(polyglot.getLanguages()));
        assertTrue(teamLanguages.containsAll(busy.getLanguages()));
    }
}


class Developer {
    private String name;
    private Set<String> languages;

    public Developer(String name) {
        this.name = name;
        this.languages = new HashSet<>();
    }

    public void addLanguage(String language) {
        this.languages.add(language);
    }

    public String getName() {
        return name;
    }

    public Set<String> getLanguages() {
        return Collections.unmodifiableSet(languages);
    }
}
