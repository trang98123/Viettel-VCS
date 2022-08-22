package stepDefinications;

public class TestContext {
    //lay ra thang dataContext
    //chi new 1 doi tuong duy nhat
    public DataContext dataContext;
    public TestContext()
    {
        dataContext= new DataContext();
    }

    public DataContext getDataContext() {
        return dataContext;
    }
}
