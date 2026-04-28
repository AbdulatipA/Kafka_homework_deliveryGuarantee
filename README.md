# Kafka_homework_deliveryGuarantee

## Задача
Создать 2 сервиса для обмена сообщениями через брокер Кафки
бизнес логика на ваше усмотрение. Семантика 1 или более
Запустить все на 3 серверах

## Требования к заданию
- 1 сервер - 1 сервис
- 2 сервер - 2 сервис
- 3 сервер - кафка брокер


### Сервисы

>[order_service (consumer)](https://github.com/AbdulatipA/Kafka_homework_deliveryGuarantee/tree/master/order_service/src/main/java/org/example/order_service)

>[person_service (producer)](https://github.com/AbdulatipA/Kafka_homework_deliveryGuarantee/tree/master/person_service/src/main/java/org/example/person_service)

### комментарий
1) person_service создает заказ
2) заказ поступает в топик order-topic
3) order_service читает заказ и сохраняет у себя в бд для дальнейшей обработки
