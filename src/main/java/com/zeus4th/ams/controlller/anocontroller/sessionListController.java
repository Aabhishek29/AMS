package com.zeus4th.ams.controlller.anocontroller;


import com.zeus4th.ams.model.ano.models.SessionList;
import com.zeus4th.ams.repository.ano.repository.SessionListDataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class sessionListController {

    @Autowired
    private SessionListDataFetcher sessionListDataFetcher;

    @GetMapping("allsessiondetails")
    public ResponseEntity<List<SessionList>> getAllSessionDetails(@RequestParam(required = false) Long id){
        try{
            List<SessionList> sessionLists = new ArrayList<>();
            if (id==null){
                sessionListDataFetcher.findAll().forEach(sessionLists::add);
                log.info(sessionListDataFetcher.toString());
            }
            if(sessionLists.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sessionLists,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
