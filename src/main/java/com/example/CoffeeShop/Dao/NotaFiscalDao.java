package Dao;

import Model.NotaFiscal;
import Model.Pedido;

import java.sql.*;
import java.util.ArrayList;

public class NotaFiscalDao {
    private Connection conn;

    //create
    public void createNf(NotaFiscal nf) {
        conn = ConnectionFactory.getConnection();

        String query = "insert into T_notaFiscal(idT_notaFiscal, nm_notaFiscal) values(?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, nf.getNfId());
            stmt.setInt(2, nf.getNfOficialNumber());

            stmt.executeUpdate();

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read by ID
    public NotaFiscal searchNfById(int id) {
        NotaFiscal nf = new NotaFiscal();
        conn = ConnectionFactory.getConnection();

        String query = "select * from T_notaFiscal where idT_notaFiscal = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nf.setNfId(rs.getInt(1));
                nf.setNfOficialNumber(rs.getInt(2));
            }

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nf;
    }

    //read all
    public ArrayList<NotaFiscal> searchAllNf() {
        ArrayList<NotaFiscal> nfList = new ArrayList<NotaFiscal>();
        conn = ConnectionFactory.getConnection();

        String query = "select * from T_notaFiscal";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                NotaFiscal nf = new NotaFiscal();

                nf.setNfId(rs.getInt(1));
                nf.setNfOficialNumber(rs.getInt(2));

                nfList.add(nf);
            }

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nfList;
    }

    //update
    public void updateNf(NotaFiscal nf) {
        conn = ConnectionFactory.getConnection();

        String query = "update T_notaFiscal set idT_notaFiscal = ?, nm_notaFiscal = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, nf.getNfId());
            stmt.setInt(2, nf.getNfOficialNumber());

            stmt.executeUpdate();

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete
    public void deleteNf(NotaFiscal nf) {
        conn = ConnectionFactory.getConnection();

        String query = "delete from T_notaFiscal where idT_notaFiscal = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, nf.getNfId());

            stmt.executeUpdate();

            // close
            conn.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
