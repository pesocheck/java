package javalab1;

public class JavaLab1 {

    public static void main(String[] args) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                System.setOut(new java.io.PrintStream(System.out, true, "CP866"));
            } else {
                System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}