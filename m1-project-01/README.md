# Азбука Морзе

// USE Case 1: Кодирование файла

// USE Case 2: Декодирование файла

1. Core - бизнес логика, константы 
   [Alphabet.java](src/main/java/com/javarush/morse/core/Alphabet.java)  
   [MorseCoder.java](src/main/java/com/javarush/morse/core/MorseCoder.java)  

2. Model (Domain) - модель данных 
   [ProcessingResult.java](src/main/java/com/javarush/morse/model/ProcessingResult.java)  
3. Service - работа с файлами, валидация 
   [ValidationService.java](src/main/java/com/javarush/morse/service/ValidationService.java)  
   [FileService.java](src/main/java/com/javarush/morse/service/FileService.java)  
4. Exception - кастомные исключения для нашей логики 
   [MorseException.java](src/main/java/com/javarush/morse/exception/MorseException.java)  
5. Ресурсы - настройки 
   [config.properties](src/main/resources/config.properties)  

// Тесты - для покрытия логики 