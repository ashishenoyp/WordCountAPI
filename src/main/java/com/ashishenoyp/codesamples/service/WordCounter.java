// --------------------------------------------
// AUTHOR: Ashish Shenoy (ashishenoyp@gmail.com)
// --------------------------------------------

package com.ashishenoyp.codesamples.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

@Component
public class WordCounter {
    private static Log logger = LogFactory.getLog(WordCounter.class);
    private Map<String, Long> wordOccurrenceCountsMap;

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    public void parseFiles(Map<String, Long> wordOccurrenceCountsMap) {
        logger.debug("Initializing Word Counter");
        this.wordOccurrenceCountsMap = wordOccurrenceCountsMap;
        try {
            Resource[] resources = resourcePatternResolver.getResources("classpath:txtfiles/*");
            for (Resource resource : resources) {
                logger.debug("Found file: " + resource.getFilename());
                parseFile(resource);
            }
        } catch (IOException ex) {
            logger.error("Could not parse files !", ex);
        }
        logger.info("Initialized Word Counter");
    }

    private void parseFile(Resource file) throws IOException {
        InputStream is = file.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = br.readLine()) != null) {
            // count words
            String[] words = line.split(" ");
            for (String word : words) {
                String lowerCaseWord = word.toLowerCase();
                Long count = wordOccurrenceCountsMap.get(lowerCaseWord);
                if (count == null) {
                    wordOccurrenceCountsMap.put(lowerCaseWord, 1L);
                } else {
                    wordOccurrenceCountsMap.put(lowerCaseWord, count+1);
                }
            }
        }
        br.close();
    }
}
