package order;

public class Order{
    String firstName;
    String lastName;
    String address;
    int metroStation;
    String phone;
    int rentTime;
    String deliveryDate;
    String comment;
    String[] color;

    public Order(String firstName, String lastName, String address, int metroStation, String phone, int rentTime,
                 String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static Order getOrderWithBlackScooter(){
        return new Order("Уаассся","Пупкин","Усачева,3",4,"+7 800 355 35 35",
                4,"2022-09-29","Дверь мне запили", new String[]{"Black"});
    }
    public static Order getOrderWithGreyScooter(){
        return new Order("Уаассся","Пупкин","Усачева,3",4,"+7 800 355 35 35",
                4,"2022-09-29","Дверь мне запили", new String[]{"Grey"});
    }
    public static Order getOrderWithBothColors(){
        return new Order("Уаассся","Пупкин","Усачева,3",4,"+7 800 355 35 35",
                4,"2022-09-29","Дверь мне запили", new String[]{"Black, Grey"});
    }
    public static Order getOrderWithoutScooterColor(){
        return new Order("Уаассся","Пупкин","Усачева,3",4,"+7 800 355 35 35",
                4,"2022-09-29","Дверь мне запили",new  String[]{});
    }
}

