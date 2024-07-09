//package com.mycompany.mavenproject1;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Document(collection = "solutionsClient") // Collection name in MongoDB
//public class SolutionsClient {
//
//    @Id
//    private String id; // MongoDB document ID field
//
//    private String job_type;
//    private String job_code;
//    private String client_name;
//    private String address;
//    private String contact;
//    private String concern;
//    private String leader;
//    private String transportation;
//    private String date_issued;
//    private String date_due;
//    private String status;
//
//    // Constructors, getters, and setters
//
//    public SolutionsClient() {
//    }
//
//    public SolutionsClient(String job_type, String job_code, String client_name, String address, String contact, String concern, String leader, String transportation, String date_issued, String date_due, String status) {
//        this.job_type = job_type;
//        this.job_code = job_code;
//        this.client_name = client_name;
//        this.address = address;
//        this.contact = contact;
//        this.concern = concern;
//        this.leader = leader;
//        this.transportation = transportation;
//        this.date_issued = date_issued;
//        this.date_due = date_due;
//        this.status = status;
//    }
//
//    // Getters and setters for all fields
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getJob_type() {
//        return job_type;
//    }
//
//    public void setJob_type(String job_type) {
//        this.job_type = job_type;
//    }
//
//    public String getJob_code() {
//        return job_code;
//    }
//
//    public void setJob_code(String job_code) {
//        this.job_code = job_code;
//    }
//
//    public String getClient_name() {
//        return client_name;
//    }
//
//    public void setClient_name(String client_name) {
//        this.client_name = client_name;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getContact() {
//        return contact;
//    }
//
//    public void setContact(String contact) {
//        this.contact = contact;
//    }
//
//    public String getConcern() {
//        return concern;
//    }
//
//    public void setConcern(String concern) {
//        this.concern = concern;
//    }
//
//    public String getLeader() {
//        return leader;
//    }
//
//    public void setLeader(String leader) {
//        this.leader = leader;
//    }
//
//    public String getTransportation() {
//        return transportation;
//    }
//
//    public void setTransportation(String transportation) {
//        this.transportation = transportation;
//    }
//
//    public String getDate_issued() {
//        return date_issued;
//    }
//
//    public void setDate_issued(String date_issued) {
//        this.date_issued = date_issued;
//    }
//
//    public String getDate_due() {
//        return date_due;
//    }
//
//    public void setDate_due(String date_due) {
//        this.date_due = date_due;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    // toString() method for debugging
//
//    @Override
//    public String toString() {
//        return "SolutionsClient{" +
//                "id='" + id + '\'' +
//                ", job_type='" + job_type + '\'' +
//                ", job_code='" + job_code + '\'' +
//                ", client_name='" + client_name + '\'' +
//                ", address='" + address + '\'' +
//                ", contact='" + contact + '\'' +
//                ", concern='" + concern + '\'' +
//                ", leader='" + leader + '\'' +
//                ", transportation='" + transportation + '\'' +
//                ", date_issued='" + date_issued + '\'' +
//                ", date_due='" + date_due + '\'' +
//                ", status='" + status + '\'' +
//                '}';
//    }
//}
