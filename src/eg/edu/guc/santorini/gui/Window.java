package eg.edu.guc.santorini.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;

import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;

import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

@SuppressWarnings("serial")
public class Window extends JFrame implements MouseListener, ActionListener {
	JTextField fieldp1;
	JTextField fieldp2;
	JLabel player1;
	JLabel player2;
	JLabel welcome;
	JPanel gridPanel;
	JPanel buttonPanel;
	JPanel a;
	JButton exit;
	adapter game;
	String p1name;
	String p2name;
	int tp1;
	int tp2;
	JButton NewGame;
	boolean select = false;
	private Location selectedloc;
	Piece selectedpiece = null;
	boolean move = true;
	private Location moveloc;
	Piece placingpiece = null;
	Location[][] templabels;

	public Window() {
		templabels = new Location[5][5];

		setTitle("Santorini");
		getContentPane().setBackground(Color.black);
		this.getContentPane().setLayout(new BorderLayout());

		setSize(1000, 700);

		a = new JPanel(null);

		a.setSize(1000, 700);
		a.setBackground(Color.GRAY);
		add(a, BorderLayout.CENTER);

		JLabel welcome = new JLabel("Santorini");
		welcome.setBounds(450, 0, 200, 100);
		welcome.setForeground(Color.WHITE);
		Font x = new Font("Normal", 100, 30);
		Font y = new Font("Normal", 50, 20);
		welcome.setFont(x);

		a.add(welcome);
		player1 = new JLabel("Player 1");
		player2 = new JLabel("player2");
		player1.setBounds(10, 150, 200, 100);
		player1.setForeground(Color.white);

		player1.setFont(y);
		a.add(player1);
		player2.setBounds(800, 150, 200, 100);
		player2.setForeground(Color.white);

		player2.setFont(y);
		a.add(player2);

		fieldp1 = new JTextField("Enter Name");
		fieldp1.setBounds(10, 210, 200, 30);
		a.add(fieldp1);
		p1name = fieldp1.getText();

		fieldp2 = new JTextField("Enter Name");
		fieldp2.setBounds(750, 210, 200, 30);

		a.add(fieldp2);
		p2name = fieldp2.getText();
		ImageIcon iconcube = new ImageIcon("Assets/cube level 0.png");
		JButton cube1 = new JButton(iconcube);
		cube1.setBounds(10, 250, 91, 92);
		cube1.setBackground(Color.GRAY);
		a.add(cube1);
		cube1.setActionCommand("cube1");
		cube1.addActionListener(this);

		ImageIcon iconpyramid = new ImageIcon("Assets/pyramid level 0.png");
		JButton pyramid1 = new JButton(iconpyramid);
		pyramid1.setActionCommand("Pyramid1");
		pyramid1.addActionListener(this);
		pyramid1.setBackground(Color.GRAY);
		pyramid1.setBounds(105, 250, 91, 92);
		a.add(pyramid1);

		JButton cube2 = new JButton(iconcube);
		cube2.setBounds(750, 250, 91, 92);
		cube2.setBackground(Color.GRAY);
		cube2.setActionCommand("cube2");
		cube2.addActionListener(this);

		a.add(cube2);

		JButton pyramid2 = new JButton(iconpyramid);

		pyramid2.setActionCommand("Pyramid2");
		pyramid2.addActionListener(this);
		pyramid2.setBackground(Color.GRAY);
		pyramid2.setBounds(845, 250, 91, 92);
		a.add(pyramid2);

		ImageIcon startimg = new ImageIcon("IMG/start.png");
		JButton start = new JButton(startimg);
		start.setBounds(420, 200, 120, 80);
		start.setActionCommand("start");
		start.addActionListener(this);
		a.add(start);
		ImageIcon exitimg = new ImageIcon("IMG/exit.gif");
		exit = new JButton(exitimg);
		exit.setBackground(Color.gray);
		exit.setBounds(420, 290, 120, 80);
		exit.setActionCommand("exit");
		exit.addActionListener(this);
		a.add(exit);

		initbuttonpanel();

		createbuttons();
		buttonPanel.setVisible(false);
		game = new adapter();

		a.setVisible(true);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void initbuttonpanel() {
		buttonPanel = new JPanel(new GridLayout(5, 1));
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.setSize(200, 500);

		getContentPane().add(buttonPanel, BorderLayout.EAST);

	}

	public void initgrid() {
		gridPanel = new JPanel(new GridLayout(5, 5));

		gridPanel.setSize(800, 462);
		gridPanel.setBackground(Color.BLACK);
		add(gridPanel, BorderLayout.CENTER);

	}

	public void createbuttons() {
		NewGame = new JButton(new ImageIcon("Assets/new-game.png"));
		NewGame.setSize(10, 5);

		NewGame.setBackground(Color.gray);
		NewGame.setActionCommand("New Game");
		NewGame.addActionListener(this);
		this.buttonPanel.add(NewGame);

	}

	public void initboard() {

		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 5; x++) {

				game.a.DrawGUI();

			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {

				templabels[i][j] = game.a.getBoard()[i][j];
				templabels[i][j].addMouseListener(this);
			}
		}

	}

