package com.example.demo.Data;

public class SVNData {
    private String classPath;
    private String issueId;


    public SVNData(String classPath, String issueId)
    {
        this.classPath = classPath;
        this.issueId = issueId;
    }

    public String getIssueId()
    {
        return issueId;
    }

    public String getClassPath()
    {
        return classPath;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classPath == null) ? 0 : classPath.hashCode());
        result = prime * result + ((issueId == null) ? 0 : issueId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SVNData other = (SVNData) obj;
        if (classPath == null)
        {
            if (other.classPath != null)
                return false;
        }
        else if (!classPath.equals(other.classPath))
            return false;
        if (issueId == null)
        {
            if (other.issueId != null)
                return false;
        }
        else if (!issueId.equals(other.issueId))
            return false;
        return true;
    }
}
