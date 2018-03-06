package com.example.demo.controller;

import com.example.demo.Data.IssueSearchResult;
import com.example.demo.persistence.FunctionalAreaClass;
import com.example.demo.persistence.IssueId;
import com.example.demo.repository.FunctionalAreaClassRepository;
import com.example.demo.service.FunctionalAreaFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FunctionalAreaController {

    @Autowired
    private FunctionalAreaFinderService functionalAreaFinderService;

    @CrossOrigin(origins = "*")
    @RequestMapping( path = "api/findForJiraIssue", method = RequestMethod.POST)
    public @ResponseBody
    List<IssueSearchResult> findFunctionalAreasForIssueId(@RequestBody final String jiraIssueId)
    {
        List<IssueSearchResult> resultList = functionalAreaFinderService.findFunctionalAreasForIssueId(jiraIssueId);
        return resultList;
    }

}