	public static void main(String[] args) {
		new Window();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (select == false) {
			select = true;

			selectedloc = (Location) e.getSource();

			if (selectedloc.equals(game.a.getP1().getT1().getLocation())) {
				selectedpiece = game.a.getP1().getT1();
			}

			else if (selectedloc.equals(game.a.getP1().getT2().getLocation())) {
				selectedpiece = game.a.getP1().getT2();
			} else if (selectedloc.equals(game.a.getP2().getT1().getLocation())) {
				selectedpiece = game.a.getP2().getT1();
			} else if (selectedloc.equals(game.a.getP2().getT2().getLocation())) {
				selectedpiece = game.a.getP2().getT2();
			} else
				return;

			ArrayList<Location> pmoves = selectedpiece.possibleMoves();
			Location[][] x = game.a.getBoard();
			for (int i = 0; i < pmoves.size(); i++) {
				if (game.a.canMove(selectedpiece,
						game.a.getBoard()[pmoves.get(i).gety()][pmoves.get(i)
								.getx()])) {

					x[pmoves.get(i).gety()][pmoves.get(i).getx()]
							.setBorder(BorderFactory.createLoweredBevelBorder());

				}
			}

			game.a.setBoard(x);
		} else {

			if (move == true) {
				moveloc = (Location) e.getSource();
				move = false;
				try {

					game.a.move(selectedpiece, moveloc);
					if (game.a.isGameOver()) {

						JPanel over = new JPanel(null);

						JLabel Winner = new JLabel();

						Winner.setBounds(420, 0, 500, 100);
						Winner.setForeground(Color.WHITE);
						Font x = new Font("Normal", 100, 30);
						Winner.setFont(x);
						if (game.a.isWinner(game.a.getP1())) {
							Winner.setText(fieldp1.getText() + "  Is Winner ");
						}
						if (game.a.isWinner(game.a.getP2())) {
							Winner.setText(fieldp2.getText() + "  Is Winner");
						}

						over.setSize(1000, 700);
						over.setBackground(Color.GRAY);

						this.getContentPane().add(over, BorderLayout.CENTER);
						over.add(Winner);
						buttonPanel.add(exit);

						gridPanel.setVisible(false);

						over.setVisible(true);

					}
					Location[][] x = game.a.getBoard();
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							x[i][j].setBorder(BorderFactory.createEmptyBorder());

						}
					}
					game.a.setBoard(x);

					if (moveloc.equals(game.a.getP1().getT1().getLocation())) {
						placingpiece = game.a.getP1().getT1();
					}

					else if (moveloc.equals(game.a.getP1().getT2()
							.getLocation())) {
						placingpiece = game.a.getP1().getT2();
					} else if (moveloc.equals(game.a.getP2().getT1()
							.getLocation())) {
						placingpiece = game.a.getP2().getT1();
					} else if (moveloc.equals(game.a.getP2().getT2()
							.getLocation())) {
						placingpiece = game.a.getP2().getT2();
					} else
						return;

					ArrayList<Location> Possplacements = placingpiece
							.possiblePlacements();
					for (int i = 0; i < Possplacements.size(); i++) {
						if (game.a
								.canPlace(placingpiece,
										game.a.getBoard()[Possplacements.get(i)
												.gety()][Possplacements.get(i)
												.getx()])) {

							x[Possplacements.get(i).gety()][Possplacements.get(
									i).getx()].setBorder(BorderFactory
									.createLoweredBevelBorder());

						}
					}

					game.a.setBoard(x);
					// gridPanel.removeAll();
					initboard();
					addTiles();
					gridPanel.validate();

				} catch (InvalidMoveException e1) {
					System.out.println("You cannot move here");

				}

			}

			else {
				Location placeloc = (Location) e.getSource();
				selectedloc = null;
				moveloc = null;

				select = false;
				move = true;

				try {
					game.a.place(placingpiece, placeloc);
					gridPanel.removeAll();
					initboard();
					addTiles();
					gridPanel.validate();
					Location[][] x = game.a.getBoard();
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							x[i][j].setBorder(BorderFactory.createEmptyBorder());
						}
					}
					game.a.setBoard(x);
				} catch (InvalidPlacementException e1) {

					System.out.println("You cant place");
				}

			}

		}
		addTiles();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Build")) {

		}
		if (e.getActionCommand().equals("start")) {
			if (tp1 == 0 || tp2 == 0 || fieldp1.getText().equals("Enter Name")
					|| fieldp2.getText().equals("Enter Name")) {
				return;
			}
			game = new adapter(game.initPlayerp1(fieldp1.getText(), tp1),

			game.initPlayerp2(fieldp2.getText(), tp2));

			initgrid();
			initboard();
			addTiles();
			a.setVisible(false);
			gridPanel.setVisible(true);
			buttonPanel.add(exit);
			buttonPanel.setVisible(true);

		}
		if (e.getActionCommand().equals("New Game")) {
			game = new adapter(game.initPlayerp1(fieldp1.getText(), tp1),

			game.initPlayerp2(fieldp2.getText(), tp2));
			gridPanel.removeAll();

			initboard();
			addTiles();

			gridPanel.setVisible(true);

			gridPanel.validate();
			a.setVisible(false);

			buttonPanel.setVisible(true);

		}

		if (e.getActionCommand().equals("exit")) {
			WindowDestroyer myListener = new WindowDestroyer();
			this.addWindowListener(myListener);
			System.exit(0);
		}

		if (e.getActionCommand() == "cube1") {
			tp1 = 1;

		}

		if (e.getActionCommand() == "Pyramid1") {
			tp1 = 2;
		}

		if (e.getActionCommand() == "cube2") {
			tp2 = 1;
		}

		if (e.getActionCommand() == "Pyramid2") {
			tp2 = 2;
		}
		if (e.getActionCommand() == "move") {

		}
	}

	public void addTiles() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Location x = templabels[i][j];
				gridPanel.add(x);

			}
		}
	}

}