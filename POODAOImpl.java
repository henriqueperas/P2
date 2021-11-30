import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class POODAOImpl implements POODAO{

    private static final String URLDB = "jdbc:mariadb://127.0.0.1:3306/POO?allowMultiQueries=true";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456";

    public POODAOImpl(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URLDB, USUARIO, PASSWORD);
    }

    @Override
    public void adicionarAluno(POOaluno aluno) {
        try{
            Connection con = getConnection();
            String sql = "INSERT INTO aluno (RA, nome, sobrenome, nasimento, CPF) " +
                    "VALUES (?, ?, ?, ?, ?);";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,aluno.getRA());
            stmt.setString(2,aluno.getNome());
            stmt.setString(3,aluno.getSobrenome());
            stmt.setDate(4, java.sql.Date.valueOf(aluno.getNasimento()));
            stmt.setInt(5,aluno.getCPF());
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionarMateria(POOmateria materia) {
        try{
            Connection con = getConnection();
            String sql = "INSERT INTO materia (Codigo, nome, duracao, turno) " +
                    "VALUES (?, ?, ?, ?);";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,materia.getCodigo());
            stmt.setString(2,materia.getNome());
            stmt.setString(3,materia.getDuracao());
            stmt.setString(4,materia.getTurno());
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<POOaluno> pesquisarPorNomeAluno(String nome) {
        List<POOaluno> lista = new ArrayList<>();
        try{
            Connection con = getConnection();
            String sql = "SELECT * FROM aluno WHERE nome like '%" + nome + "%'";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                POOaluno aluno = new POOaluno();
                aluno.setRA(rs.getLong("RA"));
                aluno.setNome(rs.getString("nome"));
                aluno.setSobrenome(rs.getString("Sobrenome"));
                aluno.setNasimento(rs.getDate("Nasimento").toLocalDate());
                aluno.setCPF(rs.getInt("CPF"));
                lista.add(aluno);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<POOmateria> pesquisarPorNomeMateria(String nome) {
        List<POOmateria> lista = new ArrayList<>();
        try {
            Connection con = getConnection();
            String sql = "SELECT * FROM materia WHERE nome like '%" + nome + "%'";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                POOmateria materia = new POOmateria();
                materia.setCodigo(rs.getLong("Codigo"));
                materia.setNome(rs.getString("nome"));
                materia.setDuracao(rs.getString("duracao"));
                materia.setTurno(rs.getString("turno"));
                lista.add(materia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void removerAluno(long RA) {
        try{
            Connection con = getConnection();
            String sql = "DELETE FROM aluno WHERE RA = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,RA);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerMateria(long Codigo) {
        try{
            Connection con = getConnection();
            String sql = "DELETE FROM materia WHERE Codigo = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,Codigo);
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizarAluno(long RA, POOaluno aluno) {
        try{
            Connection con = getConnection();
            String sql = "UPDATE aluno SET nome = ?, sobrenome = ?, nasimento = ?, CPF = ? WHERE RA = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,aluno.getNome());
            stmt.setString(2,aluno.getSobrenome());
            stmt.setDate(3, java.sql.Date.valueOf(aluno.getNasimento()));
            stmt.setInt(4,aluno.getCPF());
            stmt.setLong(5,aluno.getRA());
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizarMateria(long Codigo, POOmateria materia) {
        try{
            Connection con = getConnection();
            String sql = "UPDATE materia SET nome = ?, duracao = ?, turno = ? WHERE Codigo = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,materia.getNome());
            stmt.setString(2,materia.getDuracao());
            stmt.setString(3,materia.getTurno());
            stmt.setLong(4,materia.getCodigo());
            stmt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
