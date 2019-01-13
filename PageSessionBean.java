/*
 * PageSessionBean.java 
 *
 * Copyright (c) Shared System Inc.
 */
package jp.co.kintetsuls.wbb.jsf.beans.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Map<String, byte[]> pageConData;

    public PageSessionBean() {
        this.seniMotoId = new HashMap();
        this.pageConData = new HashMap();
    }

    public Map<String, String> getSeniMotoId() {
        return seniMotoId;
    }

    public Map<String, Map<String, Object>> getPageConData(String str) {
        ObjectInputStream ois = null;
        if (pageConData.get(str) != null) {
            Map<String, Map<String, Object>> res = null;
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(pageConData.get(str));
                ois = new ObjectInputStream(bais);
                res = (Map<String, Map<String, Object>>) ois.readObject();
                return res;
            } catch (EOFException ex) {
                return res;
            } catch (IOException ex) {
                Logger.getLogger(PageSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PageSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ois.close();
                } catch (IOException ex) {
                    Logger.getLogger(PageSessionBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public void setSeniMotoId(Map<String, String> seniMotoId) {
        this.seniMotoId = seniMotoId;
    }

    public void setPageConData(String str, Map<String, Map<String, Object>> pageConData) {
        byte[] mapNew = clone((HashMap) pageConData);
        this.pageConData.put(str, mapNew);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Serializable> T clone(HashMap obj) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();

            return (T) baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
