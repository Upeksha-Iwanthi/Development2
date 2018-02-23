package com.example.demo.service.apps;

import com.example.demo.Data.IssueIdFilter;
import com.example.demo.Data.SVNData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.io.SVNRepository;

import java.util.*;
@Component
public class SVNReader {
    private static final Logger LOGGER = Logger.getLogger(SVNReader.class);

    @Autowired
    private MySVNRepositoryFactory mySvnRepositoryFactory;



    public Set<SVNData> getHistory(String url, Long processedRevision, final Map<String,String> propertyHolder) throws SVNException {
        Set<SVNData> totalRowSet = new HashSet<>();

        SVNRepository repository = mySvnRepositoryFactory.create(url);
        long latestRevision = repository.getLatestRevision();
        propertyHolder.put("LatestRev",latestRevision+"");

        if (repository == null)
        {
            System.out.println("Can't create repo for " + url);
        }
        @SuppressWarnings("unchecked")
        Collection<SVNLogEntry> logEntries = repository.log(new String[]{},null, processedRevision, latestRevision, true, true);
        for (SVNLogEntry svnLogEntry : logEntries) {
            String message = svnLogEntry.getMessage();
            List<String> issueList = IssueIdFilter.getValidIssueIds(message);
            totalRowSet.addAll(makeRowData(issueList, new ArrayList<>(svnLogEntry.getChangedPaths().keySet())));
        }
        return totalRowSet;
    }

//    public Long getLatestRevision(String url) throws SVNException {
//        SVNRepository repository = mySvnRepositoryFactory.create(url);
//        return repository.getLatestRevision();
//    }

    private static Set<SVNData> makeRowData(List<String> issueList, List<String> classList) {
        Set<SVNData> rowSet = new HashSet<>();

        for (String issueId : issueList) {
            for (String clazz : classList) {
                rowSet.add(new SVNData(clazz, issueId));
            }
        }
        return rowSet;
    }
}
