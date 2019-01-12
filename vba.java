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
