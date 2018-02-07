package com.example.demo.service.jira;

import com.atlassian.jira.rest.client.domain.IssueLink;

/**
 * Implementation class for resolved issues strategy.
 *  
 * @author athula.bogoda
 *
 */
public class ResolvedIssueStrategy implements IssueLinkStrategy
{

  @Override
  public boolean satisfy(final IssueLink issueLink)
  {
    return (issueLink.getIssueLinkType().getName().equalsIgnoreCase("Resolve"));
  }

}
