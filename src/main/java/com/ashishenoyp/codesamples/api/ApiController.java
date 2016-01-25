// --------------------------------------------
// AUTHOR: Ashish Shenoy (ashishenoyp@gmail.com)
// --------------------------------------------

package com.ashishenoyp.codesamples.api;

import com.ashishenoyp.codesamples.model.Response;
import com.ashishenoyp.codesamples.service.WordCountService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {
    private static Log logger = LogFactory.getLog(ApiController.class);

    @Autowired
    private WordCountService wordCountService;

    @ResponseBody
    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public ResponseEntity<Response> queryWord(@RequestParam (value = "word") String queryWord) {
        logger.debug("GET method called for word: " + queryWord);
        queryWord = queryWord.toLowerCase();

        if (queryWord == null || queryWord.isEmpty()) {
            return new ResponseEntity<Response>(HttpStatus.BAD_REQUEST);
        }

        Response response = new Response();
        response.setQueryWord(queryWord);
        response.setNumWordOccurrences(wordCountService.getNumWordOccurrences(queryWord));
        response.setNumWordRequests(wordCountService.getNumWordRequests(queryWord));

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
}
