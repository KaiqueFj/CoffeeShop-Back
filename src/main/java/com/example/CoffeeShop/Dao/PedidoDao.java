package Dao;

import Model.NotaFiscal;
import Model.Pedido;
import Model.Produto;
import Model.ProdutoVendido;

import java.sql.*;
import java.util.ArrayList;

public class PedidoDao {

    private Connection conn;

    //create
    public void createOrder(Pedido order) {
        conn = ConnectionFactory.getConnection();

        String query = "insert into T_Pedido(idT_Pedido, ds_pedido, dt_pedido, vl_pedido, T_cliente_idT_cliente, " +
                "T_cliente_T_cliente_fisico_id_cliente_fisico, T_cliente_T_cliente_juridico_id_cliente_juridico, " +
                "T_notaFiscal_idT_notaFiscal) values(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, order.getOrderId());
            stmt.setString(2, order.getOrderDescription());
            stmt.setDate(3, Date.valueOf(order.getDate()));
            stmt.setFloat(4, order.getTotalAmount()); //arrumar campo no BD que está como VARCHAR
            /*
            stmt.setInt(5, order.getCustomer().getId());
            stmt.setInt(6, );
            stmt.setInt(7, );
            stmt.setInt(8, );
            */

            stmt.executeUpdate();

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read by ID
    public Pedido searchOrderById(int id) {
        Pedido order = new Pedido();
        conn = ConnectionFactory.getConnection();

        String query = "select * from T_Pedido where idT_Pedido = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order.setOrderId(rs.getInt(1));
                order.setOrderDescription(rs.getString(2));
                order.setDate(rs.getDate(3).toLocalDate());
                order.setTotalAmount(rs.getFloat(4));
            }

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    //read by date

    //read by product

    //read all
    public ArrayList<Pedido> searchAllOrders() {
        ArrayList<Pedido> orderList = new ArrayList<Pedido>();
        conn = ConnectionFactory.getConnection();

        String query = "select * from T_Pedido";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido order = new Pedido();

                order.setOrderId(rs.getInt(1));
                order.setOrderDescription(rs.getString(2));
                order.setDate(rs.getDate(3).toLocalDate());
                order.setTotalAmount(rs.getFloat(4));

                orderList.add(order);
            }

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    //update
    public void updateOrder(Pedido order) {
        conn = ConnectionFactory.getConnection();

        String query = "update T_Pedido set idT_Pedido = ?, ds_pedido = ?, dt_pedido = ?, vl_pedido = ?, " +
                "T_cliente_idT_cliente = ?, T_cliente_T_cliente_fisico_id_cliente_fisico = ?, " +
                "T_cliente_T_cliente_juridico_id_cliente_juridico = ?, T_notaFiscal_idT_notaFiscal = ? " +
                "where idT_Pedido = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, order.getOrderId());
            stmt.setString(2, order.getOrderDescription());
            stmt.setDate(3, Date.valueOf(order.getDate()));
            stmt.setFloat(4, order.getTotalAmount()); //arrumar campo no BD que está como VARCHAR
            /*
            stmt.setInt(5, order.getCustomer().getId());
            stmt.setInt(6, );
            stmt.setInt(7, );
            stmt.setInt(8, );
             */

            stmt.executeUpdate();

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete
    public void deleteOrder(Pedido order) {
        conn = ConnectionFactory.getConnection();

        String query = "delete from T_Pedido where idT_Pedido = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, order.getOrderId());

            stmt.executeUpdate();

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
