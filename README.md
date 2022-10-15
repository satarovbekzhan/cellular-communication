# Система мобильной связи на Java

Работа выполнена в рамках курса "Разработка Объектно Ориентированных Систем".

==Лабораторная работа № 3-4.== Разработка программ с использованием принципа единственной обязанности (SRP).

==Вариант А. Задача **12.== Мобильная связь. Определить иерархию тарифов мобильной  компании. Создать список тарифов компании. Подсчитать общую численность клиентов. Провести сортировку тарифов на основе размера абонентской платы. Найти тариф в компании, соответствующий заданному диапазону параметров.

### Диаграмма классов

[<img src="diagram.png" width="100%" alt="Диаграмма классов системы мобильной связи"/>](diagram.png)

### Запуск и проверка

Для удобства просмотра исходного кода используйте [`VS Code Online`](https://vscode.dev/https://github.com/satarovbekzhan/cellular-communication). Возможно потребуется вход в аккаунт.

Для проверки работоспособности каждого класса специально написаны тесты. Чтобы запустить тесты, сперва убедитесь, что у вас установлена минимальная требуемая версия Java:

```commandline
$ java --version
```

В данном проекте использована версия Java 11:

```ignorelang
java 11.0.11 2021-04-20 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.11+9-LTS-194)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.11+9-LTS-194, mixed mode)
```

Затем перейдите в корень проекта и запустите команду ниже:

```commandline
$ ./gradlew clean build test
```

Все четыре теста успешно прошли:

```ignorelang
> Task :test

MainTest > All data from the 'data.json' file is parsed correctly (at BeforeAll stage). PASSED

MainTest > The total number of clients of the mobile operator totals 5. PASSED

MainTest > The price of the tariff plan with the lowest subscription fee is 50 soms. PASSED

MainTest > There is 2 tariff plans with the cost of Internet traffic less than 3 soms. PASSED

BUILD SUCCESSFUL in 1s
6 actionable tasks: 6 executed
```
