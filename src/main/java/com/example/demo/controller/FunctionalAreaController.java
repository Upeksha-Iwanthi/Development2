package com.example.demo.controller;

import com.example.demo.Data.IssueSearchResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FunctionalAreaController {

    @CrossOrigin(origins = "*")
    @RequestMapping( path = "api/findForJiraIssue", method = RequestMethod.POST)
    public @ResponseBody
    IssueSearchResult findIssues(@RequestBody final String jiraIssueId)
    {
        return null;
    }
}
