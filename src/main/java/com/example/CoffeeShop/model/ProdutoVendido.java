package Model;

public class ProdutoVendido {
    int orderItemId;
    int orderQuantity;
    Produto product;

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Produto getProduct() {
        return product;
    }

    public void setProduct(Produto product) {
        this.product = product;
    }
}
