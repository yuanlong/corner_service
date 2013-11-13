package com.doucome.corner.service.web.inter.context;

/**
 * 
 */
public class AuthzContextHolder {

    private static ThreadLocal<AuthzContext> contextHolder = new ThreadLocal<AuthzContext>();

    public static AuthzContext getContext() { 
        if (contextHolder.get() == null) {
            setContext(new AuthzContext());
        }
        return contextHolder.get();
    }

    public static void setContext(AuthzContext context) {
        contextHolder.set(context);
    }
}
