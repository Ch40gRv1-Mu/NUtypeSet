package com.example.nutypeset.model.entity;

public class Box {
    private String environment;
    private String postParam="none";
    private String contents;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getHasPostParam() {
        return postParam;
    }

    public void setHasPostParam(String hasPostParam) {
        this.postParam = hasPostParam;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String returnBox(){
        String result="";
        if (postParam == "none"){
            result += "\\begin{"+environment+"}\n";
        }
        else{
            result += "\\begin{"+environment+"}["+postParam+"]\n";
        }
        result += (contents+"\n");
        result += "\\end{"+environment+"}\n";
        return result;
    }

}
