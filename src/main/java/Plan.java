import java.util.ArrayList;
import java.util.Optional;

/**
 * Класс Plan представляет с собой тарифный план оператора сотовой связи, которая
 * хранит такие данные, как уникальный идентификатор, название, срок действия и цену.
 */
public class Plan implements Cloneable {
    /**
     * Уникальный идентификатор тарифного плана.
     */
    private String id;
    /**
     * Название тарифного плана.
     */
    private String name;
    /**
     * Срок действия тарифного плана.
     */
    private double duration;
    /**
     * Цена тарифного плана.
     */
    private double price;
    /**
     * Единицы тарифного плана.
     */
    private final ArrayList<Unit> units;

    /**
     * Инициализирует новый экземрляр оператора с изначальными данными.
     */
    Plan() {
        this.id = "";
        this.name = "";
        this.duration = 0.0;
        this.price = 0.0;
        this.units = new ArrayList<>();
    }

    /**
     * Возвращает уникальный идентификатор тарифного плана.
     *
     * @return уникальный идентификатор тарифного плана
     */
    public String getId() {
        return this.id;
    }

    /**
     * Устанавливает уникальный идентификатор тарифного плана если она еще не была
     * установлена, в противном случае ничего не предпринимается.
     *
     * @param id уникальный идентификатор тарифного плана
     * @return true если идентификатор тарифного плана уже был установлен или false в
     * противном случае
     */
    boolean setId(String id) {
        if (id.equals("")) return false;
        else {
            this.id = id;
            return true;
        }
    }

    /**
     * Возвращает название тарифного плана.
     *
     * @return название тарифного плана
     */
    String getName() {
        return name;
    }

    /**
     * Устанавливает новое название тарифного плана вне зависимости от того, была
     * ли установлена название до этого или нет.
     *
     * @param name новое название тарифного плана
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает срок действия тарифного плана.
     *
     * @return срок действия тарифного плана
     */
    double getDuration() {
        return this.duration;
    }

    /**
     * Устанавливает новый срок действия тарифного плана вне зависимости от того.
     *
     * @param duration новый срок действия тарифного плана
     */
    void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Возвращает цену тарифного плана.
     *
     * @return цена тарифного плана
     */
    double getPrice() {
        return price;
    }

    /**
     * Устанавливает новое цену тарифного плана.
     *
     * @param price цена тарифного плана
     */
    void setPrice(double price) {
        this.price = price;
    }

    /**
     * Возвращает все единицы тарифного плана.
     *
     * @return все единицы тарифного плана
     */
    ArrayList<Unit> getAllUnits() {
        return this.units;
    }

    /**
     * Добавляет новую единицу к тарифному плану.
     *
     * @param unit единица которая будет добавлена
     * @return true при успешном добавлении или false если единица с
     * одноименным типом уже существует
     */
    boolean addNewUnit(Unit unit) {
        if (units.contains(unit)) return false;
        else return units.add(unit);
    }

    /**
     * Возвращает единицу тарифного плана по типу.
     *
     * @param type тип единицы
     * @return единица тарифного плана или null если ничего не найдено
     */
    Unit getUnitByType(Unit.Type type) {
        Optional<Unit> unit = units.stream().filter(u -> u.getType() == type).findFirst();
        return unit.orElse(null);
    }

    /**
     * Удаляет единицу тарифного плана по типу.
     *
     * @param unit единица которая будет удалена
     * @return true при успешном удалении или false в противном случае
     */
    boolean deleteUnit(Unit unit) {
        return units.remove(unit);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Plan)) return false;
        Plan p = (Plan) o;
        return this.id.equals(p.id);
    }

    @Override
    public Plan clone() {
        try {
            Plan clone = (Plan) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
