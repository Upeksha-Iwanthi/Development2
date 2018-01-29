package com.example.demo.service.apps;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MySVNRepositoryFactory
{
    @SuppressWarnings("deprecation")
    public SVNRepository create(final String url) throws SVNException
    {
        SVNRepository svnRepository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(new File(getAppDataPath()),
                "usr_sjenkins", "S0r&k!n$");
        svnRepository.setAuthenticationManager(authManager);
        return svnRepository;
    }

    private String getAppDataPath()
    {
        return new StringBuilder("C:\\Users\\UIwanthi\\AppData\\Roaming\\Subversion").toString();
    }
}
