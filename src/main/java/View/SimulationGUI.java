package View;

import Controller.ShopManager;

import javax.swing.*;
import java.awt.*;

public class SimulationGUI extends JFrame {

    private boolean simulationBegin = false;

    private JEditorPane jEditorPane1;

    private JLabel title;   //the title part
    private JLabel simInputs;
    private JLabel queues;
    private JLabel currentTime;
    private JLabel remainingClients;
    private JTextField remainingClientsText;
    //input part
    private JLabel noOfClientsLabel;    //clients
    private JTextField noOfClientsText;
    private JLabel noOfQueuesLabel;     //queues
    private JTextField noOfQueuesText;
    private JLabel simulationTimeLabel; //simulation time
    private JTextField simulationTimeText;
    private JLabel arrivalIntervalLabel;//arrival interval
    private JTextField arrivalIntervalText;
    private JLabel serviceIntervalLabel;//service interval
    private JTextField serviceIntervalText;
    //output part
    private JLabel[] queuesLabels;
    private JTextField[] queuesTexts;
    //startSimulation button
    private JButton startSimulation;

    private final Font unused = new Font("SansSerif", Font.ITALIC, 20);
    private final Font defaultFont = new Font("SansSerif", Font.PLAIN, 20);

    public SimulationGUI() {
        initComponents();
    }

