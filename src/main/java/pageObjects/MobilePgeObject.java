package pageObjects;

import commons.BasePage;
import pageUIs.MobilePageUI;
import org.openqa.selenium.WebDriver;



public class MobilePgeObject extends BasePage {
    WebDriver driver;
    public MobilePgeObject(WebDriver driver)
    {
        this.driver=driver;
    }
    public int getProductMobileNumberOnUI() {
        // TODO Auto-generated method stub
        waitForElementVisible(driver, MobilePageUI.PRODUCT_NAME_NUMBER);
        return countElementNumber(driver,  MobilePageUI.PRODUCT_NAME_NUMBER);

    }
//    public int getProductMobileNumberInDB() {
//        // TODO Auto-generated method stub
//        ArrayList<String> listProduct= new ArrayList<>();
//        //mo ket noi
//        Connection conn=null;
//        try {
//            conn= SQLJTDSConnUtils.getSQLServerConnection();
//
//            String querySql="SELECT*FROM [trangkool].[dbo].[PRODUCT]";
//
//            Statement statement= conn.createStatement();
//
//            ResultSet result=statement.executeQuery(querySql);
//            while(result.next())
//            {System.out.println(result.getString("NAME"));
//                listProduct.add(result.getString("NAME"));
//            }
//            conn.close();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return listProduct.size();
//    }
}

