package user.ee.view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *	 기본 정보 관리 -김건하-
 *	19.02.07
 * @author owner
 */
public class EeInfoRegView extends JDialog {

	private JButton jbRegister, jbRegisterExt, jbRegisterImg, jbClose;
	private JComboBox<String>jcbRank, jcbLoc, jcbEducation, jcbPortfolio;
	private JTextField jtfExtResume;
	
	public EeInfoRegView() {
//		super("기본 정보 관리",true);
		
		//image
		ImageIcon ii=new ImageIcon("C:/dev/homework/1949/1949/03.개발/가데이터/구직자사진/150x200px/각키.jpg");
		JLabel jlImage=new JLabel(ii);
		jlImage.setBorder(new TitledBorder("구직자 이미지"));
		jlImage.setBounds(38, 20, 160, 225);
		add(jlImage);
		
		jbRegisterImg=new JButton("이미지 변경");
		add(jbRegisterImg);
		jbRegisterImg.setBounds(42, 260, 150, 30);
		
		jbRegisterExt = new JButton("외부이력서 등록");
		add(jbRegisterExt);
		jbRegisterExt.setBounds(50, 360, 150, 30);
		
		jbRegister = new JButton("등록");
		add(jbRegister);
		jbRegister.setBounds(240, 360, 100, 30);
		
		jbClose=new JButton("닫기");
		add(jbClose);
		jbClose.setBounds(355, 360, 100, 30);
				
		
		
		//Label
		JLabel jlName=new JLabel("이름");
		jlName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		
		JLabel jlRank=new JLabel("직급");
		jlRank.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		
		JLabel jlLoc=new JLabel("근무지역");
		jlLoc.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		
		JLabel jlEdu=new JLabel("학력");
		jlEdu.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		
		JLabel jlAge=new JLabel("나이");
		jlAge.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		
		JLabel jlPort=new JLabel("포트폴리오 유무");
		jlPort.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		
		JLabel jlGender=new JLabel("성별");
		jlGender.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		
		JLabel jlResume=new JLabel("외부이력서");
		jlResume.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		
		
		//Combobox  jcbRank, jcbLoc, jcbEducation, jcbPortfolio;
		String[] rank= {"신입", "경력"};
		jcbRank =new JComboBox<>(rank);
		String[] loc= {"서울", "경기", "인천", "대전", "세종", "충남", "충북", "광주", "전남", "전북", "대구", "경북", "부산", "울산", "경남",
				"강원", "제주", "전국"};
		jcbLoc=new JComboBox<>(loc);
		String[] edu= {"고졸", "초대졸", "대졸", "석사", "박사"};
		jcbEducation=new JComboBox<>(edu);
		String[] port= {"YES", "NO"};
		jcbPortfolio=new JComboBox<>(port);
		
		//Label
		add(jlName);
		jlName.setBounds(265, 20, 50, 20);
		add(jlRank);
		jlRank.setBounds(265, 60, 50, 20);
		add(jlLoc);
		jlLoc.setBounds(255, 100, 70, 20);
		add(jlEdu);
		jlEdu.setBounds(265, 138, 50, 30);
		add(jlAge);
		jlAge.setBounds(265, 175, 50, 30);
		add(jlPort);
		jlPort.setBounds(215, 215, 100, 30);
		add(jlGender);
		jlGender.setBounds(265, 255, 50, 30);
		add(jlResume);
		jlResume.setBounds(235, 295, 100, 30);
		
		//JText
		JTextField jtfName=new JTextField(10);
		add(jtfName);
		jtfName.setBounds(325, 22, 130, 20);
		
		JTextField jtfAge=new JTextField(10);
		add(jtfAge);
		jtfAge.setBounds(325, 182, 130, 20);
		
		JTextField jtfGender=new JTextField(10);
		add(jtfGender);
		jtfGender.setBounds(325, 262, 130, 20);
		
		jtfExtResume=new JTextField(10);
		add(jtfExtResume);
		jtfExtResume.setBounds(325, 302, 130, 20);
		
		//Combobox
		add(jcbRank);
		jcbRank.setBounds(325,62,130,20);
		add(jcbLoc);
		jcbLoc.setBounds(325,102,130,20);
		add(jcbEducation);
		jcbEducation.setBounds(325,142,130,20);
		add(jcbPortfolio);
		jcbPortfolio.setBounds(325,222,130,20);
		
		
		
		
		setLayout(null);
		setBounds(100, 100, 490, 450);
		
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		
	}//EeInfoRegView
	public static void main(String[] args) {
		new EeInfoRegView();
	}//main
	
}//class
