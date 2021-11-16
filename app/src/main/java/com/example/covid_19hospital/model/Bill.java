package com.example.covid_19hospital.model;

public class Bill {

    private int id;

    private String name;

    private int age;

    private String address;

    private String contact;

    private String email;

    private String nidNo;

    private String services;

    private String paymentStatus;

    private long doctorFee;

    private long covidTestFee;

    private long pathologyBill;

    private long cavinRent;

    private long medicineAndOthers;

    private long totalBill;

    private long paidAmount;


    private long dueAmount;

    private String paymentMethod;

    private String transactionId;

    private String year;

    private String month;

    public Bill() {
    }

    public Bill(int id, String name, int age, String address, String contact, String email, String nidNo, String services, String paymentStatus, long doctorFee, long covidTestFee, long pathologyBill, long cavinRent, long medicineAndOthers, long totalBill, long paidAmount, long dueAmount, String paymentMethod, String transactionId, String year, String month) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.nidNo = nidNo;
        this.services = services;
        this.paymentStatus = paymentStatus;
        this.doctorFee = doctorFee;
        this.covidTestFee = covidTestFee;
        this.pathologyBill = pathologyBill;
        this.cavinRent = cavinRent;
        this.medicineAndOthers = medicineAndOthers;
        this.totalBill = totalBill;
        this.paidAmount = paidAmount;
        this.dueAmount = dueAmount;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.year = year;
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNidNo() {
        return nidNo;
    }

    public void setNidNo(String nidNo) {
        this.nidNo = nidNo;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public long getDoctorFee() {
        return doctorFee;
    }

    public void setDoctorFee(long doctorFee) {
        this.doctorFee = doctorFee;
    }

    public long getCovidTestFee() {
        return covidTestFee;
    }

    public void setCovidTestFee(long covidTestFee) {
        this.covidTestFee = covidTestFee;
    }

    public long getPathologyBill() {
        return pathologyBill;
    }

    public void setPathologyBill(long pathologyBill) {
        this.pathologyBill = pathologyBill;
    }

    public long getCavinRent() {
        return cavinRent;
    }

    public void setCavinRent(long cavinRent) {
        this.cavinRent = cavinRent;
    }

    public long getMedicineAndOthers() {
        return medicineAndOthers;
    }

    public void setMedicineAndOthers(long medicineAndOthers) {
        this.medicineAndOthers = medicineAndOthers;
    }

    public long getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(long totalBill) {
        this.totalBill = totalBill;
    }

    public long getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(long paidAmount) {
        this.paidAmount = paidAmount;
    }

    public long getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(long dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
