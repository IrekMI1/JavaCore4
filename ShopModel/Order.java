package JavaSeminar4.ShopModel;

public class Order {
    private int count;
    private double total;
    private Buyer buyer;
    private Goods goods;

    public Order(Buyer buyer, Goods goods, int count) {
        this.count = count;
        this.buyer = buyer;
        this.goods = goods;
        this.total = goods.getPrice() * count * (1 - goods.getDiscountValue());
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public int getCount() {
        return count;
    }

    public Goods getGoods() {
        return goods;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "count=" + count +
                ", buyer=" + buyer +
                ", goods=" + goods +
                ", total=" + total +
                '}';
    }

    public static class CustomerException extends RuntimeException {
        public CustomerException(String message) {
            super(message);
        }
    }

    public static class AmountException extends RuntimeException {
        public AmountException(String message) {
            super(message);
        }
    }

    public static class ProductException extends RuntimeException {
        public ProductException(String message) {
            super(message);
        }
    }

    public static class TooMuchSaleException extends RuntimeException {
        public TooMuchSaleException(String message) { super(message);}
    }
}
