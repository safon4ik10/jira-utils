package ru.rvision.utils.services;

/**
 * @author Vladimir Troshin on 29.06.2022
 * https://jira.rvision.pro/browse/
 */
public enum RocketChatRestsV1 {
    ChatPostMessage("chat.postMessage", "POST", true);

    private String methodName;
    private String httpMethod;
    private boolean requireAuth;

    private RocketChatRestsV1(String methodName, String httpMethod, boolean requireAuth) {
        this.methodName = methodName;
        this.httpMethod = httpMethod;
        this.requireAuth = requireAuth;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public boolean isRequireAuth() {
        return this.requireAuth;
    }
}
