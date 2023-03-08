package com.example.hw49.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("src/main/resources/static/json/candidates.json");

    public static List<Candidate> readCandidates() {
        var type = new TypeToken<ArrayList<Candidate>>() {}.getType();
        String json = "";
        try {
            json = Files.readString(PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GSON.fromJson(json, type);
    }


}

