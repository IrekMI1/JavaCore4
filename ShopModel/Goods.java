package JavaSeminar4.ShopModel;

import java.util.Random;

public class Goods {
    enum Categories {
        HOME(Discounts.ZERO),
        FOOD(Discounts.TWENTY),
        SPORT(Discounts.ZERO),
        ELECTRONICS(Discounts.TWENTY),
        TOOLS(Discounts.TWENTY);
        private Discounts discount;

        Categories(Discounts discount) {
            this.discount = discount;
        }

        private void setDiscount(Discounts discount) {
            this.discount = discount;
        }
    }

    enum Discounts {
        ZERO(0), FIVE(5), TEN(10), FIFTEEN(15), TWENTY(20);
        private final int discount;

        Discounts(int discount) {
            this.discount = discount;
        }

        public int getDiscount() {
            return discount;
        }
    }

    private int price;
    private String name;
    private final Categories category;
    private Discounts discount;

    public Goods(String name, int price, Categories category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Categories getCategory() {
        return category;
    }

    public double getDiscountValue() {
        return this.category.discount.getDiscount() / 100.0;
    }

    public static void setRandomDiscount(Categories category) {
        Discounts[] discounts = Discounts.values();
        Random random = new Random();
        category.setDiscount(
                discounts[random.nextInt(0, discounts.length)]
        );
    }

    @Override
    public String toString() {
        return "Goods{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", discount=" + category.discount.getDiscount() +
                '}';
    }
}
