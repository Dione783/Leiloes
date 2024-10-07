import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("INSERT INTO produtos (nome,valor,status) VALUES (?,?,?)");
            prep.setString(1,produto.getNome());
            prep.setInt(2,produto.getValor());
            prep.setString(3,produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null,"Produto Cadastrado");
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement("SELECT * from produtos");
            resultset = prep.executeQuery();
            while(resultset.next()){
                int id = resultset.getInt("id");
                String nome = resultset.getString("nome");
                int valor = resultset.getInt("valor");
                String status = resultset.getString("status");
                ProdutosDTO produto = new ProdutosDTO(id,nome,valor,status);
                listagem.add(produto);
            }
            return listagem;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro resultset");
        }
        JOptionPane.showMessageDialog(null,"Erro resultset");
        return null;
    }
    
}