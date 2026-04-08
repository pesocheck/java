package javalab1;

/**
 * Точка входа в приложение.
 *
 * @author Maksim
 */
public class JavaLab1 {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
