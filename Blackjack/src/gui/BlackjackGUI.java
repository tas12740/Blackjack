package gui;

import cards.*;
import players.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.Timer;

public class BlackjackGUI extends JFrame {
	private static final long serialVersionUID = -3673199892890222483L;
	// Game components
	private Player player;
	private Player house;
	private Deck deck;
	private int bet;

	// Table
	private JPanel table;
	private SpringLayout layoutTable;

	private BufferedImage deckImage;
	private BufferedImage holeCardFaceUp;

	private JLabel deckLabel;
	private JLabel holeLabel;
	private JLabel dealerCard;
	private JLabel userCard;

	private JLabel betLabel;

	private int z;

	// Bottom Panel
	private JPanel bottom;
	private JLabel score;
	private JLabel houseScore;
	private JButton reset;
	private JButton hit;
	private JButton doubleDown;
	private JButton stand;
	private JButton surrender;
	// private JButton split

	// Money
	private JLabel moneyLabel;
	private JTextField moneyField;
	private JButton betButton;
	private JLabel result;

	public BlackjackGUI() {
		player = new Player();
		house = new Player();
		deck = new Deck();
		// set up the upper panel
		score = new JLabel("");
		houseScore = new JLabel("");
		score.setOpaque(true);

		table = new JPanel();
		setUpTable();
		bottom = new JPanel();
		setUpBottom();

		this.setLayout(new BorderLayout());

		this.add(table, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);

		try {
			BufferedImage aceDiamond = ImageIO.read(BlackjackGUI.class.getResource("/AceDiamonds.png"));
			this.setIconImage(aceDiamond);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTitle("Blackjack");
		pack();
		validate();
		repaint();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BlackjackGUI();
	}

	private JPanel setUpTable() {
		deck.shuffle();
		player.reset();
		house.reset();

		table.removeAll();
		table.setBackground(Color.green);

		layoutTable = new SpringLayout();
		table.setLayout(layoutTable);

		try {
			deckImage = ImageIO.read(BlackjackGUI.class.getResource("/playingcardback.png"));
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		deckLabel = new JLabel(new ImageIcon(deckImage));
		layoutTable.putConstraint(SpringLayout.WEST, deckLabel, 5, SpringLayout.WEST, table);
		layoutTable.putConstraint(SpringLayout.NORTH, deckLabel, 5, SpringLayout.NORTH, table);
		table.add(deckLabel);

		table.setPreferredSize(new Dimension(657, 304));

		return table;
	}

	private JPanel setUpBottom() {
		JPanel buttons = new JPanel();
		JPanel money = new JPanel();

		bottom.removeAll();

		// buttons
		buttons.add(score);

		hit = new JButton("Hit");
		buttons.add(hit);
		hit.setEnabled(false);
		hit.addActionListener(new HitButton());
		hit.setFocusPainted(false);

		stand = new JButton("Stand");
		buttons.add(stand);
		stand.setEnabled(false);
		stand.addActionListener(new StandButton());
		stand.setFocusPainted(false);

		doubleDown = new JButton("Double");
		buttons.add(doubleDown);
		doubleDown.setEnabled(false);
		doubleDown.addActionListener(new DoubleButton());
		doubleDown.setFocusPainted(false);

		surrender = new JButton("Surrender");
		buttons.add(surrender);
		surrender.setEnabled(false);
		surrender.addActionListener(new SurrenderButton());
		surrender.setFocusPainted(false);

		reset = new JButton("Reset");
		buttons.add(reset);
		reset.addActionListener(new ResetButton());
		reset.setEnabled(false);
		reset.setFocusPainted(false);

		buttons.add(houseScore);

		// Money
		moneyLabel = new JLabel("You have $" + player.getMoney() + "     Bet:");
		money.add(moneyLabel);
		moneyField = new JTextField(5);
		moneyField.setHorizontalAlignment(JTextField.CENTER);
		moneyField.setText("1");
		moneyField.addActionListener(new BetButton());
		money.add(moneyField);

		betButton = new JButton("Bet");
		money.add(betButton);
		betButton.addActionListener(new BetButton());
		betButton.setFocusPainted(false);

		result = new JLabel("");
		money.add(result);

		bottom.setLayout(new BorderLayout());
		bottom.add(buttons, BorderLayout.CENTER);
		bottom.add(money, BorderLayout.SOUTH);

		return bottom;
	}

	private class HitButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(hit)) {
				player.hit(deck.deal());
				JLabel cardLabel;
				Card c = player.getCards().get(player.getCards().size() - 1);
				String path = "/" + c.getValue() + c.getSuit() + ".png";
				BufferedImage playerCard = null;
				try {
					playerCard = ImageIO.read(BlackjackGUI.class.getResource(path));
				} catch (IOException exc) {
					exc.printStackTrace();
				}
				cardLabel = new JLabel(new ImageIcon(playerCard));
				layoutTable.putConstraint(SpringLayout.WEST, cardLabel, 36, SpringLayout.WEST, userCard);
				layoutTable.putConstraint(SpringLayout.NORTH, cardLabel, 98, SpringLayout.SOUTH, dealerCard);
				userCard = cardLabel;
				table.add(cardLabel);
				table.setComponentZOrder(cardLabel, 0);

				if (player.getSum()[0] > 21) {
					score.setForeground(Color.RED);
					score.setText("Bust! You have " + player.display());
					stand.setEnabled(false);
					hit.setEnabled(false);
					surrender.setEnabled(false);
					doubleDown.setEnabled(false);

					player.subtractMoney(bet);
					moneyLabel.setText("You have $" + player.getMoney() + "     Bet:");
					result.setForeground(Color.red);
					result.setText("You lost $" + bet);
					reset.setEnabled(true);
				} else {
					score.setForeground(Color.BLACK);
					score.setText("You have " + player.display());
				}

				surrender.setEnabled(false);

				pack();
				validate();
				repaint();
			}

		}

	}

	private class StandButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			hit.setEnabled(false);
			surrender.setEnabled(false);
			// reveal the other card
			try {
				holeLabel.setIcon(new ImageIcon(ImageIO.read(BlackjackGUI.class.getResource(
						"/" + house.getCards().get(0).getValue() + house.getCards().get(0).getSuit() + ".png"))));
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			while (house.getSum()[0] <= 17) {
				if (has21()) {
					break;
				}
				house.hit(deck.deal());
				JLabel cardLabel;
				Card c = house.getCards().get(house.getCards().size() - 1);
				String path = "/" + c.getValue() + c.getSuit() + ".png";
				BufferedImage playerCard = null;
				try {
					playerCard = ImageIO.read(BlackjackGUI.class.getResource(path));
				} catch (IOException exc) {
					exc.printStackTrace();
				}
				cardLabel = new JLabel(new ImageIcon(playerCard));
				layoutTable.putConstraint(SpringLayout.WEST, cardLabel, 36, SpringLayout.WEST, dealerCard);
				layoutTable.putConstraint(SpringLayout.NORTH, cardLabel, 5, SpringLayout.NORTH, table);
				dealerCard = cardLabel;
				table.add(cardLabel);
				table.setComponentZOrder(cardLabel, 0);
			}

			int maxDealer = house.getMaxScore();
			if (maxDealer > 21) {
				houseScore.setText("House has busted with score " + maxDealer);
				houseScore.setForeground(Color.RED);
				player.addMoney(bet);
				result.setForeground(Color.black);
				result.setText("You won $" + bet);
				moneyLabel.setText("You have $" + player.getMoney() + "     Bet:");
				stand.setEnabled(false);
				doubleDown.setEnabled(false);
				reset.setEnabled(true);
				return;
			} else {
				houseScore.setText("House has " + maxDealer);
				houseScore.setForeground(Color.BLACK);
			}

			// now compare the dealer and user totals
			int maxPlayer = player.getMaxScore();
			if (maxPlayer > maxDealer) {
				player.addMoney(bet);
				result.setForeground(Color.black);
				result.setText("You won $" + bet);
				moneyLabel.setText("You have $" + player.getMoney() + "     Bet:");
			} else {
				player.subtractMoney(bet);
				result.setForeground(Color.red);
				result.setText("You lost $" + bet);
				moneyLabel.setText("You have $" + player.getMoney() + "     Bet:");
			}

			reset.setEnabled(true);
			stand.setEnabled(false);
			doubleDown.setEnabled(false);

			pack();
			validate();
			repaint();
			table.repaint();
		}

		private boolean has21() {
			int[] sum = house.getSum();
			for (int i = 0; i < sum.length; i++) {
				if (sum[i] == 21) {
					return true;
				}
			}
			return false;
		}
	}

	private class ResetButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(reset)) {
				table = setUpTable();
				hit.setEnabled(false);
				stand.setEnabled(false);
				betButton.setEnabled(true);
				moneyField.setEnabled(true);
				surrender.setEnabled(false);
				doubleDown.setEnabled(false);
				score.setText("");
				result.setText("");
				houseScore.setText("");
				pack();
				validate();
				repaint();
				table.repaint();
				reset.setEnabled(false);
			}

		}

	}

	private class DoubleButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(doubleDown)) {
				bet *= 2;

				betLabel.setText("$" + bet);

				player.hit(deck.deal());
				JLabel cardLabel;
				Card c = player.getCards().get(player.getCards().size() - 1);
				String path = "/" + c.getValue() + c.getSuit() + ".png";
				BufferedImage playerCard = null;
				try {
					playerCard = ImageIO.read(BlackjackGUI.class.getResource(path));
				} catch (IOException exc) {
					exc.printStackTrace();
				}
				cardLabel = new JLabel(new ImageIcon(playerCard));
				layoutTable.putConstraint(SpringLayout.WEST, cardLabel, 36, SpringLayout.WEST, userCard);
				layoutTable.putConstraint(SpringLayout.NORTH, cardLabel, 98, SpringLayout.SOUTH, dealerCard);
				userCard = cardLabel;
				table.add(cardLabel);
				table.setComponentZOrder(cardLabel, 0);

				if (player.getSum()[0] > 21) {
					score.setForeground(Color.RED);
					score.setText("Bust! You have " + player.display());

					player.subtractMoney(bet);
					moneyLabel.setText("You have $" + player.getMoney() + "     Bet:");
					result.setForeground(Color.RED);
					result.setText("You lost $" + bet);

					doubleDown.setEnabled(false);
					hit.setEnabled(false);
					stand.setEnabled(false);
					surrender.setEnabled(false);
					reset.setEnabled(true);
				} else {
					score.setForeground(Color.BLACK);
					score.setText("You have " + player.display());

					stand.doClick();
					doubleDown.setEnabled(false);
					hit.setEnabled(false);
					stand.setEnabled(false);
					surrender.setEnabled(false);
				}

				pack();
				validate();
				repaint();
			}
		}
	}

	private class BetButton implements ActionListener {

		private Timer timeHole = new Timer(10, new MoveHoleCard());
		private int offsetHole = 0;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(betButton)) {
				if (moneyField.getText().equals("Taylor is the best")) {
					player.addMoney(1000000);
				} else {
					try {
						result.setText("");

						bet = Integer.parseInt(moneyField.getText());
						hit.setEnabled(true);
						stand.setEnabled(true);
						betButton.setEnabled(false);
						moneyField.setEnabled(false);
						doubleDown.setEnabled(true);
						surrender.setEnabled(true);

						betLabel = new JLabel("$" + bet);
						table.add(betLabel);
						layoutTable.putConstraint(SpringLayout.WEST, betLabel, 5, SpringLayout.WEST, table);
						layoutTable.putConstraint(SpringLayout.SOUTH, betLabel, -5, SpringLayout.SOUTH, table);

						// Deal the house's cards: hole then extra
						house.hit(deck.deal());
						house.hit(deck.deal());
						Card c = house.getCards().get(0);
						String path = "/" + c.getValue() + c.getSuit() + ".png";
						try {
							holeCardFaceUp = ImageIO.read(BlackjackGUI.class.getResource(path));
						} catch (IOException exc) {
							exc.printStackTrace();
						}

						if (house.getSum().length > 1 && house.getSum()[1] == 21) {
							holeLabel = new JLabel(new ImageIcon(holeCardFaceUp));
						} else {
							holeLabel = new JLabel(new ImageIcon(deckImage));
						}
						table.add(holeLabel);

						layoutTable.putConstraint(SpringLayout.WEST, holeLabel, 146, SpringLayout.EAST, deckLabel);
						layoutTable.putConstraint(SpringLayout.NORTH, holeLabel, 5, SpringLayout.NORTH, table);

						JLabel otherCardLabel;
						c = house.getCards().get(1);
						path = "/" + c.getValue() + c.getSuit() + ".png";
						BufferedImage dealerCardImage = null;
						try {
							dealerCardImage = ImageIO.read(BlackjackGUI.class.getResource(path));
						} catch (IOException exc) {
							exc.printStackTrace();
						}
						otherCardLabel = new JLabel(new ImageIcon(dealerCardImage));
						layoutTable.putConstraint(SpringLayout.WEST, otherCardLabel, 36, SpringLayout.WEST, holeLabel);
						layoutTable.putConstraint(SpringLayout.NORTH, otherCardLabel, 5, SpringLayout.NORTH, table);
						dealerCard = otherCardLabel;
						table.add(otherCardLabel);

						// users cards
						player.hit(deck.deal());
						player.hit(deck.deal());

						JLabel firstPlayerCardLabel;
						c = player.getCards().get(0);
						path = "/" + c.getValue() + c.getSuit() + ".png";
						BufferedImage playerCard = null;
						try {
							playerCard = ImageIO.read(BlackjackGUI.class.getResource(path));
						} catch (IOException exc) {
							exc.printStackTrace();
						}
						firstPlayerCardLabel = new JLabel(new ImageIcon(playerCard));
						userCard = firstPlayerCardLabel;
						layoutTable.putConstraint(SpringLayout.WEST, firstPlayerCardLabel, 219, SpringLayout.WEST,
								table);
						layoutTable.putConstraint(SpringLayout.NORTH, firstPlayerCardLabel, 98, SpringLayout.SOUTH,
								dealerCard);
						table.add(firstPlayerCardLabel);

						JLabel secondPlayerCardLabel;
						c = player.getCards().get(1);
						path = "/" + c.getValue() + c.getSuit() + ".png";
						try {
							playerCard = ImageIO.read(BlackjackGUI.class.getResource(path));
						} catch (IOException exc) {
							exc.printStackTrace();
						}
						secondPlayerCardLabel = new JLabel(new ImageIcon(playerCard));
						layoutTable.putConstraint(SpringLayout.WEST, secondPlayerCardLabel, 36, SpringLayout.WEST,
								userCard);
						layoutTable.putConstraint(SpringLayout.NORTH, secondPlayerCardLabel, 98, SpringLayout.SOUTH,
								dealerCard);
						userCard = secondPlayerCardLabel;
						table.add(secondPlayerCardLabel);

						z = table.getComponentCount() - 1;
						table.setComponentZOrder(holeLabel, z--);
						table.setComponentZOrder(otherCardLabel, z--);
						table.setComponentZOrder(firstPlayerCardLabel, z--);
						table.setComponentZOrder(secondPlayerCardLabel, z--);

						score.setForeground(Color.BLACK);
						score.setText("You have " + player.display());
						reset.setEnabled(false);
						houseScore.setText("");
					} catch (Exception exc) {
						JOptionPane.showMessageDialog(table, "Enter a valid bet.");
					}
				}
			}
		}

		private class MoveHoleCard implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Fire! " + offsetHole);
				layoutTable.removeLayoutComponent(deckLabel);
				layoutTable.putConstraint(SpringLayout.WEST, holeLabel, offsetHole, SpringLayout.EAST, deckLabel);
				layoutTable.putConstraint(SpringLayout.NORTH, holeLabel, 5, SpringLayout.NORTH, table);

				table.validate();
				table.repaint();

				offsetHole++;

				if (offsetHole == 146) {
					timeHole.stop();
				}
			}

		}

	}

	private class SurrenderButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(surrender)) {
				int b = bet / 2;

				reset.setEnabled(true);

				reset.doClick();

				reset.setEnabled(false);

				result.setText("You surrendered $" + b);
				player.subtractMoney(b);
			}

		}

	}
}
