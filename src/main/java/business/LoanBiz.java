package business;

public class LoanBiz {
    private static LoanBiz ourInstance = new LoanBiz();

    public static LoanBiz getInstance() {
        return ourInstance;
    }

    private LoanBiz() {
    }

}
