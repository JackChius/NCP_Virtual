import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class SliderPanel extends JPanel
{
    private JPanel sliderPanel;
    private JTextField textField;
    private ChangeListener listener;

    public SliderPanel()
    {
//        super();
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridBagLayout());

        // common listener for all sliders
        listener = event -> {
            // update text field when the slider value changes
            JSlider source = (JSlider) event.getSource();
            System.out.println(source.getValue());
            String item = source.getName();
            switch( item ) {
                case "BED_COUNT" :
                    Constants.BED_COUNT = source.getValue();
                    break;
                case "HOSPITAL_RECEIVE_TIME":
                    Constants.HOSPITAL_RECEIVE_TIME = source.getValue();
                    break;
                case "SHADOW_TIME":
                    Constants.SHADOW_TIME = source.getValue();
                    break;
                case "BROAD_RATE":
                    float rate = (float) source.getValue()/10;
                    System.out.println(rate);
                    Constants.BROAD_RATE = rate ;
                case "FATALITY_RATE":
                    float fRate = (float) source.getValue()/100;
                    System.out.println(fRate);
                    Constants.FATALITY_RATE = fRate;
            }
//            textField.setText("" + source.getValue());
        };

        // add a plain slider

        JSlider slider = new JSlider(1000,5000);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(1000);
        slider.setMinorTickSpacing(500);
        slider.setValue(1000);
        addSlider(slider, "床位数","BED_COUNT");

        // add a slider with numeric labels

        slider = new JSlider(1,100);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setValue(10);
        addSlider(slider, "医院响应时间","HOSPITAL_RECEIVE_TIME");

        slider = new JSlider(10,500);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(50);
        slider.setValue(140);
        addSlider(slider, "潜伏时间","SHADOW_TIME");

        slider = new JSlider(1,10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(2);
        slider.setMinorTickSpacing(1);
        slider.setValue(8);
        addSlider(slider, "病毒传播率","BROAD_RATE");

        slider = new JSlider(1,100);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setValue(50);
        addSlider(slider, "病死率","FATALITY_RATE");

        JButton lowPer = new JButton("低人流量");
        addBtn(lowPer,"人群流动最慢速率","lowU");

        JButton highPer = new JButton("高人流量");
        addBtn(highPer,"人群流动最快速率(默认)","highU");

        JButton middPer = new JButton("中等人流量");
        addBtn(middPer,"人群流动中等速率","middU");

        // add the text field that displays the slider value

//        textField = new JTextField();
        add(sliderPanel, BorderLayout.CENTER);
//        add(textField, BorderLayout.SOUTH);
//        pack();
    }

    /**
     * Adds a slider to the slider panel and hooks up the listener
     * @param s the slider
     * @param description the slider description
     */
    public void addSlider(JSlider s, String description, String tag)
    {
        s.addChangeListener(listener);
        s.setName(tag);
        JPanel panel = new JPanel();
        panel.add(s);
        panel.add(new JLabel(description));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = sliderPanel.getComponentCount();
        gbc.anchor = GridBagConstraints.WEST;
        sliderPanel.add(panel, gbc);
    }
    public void addBtn(JButton btn, String instruct, String tag)
    {
//        s.addChangeListener(listener);
        btn.setName(tag);
        JPanel bPanel = new JPanel();
        bPanel.add(btn);
        JLabel instrutLabel = new JLabel(instruct);
        bPanel.add(instrutLabel);
        bPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = sliderPanel.getComponentCount();
        gbc.anchor = GridBagConstraints.WEST;
        sliderPanel.add(bPanel, gbc);
        switch (tag){
            case "lowU":
                btn.addActionListener(event ->
                    Constants.u = -0.99f ); break;
            case "highU":
                btn.addActionListener(event ->
                        Constants.u = 0.99f ); break;
            case "middU":
                btn.addActionListener(event ->
                        Constants.u = 0.01f ); break;
        }
    }
}