import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


public class FenetreApplication extends JFrame implements ActionListener{
	CardLayout layoutCartes;
	private JPanel cartesPanel;
	
	private JPanel panelQuestion;
	private JLabel labelQuestion;
	private JButton boutonAfficherReponse;
	
	private JPanel panelReponse;
	private JLabel labelReponse;
	private JButton boutonProchaineQuestion;
	
	private JMenuBar menuBar;
	private JMenuItem menuItemOptions;
	private JMenuItem menuItemQuestion;
	private JMenuItem menuItemReponse;
	
	private JPanel panelOption;
	private JLabel labelNumMin;
	private JSpinner SpinNumMin;
	private JLabel labelNumMax;
	private JSpinner SpinNumMax;
	
	private Map<AbstractButton, Integer> mapSourceAction;
	
	private Paquet paquet;
	private Carte carte;
	private int numMotMin,numMotMax;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private void loadNextQuestion(){
		int randomNum = ThreadLocalRandom.current().nextInt(numMotMin, numMotMax);
		carte=paquet.getCarte(randomNum);
		labelQuestion.setText(carte.getQuestion());
		layoutCartes.show(cartesPanel, "question");
		labelReponse.setText(carte.getReponse());
	}
	
	private void initGUI(){
		this.setTitle("aide Japonais");
		this.setSize(600, 480);
		layoutCartes=new CardLayout();
		cartesPanel=new JPanel(layoutCartes);
		initCarteQuestion();
		
		initCarteReponse();
		
		initMenuBar();
		
		initCarteOption();
		
		this.add(cartesPanel);
		layoutCartes.show(cartesPanel, "question");
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void initCarteQuestion(){
		panelQuestion= new JPanel();
		panelQuestion.setLayout(new BorderLayout());
		labelQuestion= new JLabel("日本語");
		labelQuestion.setHorizontalAlignment(JLabel.CENTER);
		labelQuestion.setFont(new Font("Serif", Font.PLAIN, 36));
		panelQuestion.add(labelQuestion,BorderLayout.CENTER);
		boutonAfficherReponse= new JButton("réponse");
		boutonAfficherReponse.addActionListener(this);
		panelQuestion.add(boutonAfficherReponse,BorderLayout.SOUTH);
		cartesPanel.add(panelQuestion,"question");
	}
	
	private void initCarteReponse(){
		panelReponse=new JPanel();
		panelReponse.setLayout(new BorderLayout());
		labelReponse= new JLabel("Japonais");
		labelReponse.setHorizontalAlignment(JLabel.CENTER);
		labelReponse.setFont(new Font("Serif", Font.PLAIN, 24));
		panelReponse.add(labelReponse,BorderLayout.CENTER);
		boutonProchaineQuestion=new JButton("prochaine question");
		boutonProchaineQuestion.addActionListener(this);
		panelReponse.add(boutonProchaineQuestion,BorderLayout.SOUTH);
		cartesPanel.add(panelReponse,"réponse");
	}
	
	private void initCarteOption(){
		panelOption=panelReponse=new JPanel();
		panelOption.setLayout(new BoxLayout(panelOption, BoxLayout.Y_AXIS));
		panelOption.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
		labelNumMin=new JLabel("rang occurence minimale");
		panelOption.add(labelNumMin);
		SpinNumMin=new JSpinner();
		panelOption.add(SpinNumMin);
		labelNumMax=new JLabel("rang occurence maximale");
		panelOption.add(labelNumMax);
		SpinNumMax=new JSpinner();
		panelOption.add(SpinNumMax);
		cartesPanel.add(panelOption,"option");
	}
	
	private void initMenuBar(){
		menuBar=new JMenuBar();
		menuItemOptions=new JMenuItem("options");
		menuItemOptions.addActionListener(this);
		menuBar.add(menuItemOptions);
		menuItemQuestion=new JMenuItem("question");
		menuItemQuestion.addActionListener(this);
		menuBar.add(menuItemQuestion);
		menuItemReponse=new JMenuItem("réponse");
		menuItemReponse.addActionListener(this);
		menuBar.add(menuItemReponse);
		this.setJMenuBar(menuBar);
	}
	
	private void initMap(){
		mapSourceAction=new HashMap<AbstractButton, Integer>();
		mapSourceAction.put(boutonAfficherReponse, 0);
		mapSourceAction.put(boutonProchaineQuestion, 1);
		mapSourceAction.put(menuItemOptions, 2);
		mapSourceAction.put(menuItemQuestion, 3);
		mapSourceAction.put(menuItemReponse, 4);
	}
	
	
	public FenetreApplication(){
		paquet=new Paquet("listeMots.xml");
		numMotMin=0;
		numMotMax=paquet.taillePaquet();
		initGUI();
		initMap();
		loadNextQuestion();
	}

	public static void main(String[] args) throws IOException {
		FenetreApplication fen= new FenetreApplication();
		//LecteurBase lecb=new LecteurBase();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(mapSourceAction.containsKey(arg0.getSource())){
			switch(mapSourceAction.get(arg0.getSource())){
			case 0:
				layoutCartes.show(cartesPanel, "réponse");
			break;
			case 1:
				loadNextQuestion();
			break;
			case 2:
				layoutCartes.show(cartesPanel, "option");
			break;
			case 3:
				layoutCartes.show(cartesPanel, "question");
			break;
			case 4:
				layoutCartes.show(cartesPanel, "réponse");
			break;
			}
		}
		if(arg0.getSource() == boutonAfficherReponse)
			layoutCartes.show(cartesPanel, "réponse");
		else{
			if(arg0.getSource() ==boutonProchaineQuestion)
				loadNextQuestion();
		}
	}
}
