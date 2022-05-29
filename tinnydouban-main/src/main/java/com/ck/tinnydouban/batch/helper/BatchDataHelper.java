package com.ck.tinnydouban.batch.helper;



import java.util.*;

public class BatchDataHelper {


    private final Map<String, Long> categoryMap;

    private final Map<String, Long> languageMap;

    private final Map<String, Long> regionMap;

    private final Map<String, Long> professionMap;




    private BatchDataHelper() {

        categoryMap = new HashMap<>();
        languageMap = new HashMap<>();
        regionMap = new HashMap<>();
        professionMap = new HashMap<>();
    }

    private static class InstanceHolder {
        public static final BatchDataHelper INSTANCE = new BatchDataHelper();
    }

    public static BatchDataHelper getInstance() {
        return InstanceHolder.INSTANCE;
    }


    public void addCategory(String str, Long id) {
        categoryMap.put(str, id);
    }

    public void addLanguage(String str, Long id) {
        languageMap.put(str, id);
    }

    public void addRegion(String str, Long id) {
        regionMap.put(str, id);
    }

    public void addProfession(String str, Long id) {
        professionMap.put(str, id);
    }


    public Long hasCategory(String str) {
        return categoryMap.getOrDefault(str, (long) -1);
    }

    public Long hasLanguage(String str) {
        return languageMap.getOrDefault(str, (long) -1);
    }

    public Long hasRegion(String str) {
        return regionMap.getOrDefault(str, (long) -1);
    }

    public Long hasProfession(String str) {
        return professionMap.getOrDefault(str, (long) -1);
    }


}
