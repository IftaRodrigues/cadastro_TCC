package dao;

import db.ConnectionFactory;
import dominio.TipoProjeto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoProjetoDao {

    private ConnectionFactory cf = new ConnectionFactory();
    private Connection conn;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

    String sql;

    public TipoProjeto getTipoProjeto(Integer id) throws SQLException {
        conn = cf.getConnection();

        String sql = "select * from tipoProjeto"
                + " where id_tipoProjeto=?";

        pstm = conn.prepareStatement(sql);

        pstm.setInt(1, id);

        rs = pstm.executeQuery();

        TipoProjeto tipoProjeto = new TipoProjeto();

        while (rs.next()) {

            tipoProjeto.setId_tipoProjeto(rs.getInt("id_tipoProjeto"));
            tipoProjeto.setDescricao(rs.getString("descricao"));

        }

        rs.close();
        pstm.close();
        conn.close();

        return tipoProjeto;

    }

    public List<TipoProjeto> listaTipoProjeto() throws SQLException {

        List<TipoProjeto> Tiposp = new ArrayList<>();

        conn = cf.getConnection();

        pstm = conn.prepareStatement(
                "select * from tipoProjeto");

        rs = pstm.executeQuery();

        while (rs.next()) {
            TipoProjeto tipoProjeto = new TipoProjeto();

            tipoProjeto.setId_tipoProjeto(rs.getInt("id_tipoProjeto"));
            tipoProjeto.setDescricao(rs.getString("descricao"));

            Tiposp.add(tipoProjeto);
        }
        rs.close();
        pstm.close();
        conn.close();

        return Tiposp;

    }
}
