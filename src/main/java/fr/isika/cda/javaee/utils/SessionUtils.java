package fr.isika.cda.javaee.utils;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.isika.cda.javaee.entity.accounts.Account;


public class SessionUtils {
	public SessionUtils() {}

	private static final String ADHERENT = "loggedInUser";
	


    public static HttpSession getSession() {
    	HttpSession session =  (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//       System.out.println("Ma session " + session);
    	return session;
    }

    // Storing user object in session
    public static void setAccount(Account user) {
        getSession().setAttribute(ADHERENT, user);
    }

    // Retrieving user object from session
    public static Account getAccount() {
    Account account=(Account) getSession().getAttribute(ADHERENT);
//    System.out.println(account);
        return account;
    }
}
