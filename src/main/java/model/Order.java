package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long orderId;
    private int clientId;
    private LocalDateTime orderDate;

    public Order(long orderID, int clientId) {
        this.orderId = orderID;
        this.clientId = clientId;
        this.orderDate = LocalDateTime.now();
    }

    public Order(long orderId, int clientId, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public long getOrderId() {
        return orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order " +
                 + orderId +
                ", clientId=" + clientId +
                ", orderDate=" + orderDate;
    }
}
