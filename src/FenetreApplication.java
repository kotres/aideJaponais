import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class FenetreApplication extends JFrame implements ActionListener{
	CardLayout layoutCartes;
	private JPanel cartes;
	
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
	
	private Map<AbstractButton, Integer> mapSourceAction;
	
	private List<MotJaponais> listeMots;
	private int numMotMin,numMotMax;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private void loadNextQuestion(){
		int randomNum = ThreadLocalRandom.current().nextInt(numMotMin, numMotMax);
		labelQuestion.setText(listeMots.get(randomNum).motOriginal);
		layoutCartes.show(cartes, "question");
		labelReponse.setText(listeMots.get(randomNum).toString());
	}
	
	private void initGUI(){
		this.setTitle("aide Japonais");
		this.setSize(600, 480);
		layoutCartes=new CardLayout();
		cartes=new JPanel(layoutCartes);
		
		panelQuestion= new JPanel();
		panelQuestion.setLayout(new BorderLayout());
		labelQuestion= new JLabel("日本語");
		labelQuestion.setHorizontalAlignment(JLabel.CENTER);
		labelQuestion.setFont(new Font("Serif", Font.PLAIN, 36));
		panelQuestion.add(labelQuestion,BorderLayout.CENTER);
		boutonAfficherReponse= new JButton("réponse");
		boutonAfficherReponse.addActionListener(this);
		panelQuestion.add(boutonAfficherReponse,BorderLayout.SOUTH);
		cartes.add(panelQuestion,"question");
		
		panelReponse=new JPanel();
		panelReponse.setLayout(new BorderLayout());
		labelReponse= new JLabel("Japonais");
		labelReponse.setHorizontalAlignment(JLabel.CENTER);
		panelReponse.add(labelReponse,BorderLayout.CENTER);
		boutonProchaineQuestion=new JButton("prochaine question");
		boutonProchaineQuestion.addActionListener(this);
		panelReponse.add(boutonProchaineQuestion,BorderLayout.SOUTH);
		cartes.add(panelReponse,"réponse");
		
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
		this.add(cartes);
		layoutCartes.show(cartes, "question");
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private void initMap(){
		mapSourceAction=new HashMap<AbstractButton, Integer>();
		mapSourceAction.put(boutonAfficherReponse, 0);
		mapSourceAction.put(boutonProchaineQuestion, 1);
		mapSourceAction.put(menuItemOptions, 2);
		mapSourceAction.put(menuItemQuestion, 3);
		mapSourceAction.put(menuItemReponse, 4);
	}
	
	public FenetreApplication() throws IOException{
		listeMots=new LecteurBase().getListeMots();
		numMotMin=0;
		numMotMax=listeMots.size();
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
				layoutCartes.show(cartes, "réponse");
			break;
			case 1:
				loadNextQuestion();
			break;
			case 3:
				layoutCartes.show(cartes, "question");
			break;
			case 4:
				layoutCartes.show(cartes, "réponse");
			break;
			}
		}
		/*if(arg0.getSource() == boutonAfficherReponse)
			layoutCartes.show(cartes, "réponse");
		else{
			if(arg0.getSource() ==boutonProchaineQuestion)
				loadNextQuestion();
		}*/
	}
}
