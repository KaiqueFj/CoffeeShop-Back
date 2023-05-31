package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {
    int orderId;
    String orderDescription;
    LocalDate date;
    float totalAmount;
    Cliente customer;
    NotaFiscal nf;
    ArrayList<ProdutoVendido> items;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Cliente getCustomer() {
        return customer;
    }

    public void setCustomer(Cliente customer) {
        this.customer = customer;
    }

    public NotaFiscal getNf() {
        return nf;
    }

    public void setNf(NotaFiscal nf) {
        this.nf = nf;
    }

    public ArrayList<ProdutoVendido> getItems() {
        return items;
    }

    public void setItems(ArrayList<ProdutoVendido> items) {
        this.items = items;
    }
}
