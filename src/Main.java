import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String uData = getUserData();
            writeToFile(uData);
            System.out.println("Выполнена запись в файл");
        } catch (checkInputException e) {
            System.out.println(e.getMessage());
        } catch (phoneException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    static String getUserData() throws checkInputException, phoneException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилия, имя, отчество, номертелефона через пробел:");
        String uData = scanner.nextLine();
        String[] userData = uData.split(" ");
        if (userData.length < 4) {
            throw new checkInputException("Количество введенных данных меньше 4");
        } else if (userData.length > 4) {
            throw new checkInputException("Количество введенных данных больше 4");
        }

        try {
            Long phone = Long.valueOf(userData[3]);
        } catch (Exception e) {
            throw new phoneException("Введен некорректный номер телефона");
        }
        return uData;
    }

    static void writeToFile(String uData) throws FileNotFoundException, IOException {
        String[] userData = uData.split(" ");
        String filename = userData[0] + ".txt";

        try (FileWriter fileWriter = new FileWriter(filename, true)) {
            fileWriter.write(uData + "\n");
        }
    }
}

class checkInputException extends Exception {
    checkInputException(String message) {
        super(message);
    }
}

class phoneException extends IOException {
    phoneException(String message) {
        super(message);
    }
}
