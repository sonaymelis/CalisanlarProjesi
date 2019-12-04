import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CalisanEkrani extends JDialog {

	CalisanIslemleri calisanIslemleri = new CalisanIslemleri();

	DefaultTableModel model;
	private JDialog dialog;
	private JTable calisantablosu;
	private JScrollPane scrollpane;
	private JTextField txtarama;
	private JSeparator seperator;
	private JTextField txtad;
	private JTextField txtsoyad;
	private JTextField txtdep;
	private JTextField txtmaas;

	public CalisanEkrani(JFrame parent, boolean modal) {
		super(parent, modal);
		initialize();
		model = (DefaultTableModel) calisantablosu.getModel();
		model.addColumn("id");
		model.addColumn("ad");
		model.addColumn("soyad");
		model.addColumn("departman");
		model.addColumn("maas");
		calisanGoruntule();
	}

	@SuppressWarnings("deprecation")
	private void initialize() {

		dialog = new JDialog();
		dialog.setBounds(100, 100, 707, 546);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.getContentPane().setLayout(null);

		calisantablosu = new JTable();
		scrollpane = new JScrollPane(calisantablosu);
		scrollpane.setBounds(20, 250, 650, 240);
		dialog.getContentPane().add(scrollpane);

		txtarama = new JTextField();
		txtarama.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				String ara = txtarama.getText();
				dinamikAra(ara);
			}
		});
		txtarama.setBounds(20, 20, 300, 25);
		dialog.getContentPane().add(txtarama);

		seperator = new JSeparator();
		seperator.setBounds(20, 65, 650, 20);
		dialog.getContentPane().add(seperator);

		JLabel lblad = new JLabel("Ad :");
		lblad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblad.setBounds(20, 85, 100, 25);
		dialog.getContentPane().add(lblad);

		JLabel lblsoyad = new JLabel("Soyad :");
		lblsoyad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblsoyad.setBounds(20, 115, 100, 25);
		dialog.getContentPane().add(lblsoyad);

		JLabel lbldep = new JLabel("Departman :");
		lbldep.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbldep.setBounds(20, 145, 100, 25);
		dialog.getContentPane().add(lbldep);

		JLabel lblmaas = new JLabel("Maaþ :");
		lblmaas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblmaas.setBounds(20, 175, 100, 25);
		dialog.getContentPane().add(lblmaas);

		txtad = new JTextField();
		txtad.setBounds(130, 85, 200, 25);
		dialog.getContentPane().add(txtad);

		txtsoyad = new JTextField();
		txtsoyad.setBounds(130, 115, 200, 25);
		dialog.getContentPane().add(txtsoyad);

		txtdep = new JTextField();
		txtdep.setBounds(130, 145, 200, 25);
		dialog.getContentPane().add(txtdep);

		txtmaas = new JTextField();
		txtmaas.setBounds(130, 175, 200, 25);
		dialog.getContentPane().add(txtmaas);

		JLabel mesajyazisi = new JLabel("");
		mesajyazisi.setFont(new Font("Tahoma", Font.BOLD, 15));
		mesajyazisi.setForeground(Color.RED);
		mesajyazisi.setBounds(130, 210, 250, 25);
		dialog.getContentPane().add(mesajyazisi);

		JButton btncalisanekle = new JButton("Yeni Çalýþan Ekle");
		btncalisanekle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mesajyazisi.setText("");
				String ad = txtad.getText();
				String soyad = txtsoyad.getText();
				String departman = txtdep.getText();
				String maas = txtmaas.getText();

				calisanIslemleri.calisanEkle(ad, soyad, departman, maas);
				calisanGoruntule();
				mesajyazisi.setText("Yeni çalýþan baþarýyla eklendi...");

			}
		});
		btncalisanekle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncalisanekle.setBounds(370, 85, 200, 25);
		dialog.getContentPane().add(btncalisanekle);

		calisantablosu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedrow = calisantablosu.getSelectedRow();
				txtad.setText(model.getValueAt(selectedrow, 1).toString());
				txtsoyad.setText(model.getValueAt(selectedrow, 2).toString());
				txtdep.setText(model.getValueAt(selectedrow, 3).toString());
				txtmaas.setText(model.getValueAt(selectedrow, 4).toString());
			}
		});


		JButton btngüncelle = new JButton("Güncelle");
		btngüncelle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mesajyazisi.setText("");
				String ad = txtad.getText();
				String soyad = txtsoyad.getText();
				String departman = txtdep.getText();
				String maas = txtmaas.getText();
				
				int selectedrow = calisantablosu.getSelectedRow();
				
				if (selectedrow == -1) {
					if (model.getRowCount() == 0) {
						mesajyazisi.setText("Çalýþanlar tablosu þuanda boþ!!!");
					}else {
						mesajyazisi.setText("Lütfen güncellenecek çalýþaný seçiniz...");
					}
				}else {
					int id = (int) model.getValueAt(selectedrow, 0);
					calisanIslemleri.calisanGuncelle(id,ad,soyad,departman,maas);
					calisanGoruntule();
					mesajyazisi.setText("Çalýþan baþarýyla güncellendi...");
				}
				

			}
		});
		btngüncelle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btngüncelle.setBounds(370, 115, 200, 25);
		dialog.getContentPane().add(btngüncelle);
		
		JButton btnsil = new JButton("Sil");
		btnsil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mesajyazisi.setText("");
				
				int selectedrow = calisantablosu.getSelectedRow();
				
				if (selectedrow == -1) {
					if (model.getRowCount() == 0) {
						mesajyazisi.setText("Çalýþanlar tablosu þuanda boþ!!!");
					}else {
						mesajyazisi.setText("Lütfen silinecek çalýþaný seçiniz...");
					}
				}else {
					int id = (int) model.getValueAt(selectedrow, 0);
					calisanIslemleri.calisanSil(id);
					calisanGoruntule();
					mesajyazisi.setText("Çalýþan baþarýyla silindi...");
				}
				

			}
		});
		btnsil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnsil.setBounds(370, 145, 200, 25);
		dialog.getContentPane().add(btnsil);

		dialog.setVisible(true);

	}

	public void dinamikAra(String ara) {

		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
		calisantablosu.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(ara));

	}

	public void calisanGoruntule() {

		model.setRowCount(0);

		ArrayList<Calisan> calisanlar = new ArrayList<>();

		calisanlar = calisanIslemleri.calisanlariGetir();

		if (calisanlar != null) {
			for (Calisan calisan : calisanlar) {
				Object[] eklenecek = { calisan.getId(), calisan.getAd(), calisan.getSoyad(), calisan.getDepartman(),
						calisan.getMaas() };
				model.addRow(eklenecek);
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				CalisanEkrani calisanEkrani = new CalisanEkrani(new JFrame(), true);
				calisanEkrani.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
			});

				calisanEkrani.setVisible(true);
			}
		});
	}
	


}
