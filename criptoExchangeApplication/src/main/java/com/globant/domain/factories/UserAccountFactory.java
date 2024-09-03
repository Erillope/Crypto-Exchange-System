package com.globant.domain.factories;

import com.globant.domain.factories.creationorders.OutlookCreationOrder;
import com.globant.domain.factories.creationorders.GmailCreationOrder;
import com.globant.domain.factories.creationorders.EmailCreationOrder;
import com.globant.domain.exceptions.InvalidUserAccountException;
import com.globant.domain.user.accounts.GmailAccount;
import com.globant.domain.user.accounts.OutlookAccount;
import com.globant.domain.user.accounts.UserAccount;
import com.globant.domain.util.EmailDomainExtractor;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author erillope
 */
public class UserAccountFactory {
    private static final Map<String, EmailCreationOrder> emailDomains;
    static {
        emailDomains = new HashMap<>();
        emailDomains.put("gmail", new GmailCreationOrder());
        emailDomains.put("outlook", new OutlookCreationOrder());
    }
    
    public GmailAccount createGmailAccount(String name, String email, String password) throws InvalidUserAccountException{
        return new GmailAccount(name, email, password);
    }
    
    public OutlookAccount createOutlookAccount(String name, String email, String password) throws InvalidUserAccountException{
        return new OutlookAccount(name, email, password);
    }
    
    public UserAccount createAccount(String name, String email, String password) throws InvalidUserAccountException{
        String emailDomain = EmailDomainExtractor.extract(email);
        EmailCreationOrder creationOrder = emailDomains.get(emailDomain);
        return creationOrder.execute(this, name, email, password);
    }
    
    
}
