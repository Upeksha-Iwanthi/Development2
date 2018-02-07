package com.example.demo.service;

import com.atlassian.jira.rest.client.IssueRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.Field;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.IssueLink;
import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.ProductArea;
import com.example.demo.service.jira.IssueLinkStrategy;
import com.example.demo.service.jira.ResolvedIssueStrategy;
import org.codehaus.jettison.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JiraServiceImpl implements JiraService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JiraServiceImpl.class);

    private IssueRestClient issueClient;


    @Override
    public ProductArea findProductAreasForIssueId(String issueId) throws Exception
    {
        final List<FunctionalArea> functionalAreas = new ArrayList<>();
        final ProductArea productArea =new ProductArea();

        // Get the product areas of the issue
        final NullProgressMonitor progressMonitor = new NullProgressMonitor();
        Issue issue = null;
        try {
            issue = issueClient.getIssue(issueId, progressMonitor);
        } catch (Exception e) {
            LOGGER.error("Can't find issue " + issueId + " " + e.getMessage());
            return productArea;
        }
        // Get the linked issues
        final IssueLinkStrategy strategy = new ResolvedIssueStrategy();
        for (final IssueLink link : issue.getIssueLinks())
        {
            if (strategy.satisfy(link))
            {
                final Issue linkedIssue = issueClient.getIssue(link.getTargetIssueKey(),progressMonitor);
                addFunctionalArea(functionalList,linkedIssue);
            }
        }

        return getProductArea(issue,functionalAreas);
    }

    private ProductArea getProductArea(final Issue issue,List<FunctionalArea> functionalAreas) throws Exception
    {
        final Field productAreaField = issue.getFieldByName("Functional Area");
        if (productAreaField != null)
        {
            Object productAreaValue = productAreaField.getValue();

            if (productAreaValue != null && productAreaValue instanceof JSONObject)
            {
                final JSONObject productAreaJson = (JSONObject) productAreaValue;
                String productName = productAreaJson.getString("value");

                String area = null;
                try {
                    Object child = productAreaJson.get("child");
                    if (child != null && child instanceof JSONObject)
                    {
                        JSONObject childObj = (JSONObject)child;
                        area = childObj.getString("value");
                    }
                } catch (Exception e) {
                    area = "";
                }
                FunctionalArea fa = new FunctionalArea(area);
                if(!functionalAreas.contains(fa))
                {
                    functionalAreas.add(fa);
                }
                return new ProductArea(productName,functionalAreas);
            } else {
                Issue parentIssue = getParentIssue(issue);
                if (parentIssue != null){
                    return getProductArea(parentIssue,functionalAreas);
                }
            }
        }

        return null;
    }

    private Issue getParentIssue(Issue issue) throws JSONException
    {
        Field parentField = issue.getFieldByName("Parent");
        if (parentField != null)
        {
            Object parentValue = parentField.getValue();
            return issueClient.getIssue(((org.codehaus.jettison.json.JSONObject) parentValue).getString("key"),new NullProgressMonitor());
        }
        return null;
    }

}
