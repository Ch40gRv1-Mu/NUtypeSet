package com.example.nutypeset.model.request;

/**
 * login request
 */
public class UpdateLatexRequest {
    private String pdfsrc;
    private String latexFinalCode;

    public String getPdfsrc() {
        return pdfsrc;
    }

    public void setPdfsrc(String pdfsrc) {
        this.pdfsrc = pdfsrc;
    }

    public String getLatexFinalCode() {
        return latexFinalCode;
    }

    public void setLatexFinalCode(String latexFinalCode) {
        this.latexFinalCode = latexFinalCode;
    }
}
