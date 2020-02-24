import javax.swing.*;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * 模拟程序主入口
 *
 * @author
 * @comment GinRyan
 */
public class Main {

    public static void main(String[] args) {
        initHospital();
        initPanel();
        initInfected();
    }

    /**
     * 初始化画布
     */
    private static void initPanel() {
        MyPanel p = new MyPanel();
        Thread panelThread = new Thread(p);
        JFrame frame = new JFrame();
        frame.add(p);
        // 添加功能面板
        SliderPanel cp = new SliderPanel();
        cp.setBackground( new Color(0, 0, 0, 210) );
        frame.add(cp, BorderLayout.EAST);
//        JPanel conPanel = new JPanel();
//        conPanel.setLayout(new GridLayout(8,8));
//        conPanel.setBackground( new Color(0, 0, 0, 210) );
//        JButton xButton = new JButton("低人流量");
//        conPanel.add(xButton);
//        xButton.addActionListener(event ->
//                Constants.u = -0.99f   );
//
//        JButton xhgButton = new JButton("高人流量");
//        conPanel.add(xhgButton);
//        xhgButton.addActionListener(event ->
//                Constants.u = 0.99f   );
//
//        JSlider receiveTimeBtn = new JSlider(1,100);
//        conPanel.add(receiveTimeBtn);
//
//        frame.add(conPanel, BorderLayout.EAST);

        frame.setSize(Constants.CITY_WIDTH + hospitalWidth + 505, Constants.CITY_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("瘟疫传播模拟");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelThread.start();//开启画布线程，即世界线程，接着看代码的下一站可以转MyPanel.java
    }

    private static int hospitalWidth;

    /**
     * 初始化医院参数
     */
    private static void initHospital() {
        hospitalWidth = Hospital.getInstance().getWidth();
    }

    /**
     * 初始化初始感染者
     */
    private static void initInfected() {
        List<Person> people = PersonPool.getInstance().getPersonList();//获取所有的市民
        for (int i = 0; i < Constants.ORIGINAL_COUNT; i++) {
            Person person;
            do {
                person = people.get(new Random().nextInt(people.size() - 1));//随机挑选一个市民
            } while (person.isInfected());//如果该市民已经被感染，重新挑选
            person.beInfected();//让这个幸运的市民成为感染者
        }
    }

}
