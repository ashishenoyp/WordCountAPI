// --------------------------------------------
// AUTHOR: Ashish Shenoy (ashishenoyp@gmail.com)
// --------------------------------------------

package com.ashishenoyp.codesamples.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceImpl implements Service {
    private Map<String, Long> wordRequestCountsMap;
    private Map<String, Long> wordOccurrenceCountsMap;

    @Autowired
    private WordCounter wordCounter;

    @PostConstruct
    public void setup() {
        wordOccurrenceCountsMap = new HashMap<>();
        wordRequestCountsMap = new HashMap<>();
        // Load the resources file
        wordCounter.parseFiles(wordOccurrenceCountsMap);
    }

    @Override
    public long getNumWordOccurrences(String queryWord) {
        Long numOccurrences = wordOccurrenceCountsMap.get(queryWord);
        if (numOccurrences == null) {
            return 0;
        }
        return numOccurrences;
    }

    @Override
    public long getNumWordRequests(String queryWord) {
        Long numRequests = wordRequestCountsMap.get(queryWord);
        if (numRequests == null) {
            wordRequestCountsMap.put(queryWord, 1L);
            return 1L;
        }
        wordRequestCountsMap.put(queryWord, numRequests + 1);
        return numRequests + 1;
    }
}