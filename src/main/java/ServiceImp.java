import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceImp implements Service{

    static Connection con=null;

    static {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String pass = "Saurav@229";
        try {
            con= DriverManager.getConnection(url,username,pass);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    @Override
    public List<Product> displayAllProducts() {

        List<Product> productList=new ArrayList<>();

        String query="select * from product_info";
        try {
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);

            while (rs.next()){
                int pId=rs.getInt(1);
                String pName= rs.getString(2);
                int pQty= rs.getInt(3);
                double pPrice= rs.getDouble(4);

                Product p=new Product(pId,pName,pQty,pPrice);

                productList.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return productList;
    }

    @Override
    public int removeProduct(int pId) {
        int n=0;
        String query="delete from product_info where product_id=?";

        try {
            PreparedStatement pstmt= con.prepareStatement(query);
            pstmt.setInt(1,pId);
           n= pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    @Override
    public int updateProduct(int pId, String pName) {
        int n=0;
        String query="update product_info set product_name=? where product_id=?";
        try {
            PreparedStatement pstmt= con.prepareStatement(query);
            pstmt.setString(1,pName);
            pstmt.setInt(2,pId);
            n=pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    @Override
    public boolean placeOrder(String cName, int pId, int pQty) {
        boolean status=false;

        try {
            CallableStatement cstmt= con.prepareCall("{call placeOrder(?,?,?,?)}");
            cstmt.setString(1,cName);
            cstmt.setInt(2,pId);
            cstmt.setInt(3,pQty);
            cstmt.execute();

            status= cstmt.getBoolean(4);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return status;
    }

    @Override
    public List<Order> displayAllOrders() {
        List<Order> orderList=new ArrayList<>();
        String query="select * from order_info";
        try {
            Statement stmt= con.createStatement();
           ResultSet rs= stmt.executeQuery(query);

           while(rs.next()){
               int oId= rs.getInt(1);
               String cName= rs.getString(2);
               int pId= rs.getInt(3);
               int pQty= rs.getInt(4);
               double total= rs.getDouble(5);

               Order order=new Order(oId,cName,pId,pQty,total);

               orderList.add(order);
           }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderList;
    }


}
