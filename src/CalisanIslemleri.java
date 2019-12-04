import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalisanIslemleri {

	private Connection conn = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;

	public ArrayList<Calisan> calisanlariGetir() {

		ArrayList<Calisan> cikti = new ArrayList<Calisan>();

		try {
			statement = conn.createStatement();
			String sorgu = "Select * from calisanlar";
			ResultSet rs = statement.executeQuery(sorgu);

			while (rs.next()) {
				int id = rs.getInt("id");
				String ad = rs.getString("ad");
				String soyad = rs.getString("soyad");
				String departman = rs.getString("departman");
				String maas = rs.getString("maas");

				cikti.add(new Calisan(id, ad, soyad, departman, maas));
			}
			return cikti;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean girisYap(String kullaniciadi, String parola) {
		String sorgu = "Select * from adminler where username = ? and password = ?";
		try {
			preparedStatement = conn.prepareStatement(sorgu);

			preparedStatement.setString(1, kullaniciadi);
			preparedStatement.setString(2, parola);

			ResultSet rs = preparedStatement.executeQuery();

//			if (rs.next() == false) {
//				return false;
//			}else {
//				return true;
//			}

			return rs.next();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void calisanEkle(String ad, String soyad, String departman, String maas) {

		String sorgu = "Insert into calisanlar (ad,soyad,departman,maas) VALUES (?,?,?,?)";

		try {
			preparedStatement = conn.prepareStatement(sorgu);

			preparedStatement.setString(1, ad);
			preparedStatement.setString(2, soyad);
			preparedStatement.setString(3, departman);
			preparedStatement.setString(4, maas);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void calisanGuncelle(int id, String ad, String soyad, String departman, String maas) {

		String sorgu = "Update calisanlar set ad = ? , soyad = ? , departman = ? , maas = ? where id = ?";

		try {
			preparedStatement = conn.prepareStatement(sorgu);

			preparedStatement.setString(1, ad);
			preparedStatement.setString(2, soyad);
			preparedStatement.setString(3, departman);
			preparedStatement.setString(4, maas);
			preparedStatement.setInt(5, id);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void calisanSil(int id) {

		String sorgu = "Delete from calisanlar where id = ?";

		try {
			preparedStatement = conn.prepareStatement(sorgu);

			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public CalisanIslemleri() {

		String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.db_ismi
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//		String url = "jdbc:mysql://localhost:3306/sirketdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver bulunamadý...");
		}

		try {
			conn = DriverManager.getConnection(url, Database.kullanici_adi, Database.parola);
			System.out.println("Baðlantý baþarýlý...");
		} catch (SQLException ex) {
			System.out.println("Baðlantý baþarýsýz...");
		}

	}

}
