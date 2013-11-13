package com.doucome.corner.service.web.inter.context;

import java.util.HashMap;
import java.util.Map;


public class AuthzContext { 
 
    private Map<AuthzContextModelEnums, Object> modelMap = new HashMap<AuthzContextModelEnums, Object>();
   
    private boolean                            touch;

    private boolean                            authentication;

    private UserAuthzContext                   user     = new UserAuthzContext();

    public Object getModel(AuthzContextModelEnums key) {
        return modelMap.get(key);
    }

    public void setModel(AuthzContextModelEnums key, Object value) {
        modelMap.put(key, value);
    }

    public void clearModels() {
        modelMap.clear();
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication, boolean touch) {
        this.authentication = authentication;
        if (touch) {
            this.touch = touch;
        }
    }

    public boolean isTouch() {
        return touch;
    }

    public void setTouch(boolean touch) {
        this.touch = touch;
    }
    
    public String getUserName() {
    	return user.getUserName() ;
    }
    
    public void setUserName(String userName) {
    	user.setUserName(userName) ;
    }
    
    public String getAuthKey() {
		return user.getAuthKey();
	}

	public void setAuthKey(String authKey) {
		user.setAuthKey(authKey) ;
	}

    private class UserAuthzContext {

        private String  userName ;
        
        private String authKey ;
        
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getAuthKey() {
			return authKey;
		}

		public void setAuthKey(String authKey) {
			this.authKey = authKey;
		}
               
    }

}
