// --------------------------------------------
// AUTHOR: Ashish Shenoy (ashishenoyp@gmail.com)
// --------------------------------------------

package com.ashishenoyp.codesamples.service;

public interface WordCountService {
    public long getNumWordRequests(String queryWord);

    public long getNumWordOccurrences(String queryWord);
}
