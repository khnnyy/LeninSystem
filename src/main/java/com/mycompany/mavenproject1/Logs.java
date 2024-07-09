package com.mycompany.mavenproject1;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logs {

    private static JTable table;
    private static JPanel panel;
    private static JButton button;
    private static JFrame frame;

    public static void Logs() {
        initializeUI();
        frame.setVisible(true);
    }

    public static void initializeUI() {
        table = new JTable() {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(800, 300);
            }
        };
        panel = new JPanel(new BorderLayout());
        button = new JButton("Show Data");
        button.addActionListener(listener);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(button, BorderLayout.PAGE_END);
        frame = new JFrame("Logs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void showUI() {
        frame.setVisible(true);
    }

    static ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String uri = "mongodb+srv://khadeem:lenin_JO@clusterleninjo.divpuaq.mongodb.net/?retryWrites=true&w=majority&appName=ClusterLeninJO";
            try (com.mongodb.client.MongoClient mongoClient = MongoClients.create(uri)) {
                MongoDatabase database = mongoClient.getDatabase("LeninJobOrder");
                MongoCollection<Document> collection = database.getCollection("solutionsClient");

                String[] columnNames = {"ID", "Job Type", "Job Code", "Client Name", "Address", "Contact", "Concern", "Team Leader", "Transportation", "Date Issued", "Date Due"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                List<Document> jobList = collection.find().into(new ArrayList<>());
                System.out.println("Job list with an ArrayList:");
                for (Document job : jobList) {
                    System.out.println(job.toJson());
                    String id = job.getObjectId("_id").toString();
                    String jobType = job.getString("job_type");
                    String jobCode = job.getString("job_code");
                    String clientName = job.getString("client_name");
                    String address = job.getString("address");
                    String contact = job.getString("contact");
                    String concern = job.getString("concern");
                    String teamLeader = job.getString("team_leader");
                    String transportation = job.getString("transportation");
                    String dateIssued = job.getString("date_issued");
                    String dateDue = job.getString("date_due");

                    model.addRow(new Object[]{id, jobType, jobCode, clientName, address, contact, concern, teamLeader, transportation, dateIssued, dateDue});
                }

                table.setModel(model);

            } catch (Exception ex) {
                Logger.getLogger(Logs.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
}
