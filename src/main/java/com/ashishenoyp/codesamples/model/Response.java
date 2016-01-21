// --------------------------------------------
// AUTHOR: Ashish Shenoy (ashishenoyp@gmail.com)
// --------------------------------------------

package com.ashishenoyp.codesamples.model;

public class Response {
    private String queryWord;
    private long numWordRequests;
    private long numWordOccurrences;

    public void setNumWordRequests(long numWordRequests) {
        this.numWordRequests = numWordRequests;
    }

    public void setNumWordOccurrences(long numWordOccurrences) {
        this.numWordOccurrences = numWordOccurrences;
    }

    public void setQueryWord(String queryWord) {
        this.queryWord = queryWord;
    }

    public long getNumWordRequests() {
        return numWordRequests;
    }

    public long getNumWordOccurrences() {
        return numWordOccurrences;
    }

    public String getQueryWord() {
        return queryWord;
    }
}
