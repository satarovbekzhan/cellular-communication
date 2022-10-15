/**
 * Класс Customer представляет с собой абонента сотовой связи, которая хранит и
 * управляет лишь со своим номером.
 */
public class Customer {
    /**
     * Номер телефона абонента сотового оператора.
     */
    private String phone;


    /**
     * Инициализирует новый экземпляр абонента.
     */
    Customer() {
        this.phone = "";
    }

    /**
    * Возвращант номер телефона абонента.
     *
    * @return номер телефона абонента
     */
    String getPhone() {
        return phone;
    }

    /**
    * Устанавливает новый номер телефона абонента.
     *
    * @param phone новый номер телефона абонента
     */
    void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Customer)) return false;
        Customer c = (Customer) o;
        return this.phone.equals(c.phone);
    }
}