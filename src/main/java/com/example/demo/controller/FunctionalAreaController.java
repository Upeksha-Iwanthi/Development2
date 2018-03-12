package com.example.demo.controller;

import com.example.demo.Data.IssueSearchResult;
import com.example.demo.Data.IssueSearchResultRow;
import com.example.demo.service.FunctionalAreaFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FunctionalAreaController {

    @Autowired
    private FunctionalAreaFinderService functionalAreaFinderService;

    @CrossOrigin(origins = "*")
    @RequestMapping( path = "api/findForJiraIssue", method = RequestMethod.POST)
    public @ResponseBody
    IssueSearchResult findFunctionalAreasForIssueId(@RequestBody final String jiraIssueId)
    {
        IssueSearchResult resultList = functionalAreaFinderService.findFunctionalAreasForIssueId2(jiraIssueId);
        return resultList;
    }

}
