import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;


/**
 * Класс Operator представляет с собой оператора сотовой связи, которая управляет всем
 * процессом коммуникации и имеет доступ ко всем тарифным планам.
 */
public class Operator {
    /**
     * Список доступных тарифных планов сотового оператора.
     */
    private final ArrayList<Plan> plans;
    private final HashMap<Customer, Plan> customers;

    /**
     * Инициализирует новый экземрляр оператора с изначальными данными.
     */
    Operator() {
        this.plans = new ArrayList<>();
        this.customers = new HashMap<>();
    }

    /**
     * Возвращает все доступные тарифные планы сотового оператора.
     *
     * @return все доступные тарифные планы сотового оператора
     */
    ArrayList<Plan> getAllPlans() {
        return this.plans;
    }

    /**
     * Добавляет новый тарифный план к сотовому оператору.
     *
     * @param plan тарифный план которая будет добавлена
     * @return true при успешном добавлении или false если тарифный план с
     * одноименным идентификатором уже существует
     */
    boolean addNewPlan(Plan plan) {
        if (plans.contains(plan)) return false;
        else return plans.add(plan);
    }

    /**
     * Возвращает тарифный план по идентификатору.
     *
     * @param id идентификатор по которому производиться поиск
     * @return тарифный план или null если ничего не найдено
     */
    Plan getPlanById(String id) {
        Optional<Plan> plan = plans
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return plan.orElse(null);
    }

    /**
     * Удаляет тарифный план сотового оператора.
     *
     * @param plan тарифный план которая будет удалена
     * @return true при успешном удалении или false в противном случае
     */
    boolean deletePlan(Plan plan) {
        return plans.remove(plan);
    }

    /**
     * Возвращает все абоненты сотового оператора.
     *
     * @return все абоненты сотового оператора
     */
    ArrayList<Customer> getAllCustomers() {
        return new ArrayList<>(this.customers.keySet());
    }

    /**
     * Добавляет нового абонента с указанным тарифным планом.
     *
     * @param customer абонент которую нужно добавить
     * @param plan     тарифный план нового абонента
     * @return true если абонент успешно добавлен или false если абонент с
     * с таким номером уже существует
     */
    boolean addCustomer(Customer customer, Plan plan) {
        if (customers.containsKey(customer)) return false;
        customers.put(customer, plan);
        return true;
    }

    /**
     * Возвращает абонента сотового оператора по номеру телефона.
     *
     * @param phone номеру телефона по которому производиться поиск
     * @return абонент или null если ничего не найдено
     */
    Customer getCustomerByPhone(String phone) {
        Optional<Customer> customer = getAllCustomers()
                .stream()
                .filter(c -> c.getPhone().equals(phone))
                .findFirst();
        return customer.orElse(null);
    }

    /**
    * Удаляет абонент сотового оператора.
    *
    * @param customer абонент который будет удален
    * @return true при успешном удалении или false в противном случае
    */
    boolean deleteCustomer(Customer customer) {
        customers.remove(customer);
        return customers.containsKey(customer);
    }
}
