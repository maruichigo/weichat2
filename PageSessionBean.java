/*
 * PageSessionBean.java 
 *
 * Copyright (c) Shared System Inc.
 */
package jp.co.kintetsuls.wbb.jsf.beans.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * 画面Session情報のクラスです。
 *
 * @author sharedSystem
 */
@ManagedBean(name = "pageSessionBean")
@SessionScoped
public class PageSessionBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    // 遷移元画面情報
    private Map<String, String> seniMotoId;

    // 画面保持情報
    private Map<String, Map<String, Map<String, Object>>> pageConData;
    
    public PageSessionBean() {
        this.seniMotoId = new HashMap();
        this.pageConData = new HashMap();
    }
    
    public Map<String, String> getSeniMotoId() {
        return seniMotoId;
    }
    
    public Map<String, Map<String, Object>> getPageConData(String str) {
        return pageConData.get(str);
    }
    
    public void setSeniMotoId(Map<String, String> seniMotoId) {
        this.seniMotoId = seniMotoId;
    }
    
    public void setPageConData(String str, Map<String, Map<String, Object>> pageConData) {
        this.pageConData.put(str, pageConData);
    }    
    
}
