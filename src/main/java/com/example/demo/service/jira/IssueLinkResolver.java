package com.example.demo.service.jira;

import com.atlassian.jira.rest.client.IssueRestClient;
import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.RestClientException;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.IssueLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Resolver class which is responsible for get linked issues based on given stratergy.
 * @author athula.bogoda
 *
 */
@Component
public class IssueLinkResolver
{
  private IssueLinkStrategy linkedIssueStrategy;

  private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  public void setStrategy(IssueLinkStrategy linkedIssueStrategy)
  {
    this.linkedIssueStrategy = linkedIssueStrategy;
  }

  public List<String> getFunctionalAreas(final IssueRestClient issueRestClient,final String issueId)
  {
      final List<String> functionalList = new ArrayList<>();
      return functionalList;
  }

  public List<String> getLinkedIssues(final IssueRestClient issueRestClient, List<String> issueList)
  {
    List<String> resolvedIssueList = new ArrayList<>();
    String currentIssue = "";

    for (String issueId : issueList)
    {
      currentIssue = issueId;
      try
      {
        Issue issue = issueRestClient.getIssue(issueId, new NullProgressMonitor());
        Iterable<IssueLink> issueLinks = issue.getIssueLinks();
        for (IssueLink issueLink : issueLinks)
        {
          if (linkedIssueStrategy.satisfy(issueLink))
          {
            resolvedIssueList.add(issueLink.getTargetIssueKey());
          }
        }
      }catch (RestClientException ce){
          LOGGER.info("Issue can't be read : " + currentIssue);
          ce.printStackTrace();
      }
      catch (Exception ex)
      {
        LOGGER.info("Issue does not exist: " + currentIssue);
        ex.printStackTrace();
      }

    }
    return resolvedIssueList;
  }
}
