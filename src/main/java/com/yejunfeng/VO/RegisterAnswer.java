package com.yejunfeng.VO;

public class RegisterAnswer {
    private String action;
    private String result;

    public RegisterAnswer(String action, String result) {
        this.action = action;
        this.result = result;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
