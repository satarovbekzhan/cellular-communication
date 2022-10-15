import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MainTest {

    private static final Operator operator = new Operator();

    @BeforeAll
    static void beforeAll() {
        URL url = MainTest.class.getClassLoader().getResource("data.json");
        if (url == null) fail("Data resource (data.json) cannot be resolved!");
        try {
            Object object = new JSONParser().parse(new FileReader(url.getPath()));
            JSONObject json = (JSONObject) object;
            for (Object p : (JSONArray) json.get("plans")) {
                Plan plan = new Plan();
                plan.setId((String) ((JSONObject) p).get("id"));
                plan.setName((String) ((JSONObject) p).get("name"));
                plan.setDuration((Double) ((JSONObject) p).get("duration"));
                plan.setPrice(((Double) ((JSONObject) p).get("price")));
                for (Object u : (JSONArray) ((JSONObject) p).get("items")) {
                    Unit.Type type = null;
                    switch ((String) ((JSONObject) u).get("class")) {
                        case "MIN":
                            type = Unit.Type.MIN;
                            break;
                        case "SMS":
                            type = Unit.Type.SMS;
                            break;
                        case "INT":
                            type = Unit.Type.INT;
                            break;
                    }
                    if (type == null) continue;
                    Unit unit = new Unit(type);
                    unit.setValue((Double) ((JSONObject) u).get("value"));
                    unit.setPrice((Double) ((JSONObject) u).get("price"));
                    plan.addNewUnit(unit);
                }
                operator.addNewPlan(plan);
            }
            for (Object c : (JSONArray) json.get("customers")) {
                Customer customer = new Customer();
                customer.setPhone((String) ((JSONObject) c).get("phone"));
                Plan plan = operator.getPlanById((String) ((JSONObject) c).get("plan"));
                Plan clone = plan.clone();
                operator.addCustomer(customer, clone);
            }
        } catch (ParseException e) {
            fail("File parsing exception!");
        } catch (FileNotFoundException e) {
            fail("File not found exception!");
        } catch (IOException e) {
            fail("File read (i/o) exception!");
        }
    }

    /**
     * Все данные с файла 'data.json' обработаны правильно.
     */
    @Test
    @DisplayName("All data from the 'data.json' file is parsed correctly (at BeforeAll stage).")
    @Order(0)
    void testZero() {
        // Number of a plans added equals to 3
        assertEquals(3, operator.getAllPlans().size());
        // Name of a plan with id '23423' is 'Standard'
        assertEquals("Standard", operator.getPlanById("23423").getName());
        // Name of a plan with id '32748' is 'Extended'
        assertEquals("Extended", operator.getPlanById("32748").getName());
        // Name of a plan with id '09342' is 'Unlimited'
        assertEquals("Unlimited", operator.getPlanById("09342").getName());
        // Plan 'Standard' has three units with names 'MIN', 'SMS' and 'INT', respectively
        Plan standart = operator.getPlanById("23423");
        assertNotNull(standart.getUnitByType(Unit.Type.MIN));
        assertNotNull(standart.getUnitByType(Unit.Type.SMS));
        assertNotNull(standart.getUnitByType(Unit.Type.INT));
    }

    /**
     * Общая численность клиентов сотового оператора равна 5.
     */
    @Test
    @DisplayName("The total number of clients of the mobile operator totals 5.")
    @Order(1)
    void testOne() {
        ArrayList<Customer> customers = operator.getAllCustomers();
        assertEquals(5, customers.size());
    }

    /**
     * Цена тарифного плана с наименьшей абонентской платой равна 50 сом.
     */
    @Test
    @DisplayName("The price of the tariff plan with the lowest subscription fee is 50 soms.")
    @Order(2)
    void testTwo() {
        ArrayList<Plan> plans = operator.getAllPlans();
        ArrayList<Plan> sorted = plans.stream().sorted(
                Comparator.comparing(Plan::getPrice)
        ).collect(Collectors.toCollection(ArrayList::new));
        assertEquals(50.00, sorted.get(0).getPrice());
    }

    /**
     * Количество тарифных планов стоимостью интернет трафика за 1MB меньше 3 сомов равна 2.
     */
    @Test
    @DisplayName("There is 2 tariff plans with the cost of Internet traffic less than 3 soms.")
    @Order(3)
    void testThree() {
        ArrayList<Plan> plans = operator.getAllPlans();
        ArrayList<Plan> filtered = plans.stream().filter(
                (p) -> p.getUnitByType(Unit.Type.INT).getPrice() < 3.00
        ).collect(Collectors.toCollection(ArrayList::new));
        assertEquals(2, filtered.size());
    }
}