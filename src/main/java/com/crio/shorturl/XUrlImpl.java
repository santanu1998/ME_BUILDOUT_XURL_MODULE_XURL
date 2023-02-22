package com.crio.shorturl;

import java.util.*;
// import java.util.Random;

public class XUrlImpl implements XUrl {

    HashMap<String, String> longUrlaMap = new HashMap<>();
    HashMap<String, String> shortUrlMap = new HashMap<>();
    HashMap<String,Integer> hitCount = new HashMap<>();

    private final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final char[] ALPHA_NUMERIC =
            (LETTERS + LETTERS.toUpperCase() + "0123456789").toCharArray();

    public String generateRandomAlphaNumeric() {
        StringBuilder sb = new StringBuilder();        
        for (int i = 0; i < 9; i++) {
            sb.append(ALPHA_NUMERIC[new Random().nextInt(9)]);
        }       
        return sb.toString();
    }

    public String registerNewUrl(String longUrl) {
        for(String lu : longUrlaMap.keySet()){
            if(lu.equals(longUrl)){
                return longUrlaMap.get(lu);
            }
        }
        
        String shortUrl = "http://short.url/" + generateRandomAlphaNumeric();
        longUrlaMap.put(longUrl, shortUrl);
        return shortUrl;

    }

    public String registerNewUrl(String longUrl, String shortUrl) {
        for(String url : longUrlaMap.values()){
            if(shortUrl.equals(url)){
                return null;
            }
        }
        longUrlaMap.put(longUrl, shortUrl);
        return shortUrl;
    }

    public String getUrl(String shortUrl) {
        for (String url : longUrlaMap.keySet()) {
            if (shortUrl.equals(longUrlaMap.get(url))) {
                hitCount.put(url, hitCount.getOrDefault(url, 0) + 1);
                return url;
            }
        }
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        if(hitCount.containsKey(longUrl)){
            return hitCount.get(longUrl);
        }
        return 0;
    }

    public String delete(String longUrl) {
        longUrlaMap.remove(longUrl);
        return longUrl;
    }
}