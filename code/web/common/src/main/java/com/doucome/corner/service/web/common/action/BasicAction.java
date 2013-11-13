package com.doucome.corner.service.web.common.action;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.CollectionUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * BasicActionSupport
 * 
 * @author langben 2012-2-24
 */
@SuppressWarnings("serial")
public class BasicAction extends ActionSupport {
    
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    protected Map<String, Object> getSession() {
        return ActionContext.getContext().getSession();
    }

    protected HttpSession getHttpSession() {
        return getRequest().getSession();
    }
    
    protected void redirect(String url) throws IOException {
        HttpServletResponse response = getResponse();
        response.sendRedirect(url);
    }

    /**
     * 值栈
     * 
     * @return
     */
    protected ValueStack getValueStack() {
        return ServletActionContext.getValueStack(getRequest());
    }

    /**
     * @return
     */
    protected Map<String, Object> getParameters() {
        ActionContext actionContext = ActionContext.getContext();
        Map<String, Object> parameters = (Map<String, Object>) actionContext.getParameters();
        return parameters;
    }

    public String getActionError() {
        Collection<String> errors = getActionErrors();
        if (errors == null || errors.isEmpty()) {
            return null;
        }
        return errors.iterator().next();
    }

    public String getActionMessage() {
        Collection<String> messages = getActionMessages();
        if (messages == null || messages.isEmpty()) {
            return null;
        }
        return messages.iterator().next();
    }
    
    public String getFieldError(String field) {
    	Map<String,List<String>> errMap = getFieldErrors() ;
    	List<String> errList = errMap.get(field) ;
    	if(CollectionUtils.isEmpty(errList)) {
    		return null ;
    	}
    	return errList.iterator().next() ;
    }
}
