import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Создаем главное окно (JFrame)
        JFrame frame = new JFrame("Вариант 10");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Создаем текстовую метку (JLabel)
        JLabel label = new JLabel("Hello, world!", SwingConstants.CENTER);
        label.setBounds(250, 250, 120, 30);
        frame.add(label);
        frame.setVisible(true); // Делаем окно видимым


        int[] dx = {2}; // Горизонтальное смещение
        int[] dy = {2}; // Вертикальное смещение
        Random random = new Random(); // Создаем объект Random для генерации случайных чисел

        // Шрифты
        Font[] fonts = {
                new Font("Arial", Font.PLAIN, 14),
                new Font("TimesRoman", Font.BOLD, 14),
                new Font("Courier", Font.ITALIC, 14)
        };

        Timer timer = new Timer(10, e -> { // Таймер для получения координат метки
            int x = label.getX();
            int y = label.getY();

            // Обновляем, добавляя смещение
            x += dx[0];
            y += dy[0];

            Insets insets = frame.getInsets(); // Размер рамок окна
            int contentWidth = frame.getWidth() - insets.left - insets.right;
            int contentHeight = frame.getHeight() - insets.top - insets.bottom;

            // Проверка на столкновение с границами
            if (x <= 0 || x + label.getWidth() >= contentWidth) {
                dx[0] = -dx[0]; // Меняем направление движения по горизонтали
                changeTextCaseAndFont(label, random, fonts); // Меняем регистр и шрифт
            }
            if (y <= 0 || y + label.getHeight() >= contentHeight) {
                dy[0] = -dy[0]; // Меняем направление движения по вертикали
                changeTextCaseAndFont(label, random, fonts); // Меняем регистр и шрифт
            }
            // Новые координаты
            label.setLocation(x, y);
        });

        timer.start();
    }

    // Функция для изменения регистра символов и шрифта
    static void changeTextCaseAndFont(JLabel label, Random random, Font[] fonts) {
        String text = label.getText();
        StringBuilder newText = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (random.nextBoolean()) {
                newText.append(Character.toUpperCase(c));
            } else {
                newText.append(Character.toLowerCase(c));
            }
        }

        label.setText(newText.toString());
        label.setFont(fonts[random.nextInt(fonts.length)]);
    }
}
