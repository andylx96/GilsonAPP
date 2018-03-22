///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ViewRunDataAPI;
//
//
//import java.awt.Color;
//import javax.swing.JPanel;
//import javax.swing.JFrame;
//import static javax.swing.JFrame.EXIT_ON_CLOSE;
//import javax.swing.JLabel;
//
///**
// *View class that calls on the constructor to create a JFrame and JPanel for
// * basic data view.
// * @author r4kia
// */
//public class ViewRunDataView {
//
//
//    private static ViewRunDataModel vm;
//    private  static ViewRunDataCtrl vc;
//
//    ViewRunDataView(ViewRunDataModel vm){
//        ViewRunDataView.vm = vm;
//        JFrame f= new JFrame("ViewBasicRun");
//        JLabel info = new JLabel("Current Run Data: \n");
//        JLabel Time = new JLabel("Run Time: 2:40 \n");
//        JLabel Tspeed = new JLabel("Top Speed: " + 0 + "\n");
//        JLabel Aspeed = new JLabel("Average Speed: " + 0 + "\n");
//        JLabel Taccel = new JLabel("Top Acceleration: " + 0 + "\n");
//        JLabel Aaccel = new JLabel("Average Acceleration: " + 0 + "\n");
//        JLabel AirTime = new JLabel("Air Time: " + 0 + "\n");
//        JLabel Vertical = new JLabel("Max Vertical: "+0 + "\n");
//        JLabel Fall = new JLabel("Fall Count: "+ 0 + "\n");
//        JPanel mPanel = new JPanel();
//        mPanel.setBounds(40,80,200,200);
//        mPanel.setBackground(Color.lightGray);
//        f.add(mPanel);
//        mPanel.add(info);
//        mPanel.add(Time);
//        mPanel.add(Tspeed);
//        mPanel.add(Aspeed);
//        mPanel.add(Taccel);
//        mPanel.add(Aaccel);
//        mPanel.add(AirTime);
//        mPanel.add(Vertical);
//        mPanel.add(Fall);
//        f.setSize(500,500);
//        f.setVisible(true);
//        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//
//    /**
//     * This open a new JFrame calling the constructor
//     * @param args this is the main class for ViewRun Data Viewer class
//     */
//    public static void main(String[] args) {
//
//        ViewRunDataView viewRunDataView;
//        viewRunDataView = new ViewRunDataView(vm);
//
//    }
//}
