import java.util.List;

public interface Service {
    List<Product> displayAllProducts();

    int removeProduct(int pId);

    int updateProduct(int pId, String pName);

    boolean placeOrder(String cName, int pId, int pQty);

    List<Order> displayAllOrders();
}
