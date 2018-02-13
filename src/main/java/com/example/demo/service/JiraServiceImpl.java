package com.example.demo.service;

import com.atlassian.jira.rest.client.IssueRestClient;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.domain.Field;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.IssueLink;
import com.atlassian.jira.rest.client.internal.jersey.JerseyJiraRestClientFactory;
import com.example.demo.persistence.FunctionalArea;
import com.example.demo.persistence.ProductArea;
import com.example.demo.service.jira.IssueLinkStrategy;
import com.example.demo.service.jira.ResolvedIssueStrategy;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JiraServiceImpl implements JiraService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JiraServiceImpl.class);

    private IssueRestClient issueClient;

    JiraServiceImpl()
    {
        try
        {
            URI jiraServerURI = new URI("https://issues.cambio.se");
            JerseyJiraRestClientFactory factory = new JerseyJiraRestClientFactory();
            //TODO Need to provide a valid username and a password
            JiraRestClient client = factory.create(jiraServerURI, new BasicHttpAuthenticationHandler("Jira_test", "QazWsx123"));
            issueClient = client.getIssueClient();
        }
        catch (URISyntaxException ex)
        {
            ex.printStackTrace();
        }
    }


    @Override
    public ProductArea findProductAreasForIssueId(String issueId) throws Exception
    {
        final List<FunctionalArea> functionalAreas = new ArrayList<>();
        ProductArea productArea =new ProductArea();

        // Get the product areas of the issue
        final NullProgressMonitor progressMonitor = new NullProgressMonitor();
        Issue issue = null;
        try {
            issue = issueClient.getIssue(issueId, progressMonitor);
        } catch (Exception e) {
            LOGGER.error("Can't find issue " + issueId + " " + e.getMessage());
            return productArea;
        }

        productArea = setProductArea(issue,functionalAreas);
        if(productArea==null){
            // Get the linked issues
            final IssueLinkStrategy strategy = new ResolvedIssueStrategy();
            for (final IssueLink link : issue.getIssueLinks())
            {
                if (strategy.satisfy(link))
                {
                    final Issue linkedIssue = issueClient.getIssue(link.getTargetIssueKey(),progressMonitor);
                    return setProductArea(linkedIssue,functionalAreas);
                }
            }
        }

        return productArea;
    }


//    public static void main(String[] args) {
//        try {
//            final JiraServiceImpl obj = new JiraServiceImpl();
//            obj.findProductAreasForIssueId("IMOD-85537");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

        private ProductArea setProductArea(final Issue issue,List<FunctionalArea> functionalAreas) throws Exception {
            ProductArea productArea = new ProductArea();
            final Field productAreaField = issue.getFieldByName("Product Area");
            if (productAreaField != null)
            {
                Object productAreaValue = productAreaField.getValue();

                if (productAreaValue != null && productAreaValue instanceof JSONObject)
                {
                    final JSONObject productAreaJson = (JSONObject) productAreaValue;
                    String productName = productAreaJson.getString("value");

                    String faName = setFunctionalArea(productAreaJson);

                    productArea.setName(productName);

                    addFunctionalArea(productArea,faName,functionalAreas);

                    productArea.setFa(functionalAreas);

                    return productArea;
                } else {
                    Issue parentIssue = getParentIssue(issue);
                    if (parentIssue != null){
                        return setProductArea(parentIssue,functionalAreas);
                    }
                }
            }

            return null;
        }

        private String setFunctionalArea(final JSONObject productAreaJson) throws Exception{
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
            return area;
        }

        private void addFunctionalArea(ProductArea productArea, String area, List<FunctionalArea> functionalAreas){
            FunctionalArea fa = new FunctionalArea();
            fa.setName(area);
            fa.setProductArea(productArea);
            if(!functionalAreas.contains(fa))
            {
                functionalAreas.add(fa);
            }
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
