import java.awt.Color;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.annotation.Generated;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.omg.PortableServer.ServantRetentionPolicyValue;

public class GirisEkrani extends JFrame{

	CalisanIslemleri calisanIslemleri = new CalisanIslemleri();

	private JFrame frame;
	private JTextField txtkullaniciadi;
	private JPasswordField txtparola;

	public GirisEkrani() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 707, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Kullanýcý Adý :");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(160, 150, 216, 25);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Parola :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(160, 200, 216, 25);
		frame.getContentPane().add(lblPassword);
		
		JLabel mesajyazisi = new JLabel("");
		mesajyazisi.setFont(new Font("Tahoma", Font.BOLD, 14));
		mesajyazisi.setForeground(Color.RED);
		mesajyazisi.setBounds(160, 230, 300, 25);
		frame.getContentPane().add(mesajyazisi);

		txtkullaniciadi = new JTextField();
		txtkullaniciadi.setBounds(300, 150, 150, 25);
		frame.getContentPane().add(txtkullaniciadi);
		txtkullaniciadi.setColumns(10);

		txtparola = new JPasswordField();
		txtparola.setColumns(10);
		txtparola.setBounds(300, 200, 150, 25);
		frame.getContentPane().add(txtparola);

		JButton btnLogin = new JButton("Giriþ Yap");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mesajyazisi.setText("");
				String kullaniciadi = txtkullaniciadi.getText();
				String parola = new String(txtparola.getPassword());

				boolean girisbasarili = calisanIslemleri.girisYap(kullaniciadi, parola);
				if (girisbasarili) {
					CalisanEkrani calisanEkrani = new CalisanEkrani(GirisEkrani.this,true);
					setVisible(false);
					calisanEkrani.setVisible(true);
//					System.exit(0);
				} else {
					mesajyazisi.setText("Giriþ baþarýsýz...Lütfen tekrar deneyiniz...");
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(350, 260, 100, 25);
		frame.getContentPane().add(btnLogin);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GirisEkrani().frame.setVisible(true);
			}
		});
	}

}
