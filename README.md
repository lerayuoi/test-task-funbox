# Web-приложение для простого учета посещенных ссылок


• Первый ресурс служит для передачи в сервис массива ссылок в POST-запросе. Временем их посещения считается время получения запроса сервисом.

• Второй ресурс служит для получения GET-запросом списка уникальных доменов, посещенных за переданный интервал времени.

• Поле status ответа служит для передачи любых возникающих при обработке запроса ошибок.

### Сборка и запуск проекта
- Установите переменную JAVA_HOME если это необходимо.
- В терминале выполните команды для сборки приложения:

    ``` ./gradlew clean build ```

- Выполните команду для запуска контейнера redis:

    ``` docker-compose up ```
    
- Проверить создание контейнера можно командой ``` docker ps ```.

- Запустите класс RunApplication.java.

Теперь приложение доступно по адресу http://localhost:8081.
Конфигурационные параметры можно задать в файле application.properties