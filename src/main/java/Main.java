import java.util.List;
import java.util.Scanner;

public class Main {
    static Service service=new ServiceImp();
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {


        System.out.println("SELECT SERVICE");
        System.out.println("1:DISPLAY ALL PRODUCTS \n 2:REMOVE PRODUCT \n 3:UPDATE PRODUCT \n 4:PLACE ORDER \n 5:DISPLAY ALL ORDERS \n 6:EXIT");

        int choice=sc.nextInt();

        switch (choice){
            case 1:
                displayAllProducts();
                break;
            case 2:
                removeProduct();
                break;
            case 3:
                updateProduct();
                break;
            case 4:
                placeOrder();
                break;
            case 5:
                displayAllOrders();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.err.println("INVALID CHOICE");
        }
        main(args);
    }

    public static void displayAllOrders() {
        List<Order> orderList=service.displayAllOrders();
        for (Order o:orderList)
        System.out.println(o.getOrderId()+"\t"+o.getCustomerName()+"\t"+o.getProductId()+"\t"+o.getProductQty()+"\t"+o.getTotalAmt());
    }

    public static void placeOrder() {
        System.out.println("Enter Customer Name");
        String cName= sc.next();
        System.out.println("Enter Product Id");
        int pId= sc.nextInt();
        System.out.println("Enter Product Qty");
        int pQty= sc.nextInt();

        boolean status=service.placeOrder(cName,pId,pQty);
        if (status)
            System.out.println("ORDER PLACED");
        else
            System.out.println("ORDER NOT PLACED");
    }

    public static void updateProduct() {
        System.out.println("Enter Product id");
        int pId= sc.nextInt();
        System.out.println("Enter Product Name");
        String pName= sc.next();
        int n=service.updateProduct(pId,pName);
        System.out.println(n+" Product Updated");
    }

    public static void removeProduct() {
        System.out.println("ENTER PRODUCT ID");
        int pId= sc.nextInt();
        int n=service.removeProduct(pId);
        System.out.println(n+" Product Deleted");
    }

    public static void displayAllProducts() {
        List<Product> productList=service.displayAllProducts();
        for (Product p:productList){
            System.out.println(p.getProductId()+"\t"+p.getProductName()+"\t"+p.getProductQty()+"\t"+p.getProductPrice());
        }
    }
}