https://blog.csdn.net/babylove_BaLe/article/details/73201115

    @ManagedProperty(value = "#{pageBean}")
    private SSNaviManagedBean pageBean;  

    @ManagedProperty(value = "#{pageSessionBean}")
    private PageSessionBean pageSessionBean;

    public SSNaviManagedBean getPageBean() {
        return pageBean;
    }

    public PageSessionBean getPageSessionBean() {
        return pageSessionBean;
    }

    public void setPageBean(SSNaviManagedBean pageBean) {
        this.pageBean = pageBean;
    }

    public void setPageSessionBean(PageSessionBean pageSessionBean) {
        this.pageSessionBean = pageSessionBean;
    }





    public void init() {
        // 戻ってきた場合
        if (pageSessionBean.getPageConData("Mst501") != null) {
            pageBean.setValues(pageSessionBean.getPageConData("Mst501"));
            pageBean.setTestText((String) pageSessionBean.getPageConData("Mst501").get("detail").get("SHIMUKE_CHI_MEI"));
        }
        // 進んできた場合
        System.out.println("init!");
    }
    
    /**
     * 後処理
     * @param bean
     * @param event
     * @param messageData
     * @param backFlag
     */
//    public void search(SSNaviManagedBean bean, FacesEvent event, MessageDataModel messageData, Boolean backFlag) {
    public void search() {
    String str;
    pageSessionBean.setPageConData("Mst501",pageBean.getValues());
        str = "test";    
    }

------------------------------------------------------------------------------------------------------------------------
                                                        <!-- 入力項目テンプレート呼び出し -->
                                                    <ui:include src="/templates/search_control_main.xhtml" >
                                                        <ui:param name="condition" value="#{column}" />
                                                        <ui:param name="targetIdx" value="0" />
                                                        <c:choose> 
                                                            <c:when test="#{pageSessionBean.getPageConData('Mst501').size() > 0 }">    <!--如果 --> 
                                                                <ui:param name="valueit" value="#{pageSessionBean.getPageConData('Mst501')[condition.applyTo][condition.name]}" />
                                                            </c:when>      
                                                            <c:otherwise>  <!--否则 -->    
                                                                <ui:param name="valueit" value="#{pageBean.values[condition.applyTo][condition.name]}" />
                                                            </c:otherwise> 
                                                        </c:choose>
                                                        <ui:param name="widgetVarId" value="#{column.applyTo}_#{condition.name}" />
                                                    </ui:include>
