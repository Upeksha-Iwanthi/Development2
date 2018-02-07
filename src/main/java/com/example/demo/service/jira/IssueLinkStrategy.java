package com.example.demo.service.jira;

import com.atlassian.jira.rest.client.domain.IssueLink;

/**
 * Strategy API for linked issues.
 * @author athula.bogoda
 *
 */
public interface IssueLinkStrategy
{
  /**
   * Check given {@link IssueLink} is acceptable type.
   * @param issueLink
   * @return
   */
  boolean satisfy(final IssueLink issueLink);

}
