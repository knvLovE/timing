package Windows;

import javax.swing.*;
import java.awt.*;

public class MainWindow implements Window  {
    private static JFrame jFrame = new JFrame();
    private static TextArea textArea = new TextArea();
    private static Window mainWindow = new MainWindow();
    static {
        jFrame.setSize(640,400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textArea.setEditable(false);
        jFrame.add(textArea);
        jFrame.setVisible(true);
    }

    private MainWindow (){
    }

    public static Window getInstance() {
        return mainWindow;
    }

    @Override
    public void println(String message) {
        if (jFrame != null && textArea != null){
            textArea.append(message);
            textArea.append("\n\r");
            jFrame.add(textArea);
        }
    }

}
