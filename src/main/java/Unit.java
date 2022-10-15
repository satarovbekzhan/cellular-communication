/**
 * Класс Unit представляет с собой единицу тарифного плана: минуты, сообщения, интернет.
 * Отвечает за их хранение и обработку.
 */
public class Unit {

    /**
     * Тип единицы тарифного плана.
     */
    private final Type type;
    /**
     * Общее количество единиц тарифного плана.
     */
    private double value;
    /**
     * Цена за единицу тарифного плана.
     */
    private double price;

    /**
     * Инициализирует новый экземрляр единицы тарифного плана с изначальными данными.
     *
     * @param type тип единицы тарифного плана
     */
    Unit(Type type) {
        this.type = type;
        this.value = 0.0;
        this.price = 0.0;
    }

    /**
    * Возвращает тип единицы тарифного плана.
     *
    * @return тип единицы тарифного плана
     */
    Type getType() {
        return this.type;
    }

    /**
    * Возвращает общее количество единиц тарифного плана.
     *
    * @return общее количество единиц тарифного плана
     */
    double getValue() {
        return this.value;
    }

    /**
    * Устанавливает новое значение количества единиц тарифного плана.
     *
    * @param value новое значение количества единиц тарифного плана
     */
    void setValue(double value) {
        this.value = value;
    }

    /**
    * Возвращает цену за единицу тарифного плана.
     *
    * @return цена за единицу тарифного плана
     */
    double getPrice() {
        return this.price;
    }

    /**
    * Устанавливает новую цену за единицу тарифного плана.
     *
    * @param price новая цена за единицу тарифного плана
     */
    void setPrice(double price) {
        this.price = price;
    }

    /**
     * Сокращает количество единиц за использованный объем трафика (минуты,
     * сообщения, мегабайты) и возвращает сумму денег, которая должна быть
     * вычтена у абонента сотового оператора.
     *
    * @param d объем трафика (минуты, сообщения, мегабайты)
    * @return сумму денег, которая должна быть вычтена у абонента
     */
    double reduce(double d) {
        if (this.value > 0) {
            if (d <= this.value) {
                this.value -= d;
                return 0.0;
            } else {
                double overflow = d - this.value;
                this.value = 0.0;
                return overflow * this.price;
            }
        } else {
            return d * this.price;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Unit)) return false;
        Unit u = (Unit) o;
        return this.type == u.type;
    }

    /**
     * Типы единиц тарифного плана. Минуты (MIN), сообщения (SMS) и интернет (INT).
     */
    enum Type {
        MIN,
        SMS,
        INT
    }
}
