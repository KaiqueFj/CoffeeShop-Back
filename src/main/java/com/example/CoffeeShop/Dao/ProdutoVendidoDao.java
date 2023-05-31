package Dao;

import Model.Pedido;
import Model.ProdutoVendido;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.*;
import java.util.ArrayList;

public class ProdutoVendidoDao {

    private Connection conn;

    //create
    public void createSaleItem(ProdutoVendido orderItem) {
        conn = ConnectionFactory.getConnection();

        String query = "insert into T_produto_vendido(idT_produto_vendido, qt_vendida, T_notaFiscal_idT_notaFiscal, " +
                "T_produto_idT_produto) values(?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, orderItem.getOrderItemId());
            stmt.setInt(2, orderItem.getOrderQuantity());
            //stmt.setInt(3, orderItem.getNf());
            //stmt.setInt(4, orderItem.getProduct().getId());

            stmt.executeUpdate();

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read by ID
    public ProdutoVendido searchOrderItemById(int id) {
        ProdutoVendido orderItem = new ProdutoVendido();
        conn = ConnectionFactory.getConnection();

        String query = "select * from T_produto_vendido where idT_produto_vendido = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                orderItem.setOrderItemId(rs.getInt(1));
                orderItem.setOrderQuantity(rs.getInt(2));
                //orderItem.se(rs.getDate(3).toLocalDate());
                //orderItem.setTotalAmount(rs.getFloat(4));
            }

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItem;
    }

    //read by date

    //read by product

    //read all
    public ArrayList<ProdutoVendido> searchAllOrderItems() {
        ArrayList<ProdutoVendido> orderItemsList = new ArrayList<ProdutoVendido>();
        conn = ConnectionFactory.getConnection();

        String query = "select * from T_produto_vendido";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutoVendido orderItem = new ProdutoVendido();

                orderItem.setOrderItemId(rs.getInt(1));
                orderItem.setOrderQuantity(rs.getInt(2));
                //orderItem.se(rs.getInt(3));
                //orderItem.setProduct(rs.get(4));

                orderItemsList.add(orderItem);
            }

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemsList;
    }

    //update
    public void updateOrderItem(ProdutoVendido orderItem) {
        conn = ConnectionFactory.getConnection();

        String query = "update T_Pedido set idT_Pedido = ?, ds_pedido = ?, dt_pedido = ?, vl_pedido = ?, " +
                "T_cliente_idT_cliente = ?, T_cliente_T_cliente_fisico_id_cliente_fisico = ?, " +
                "T_cliente_T_cliente_juridico_id_cliente_juridico = ?, T_notaFiscal_idT_notaFiscal = ? " +
                "where idT_Pedido = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, orderItem.getOrderItemId());
            stmt.setInt(2, orderItem.getOrderQuantity());
            //stmt.setInt(3, orderItem.getNf());
            //stmt.setInt(4, orderItem.getProduct().getId());
            stmt.executeUpdate();

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete
    public void deleteOrderItem(ProdutoVendido orderItem) {
        conn = ConnectionFactory.getConnection();

        String query = "delete from T_produto_vendido where idT_produto_vendido = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, orderItem.getOrderItemId());

            stmt.executeUpdate();

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