    private void initQueues() {
        queuesLabels = new JLabel[5];
        queuesTexts = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            queuesLabels[i] = new JLabel("Q" + (i + 1));    //initialize labels
            queuesLabels[i].setFont(defaultFont);

            queuesTexts[i] = new JTextField("Unavailable");  //initialize texts
            queuesTexts[i].setFont(unused);
            queuesTexts[i].setForeground(Color.RED);
        }
    }

    private void setFontsAndTexts() {
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("SHOP SIMULATION");
        title.setFont(new Font("SansSerif", Font.ITALIC, 30));

        currentTime.setText("Time: " + ShopManager.currentTime + " s");
        currentTime.setFont(new Font("SansSerif", Font.ITALIC, 30));
        currentTime.setHorizontalAlignment(SwingConstants.CENTER);

        remainingClients.setFont(new Font("SansSerif", Font.ITALIC, 25));
        remainingClients.setHorizontalAlignment(SwingConstants.CENTER);

        remainingClientsText.setFont(defaultFont);
        remainingClientsText.setHorizontalAlignment(SwingConstants.CENTER);

        simInputs.setText("Input Data:");
        simInputs.setFont(new Font("SansSerif", Font.ITALIC, 25));

        queues.setText("Queues:");
        queues.setFont(new Font("SansSerif", Font.ITALIC, 25));

        serviceIntervalLabel.setText("Service Interval:");
        serviceIntervalLabel.setFont(defaultFont);
        noOfClientsLabel.setText("No. of Clients:");
        noOfClientsLabel.setFont(defaultFont);
        noOfQueuesLabel.setText("No. of Queues:");
        noOfQueuesLabel.setFont(defaultFont);
        simulationTimeLabel.setText("Simulation Time:");
        simulationTimeLabel.setFont(defaultFont);
        arrivalIntervalLabel.setText("Arrival Interval:");
        arrivalIntervalLabel.setFont(defaultFont);
        noOfQueuesText.setText("2");
        noOfQueuesText.setFont(defaultFont);
        noOfClientsText.setText("10");
        noOfClientsText.setFont(defaultFont);
        simulationTimeText.setText("20");
        simulationTimeText.setFont(defaultFont);
        arrivalIntervalText.setText("1 3");
        arrivalIntervalText.setFont(defaultFont);
        serviceIntervalText.setText("2 4");
        serviceIntervalText.setFont(defaultFont);
    }

    private void initComponents() {

        JScrollPane jScrollPane1 = new JScrollPane();
        currentTime = new JLabel();
        remainingClients = new JLabel("Remaining Clients");
        remainingClientsText = new JTextField("No clients");
        jEditorPane1 = new JEditorPane();
        startSimulation = new JButton();
        title = new JLabel();
        serviceIntervalLabel = new JLabel();
        noOfClientsLabel = new JLabel();
        noOfQueuesLabel = new JLabel();
        simulationTimeLabel = new JLabel();
        arrivalIntervalLabel = new JLabel();
        noOfQueuesText = new JTextField();
        noOfClientsText = new JTextField();
        simulationTimeText = new JTextField();
        arrivalIntervalText = new JTextField();
        serviceIntervalText = new JTextField();
        simInputs = new JLabel();
        queues = new JLabel();
        this.initQueues();

        this.setVisible(true);
        this.setTitle("My Shop");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setBounds(300, 100, 900, 600);

        jScrollPane1.setViewportView(jEditorPane1);

        startSimulation.setText("Start Simulation");
        startSimulation.setFont(defaultFont);

        startSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSimulationActionPerformed(evt);
            }
        });

        this.setFontsAndTexts();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        getContentPane().setBackground(new Color(238, 238, 238));

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(title,400, 400, 400)
                .addComponent(currentTime,400, 400, 400))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(simInputs, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(noOfClientsLabel, 150, 150, 150)
                            .addComponent(noOfQueuesLabel,150, 150, 150)
                            .addComponent(simulationTimeLabel, 150, 150, 150)
                            .addComponent(arrivalIntervalLabel,150, 150, 150)
                            .addComponent(serviceIntervalLabel,150, 150, 150))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(noOfClientsText,    20, 70, 100)
                            .addComponent(noOfQueuesText,     20, 70, 100)
                            .addComponent(simulationTimeText, 20, 70, 100)
                            .addComponent(arrivalIntervalText,20, 70, 100)
                            .addComponent(serviceIntervalText,20, 70, 100))))
                .addGap(200, 200, 200)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(queues, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(queuesLabels[0], 50, 50, 50)
                            .addComponent(queuesLabels[1], 50, 50, 50)
                            .addComponent(queuesLabels[2], 50, 50, 50)
                            .addComponent(queuesLabels[3], 50, 50, 50)
                            .addComponent(queuesLabels[4], 50, 50, 50))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(queuesTexts[0], 400, 400,800)
                            .addComponent(queuesTexts[1], 400, 400,800)
                            .addComponent(queuesTexts[2], 400, 400,800)
                            .addComponent(queuesTexts[3], 400, 400,800)
                            .addComponent(queuesTexts[4], 400, 400,800))))
                .addGap(50, 50, 50))
            .addComponent(remainingClients,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(remainingClientsText)
                    .addGap(50, 50, 50))
            .addComponent(startSimulation)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(title, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(20, 20, 20)
            .addComponent(currentTime, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(20, 20, 20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(simInputs)
                .addComponent(queues))
            .addGap(20, 20, 20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(noOfClientsLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(noOfClientsText, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(queuesLabels[0], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(queuesTexts[0], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(noOfQueuesLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(noOfQueuesText, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(queuesLabels[1], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(queuesTexts[1], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(simulationTimeLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(simulationTimeText, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(queuesLabels[2], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(queuesTexts[2], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(arrivalIntervalLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(arrivalIntervalText, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(queuesLabels[3], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(queuesTexts[3], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(serviceIntervalLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(serviceIntervalText, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(queuesLabels[4], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(queuesTexts[4], GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))

            .addGap(30, 30, 30)
            .addComponent(remainingClients)
            .addGap(20, 20, 20)
            .addComponent(remainingClientsText)
            .addGap(30, 30, 30)
            .addComponent(startSimulation)
            .addGap(20, 20, 20)
        );

        pack();
    }

    private void startSimulationActionPerformed(java.awt.event.ActionEvent evt) {
        //first, we want to read all the data.
        try {
            if(simulationBegin == false) {
                this.readInputTextFields();
                ShopManager.generateClients();
                simulationBegin = true;
            }
        }
        catch(Exception e){
            System.out.println("The input is incorrect. Please enter a valid input.");
            return;
        }

        try {
            ShopManager.mainThread.start();
        }
        catch(Exception e) {
            System.out.println("The thread was already run. Please restart the program!");
            return;
        }
    }

    private void readInputTextFields() throws Exception{

        if(startSimulation.getText().equals("Simulation Finished"))
            System.exit(0);

        ShopManager.simulationDuration = Integer.parseInt(simulationTimeText.getText());
        ShopManager.numberOfClients = Integer.parseInt(noOfClientsText.getText());
        ShopManager.numberOfQueues = Integer.parseInt(noOfQueuesText.getText());
        if(ShopManager.simulationDuration < 0 || ShopManager.numberOfClients < 0 || ShopManager.numberOfQueues < 0)
            throw new Exception();
        if(ShopManager.numberOfQueues > 5)
            System.out.println("The maximum number of queues accepted by the User Interface is 5. The simulation will run, but you will not be able to see the real time queues.");

        if(ShopManager.numberOfClients > 30)
            System.out.println("The maximum number of clients accepted by the User Interface is 30. The simulation will run, but you will not be able to see the real time queues.");

        this.readInterval(arrivalIntervalText.getText(),'a');
        this.readInterval(serviceIntervalText.getText(),'s');
    }

    private void readInterval(String s, char mode) throws Exception{
        int i = 0;
        for (String val: s.split(" ", 2)) {
            if(i == 0) {
                if (mode == 'a')
                    ShopManager.minArrivalTime = Integer.parseInt(val);
                if (mode == 's')
                    ShopManager.minProcessingTime = Integer.parseInt(val);
            }
            else {
                if (i == 1) {
                    if (mode == 'a')
                        ShopManager.maxArrivalTime = Integer.parseInt(val);
                    if (mode == 's')
                        ShopManager.maxProcessingTime = Integer.parseInt(val);
                }
                else break;
            }
            i++;
        }
        if(ShopManager.maxProcessingTime < ShopManager.minProcessingTime || ShopManager.maxArrivalTime < ShopManager.minArrivalTime || ShopManager.maxProcessingTime < 0 || ShopManager.minArrivalTime < 0 || ShopManager.maxArrivalTime < 0 || ShopManager. minProcessingTime < 0)
            throw new Exception();
    }

    public void setButtonToFinished(){
        startSimulation.setText("Simulation Finished");
    }

    public void uploadGUIstatus(int index, String queueStatus) {
        this.queuesTexts[index].setForeground(Color.GREEN);
        this.queuesTexts[index].setText(queueStatus);
    }

    public void updateRemainingQueueText(String s) {
        this.remainingClientsText.setText(s);
    }

    public void updateCurrentTime() {
        this.currentTime.setText("Time: " + ShopManager.currentTime + " s");
    }
}

