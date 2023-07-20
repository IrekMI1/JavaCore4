package JavaSeminar4.ShopModel;

import JavaSeminar4.ShopModel.Goods.*;

public class Shop {
    /*
    В класс Товаров добавить перечисление с категориями товаров, добавить в Товар поле категория со значением
    созданного перечисления. Добавить геттеры, сеттеры.
    Добавить перечисление с размерами скидок - 0, 5, 10, 15, 20%.

    Написать метод, при вызове которого на переданную категорию товара незначается рандомная скидка из перечисления.

    Добавить в заказ поле стоимость и пересчитать стоимость согласно сгенерированным скидкам.
    Если сумма величин скидок на товары из заказа получилась больше 50%, выбросить исключение TooMuchSaleException();
    Создать заказ с общей скидкой 20%. То есть нужно сложить величины скидок на категории и проверить больше 50 или нет.
    */
    public static final Goods[] goods = {
            new Goods("Phone", 26000, Categories.ELECTRONICS),
            new Goods("Fried chicken", 300, Categories.FOOD),
            new Goods("Chainsaw", 5677, Categories.TOOLS),
            new Goods("NoteBook", 78000, Categories.ELECTRONICS),
            new Goods("Table", 6500, Categories.HOME)
    };

    private static final Buyer[] buyers = {
            new Buyer("Peter", "+7-963-566-12-63", 38),
            new Buyer("John", "+7-965-489-32-85", 27),
    };

    private static final Order[] orders = new Order[5];

    public static Order buy(Buyer buyer, Goods item, int count) {
        if (count < 0 || count > 100) {
            throw new Order.AmountException(String.format("Count %d is ouf of correct amount.", count));
        }
        if (!isInArray(goods, item)) {
            throw new Order.ProductException(String.format("Product %s isn't in array", item));
        }
        if (!isInArray(buyers, buyer)) {
            throw new Order.CustomerException(String.format("Customer %s isn't in array", buyer));
        }
        if (isTooMuchSale(orders)) {
            throw new Order.TooMuchSaleException("Too much overall discount in orders.");
        }
        return new Order(buyer, item, count);
    }

    private static boolean isInArray(Object[] objects, Object object) {
        for (Object o : objects) {
            if (o.equals(object)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isTooMuchSale(Order[] orders) {
        double discountSum = 0.0;
        for (Order order : orders) {
            if (order != null) {
                discountSum += order.getGoods().getDiscountValue();
            } else {
                break;
            }
        }
        return discountSum >= 0.5;
    }

    public static void main(String[] args) {
        Object[][] info = {
                {buyers[0], goods[0], 3},
                {buyers[1], goods[1], -1},
                {buyers[0], goods[2], 150},
                {buyers[0], goods[3], 3},
                {buyers[1], new Goods("Cake", 80, Categories.FOOD), 1},
                {new Buyer("Greg", "+7-956-332-87-96", 37), goods[4], 2},
        };

        Goods.setRandomDiscount(Categories.FOOD);

        int capacity = 0;
        int i = 0;
        while (capacity != orders.length - 1 || i != info.length) {
            try {
                orders[capacity] = buy((Buyer) info[i][0], (Goods) info[i][1], (int) info[i][2]);
                capacity++;
            } catch (Order.ProductException e) {
                e.printStackTrace();
            } catch (Order.AmountException e) {
                orders[capacity++] = buy((Buyer) info[i][0], (Goods) info[i][1], 1);
            } catch (Order.CustomerException | Order.TooMuchSaleException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.printf("Orders in shop: %d.%n", capacity);
            }
            ++i;
        }
    }
}

