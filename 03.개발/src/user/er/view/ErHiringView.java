package user.er.view;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import user.er.controller.ErHiringController;
import user.er.vo.ErHiringVO;

@SuppressWarnings("serial")
public class ErHiringView extends JDialog {


	private JButton jbDetailSearch, jbSelectAll;
	private JComboBox<String> jcbSort;
	private JTable jtEeInfo;
	private DefaultTableModel dtmEeInfo;
	
	
	public ErHiringView(ErMainView rmv, List<ErHiringVO> list, String erId) {
		super(rmv,"구직자 정보 보기",true);
		erId="lucky012";		
		String[] erColumns= {"번호","기본정보번호","이미지","이름","직급","근무지역","학력","나이","포트폴리오 유무","성별","등록일"};
		dtmEeInfo=new DefaultTableModel(erColumns, 40) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String sort[]= {"등록일순","직급순"};
		jcbSort=new JComboBox<String>(sort);
		
		jtEeInfo=new JTable(dtmEeInfo);
		JScrollPane jspEe=new JScrollPane(jtEeInfo);
		
		jbDetailSearch=new JButton("조건검색");
		jbSelectAll = new JButton("전체목록");
		
		setLayout(null);
		
		jcbSort.setBounds(10, 10, 100, 30);
		jbDetailSearch.setBounds(880, 10, 100, 30);
		jbSelectAll.setBounds(775, 10, 100, 30);
		jspEe.setBounds(0, 50, 995, 450);
		
		add(jcbSort);
		add(jspEe);
		add(jbDetailSearch);
		add(jbSelectAll);
		/////이벤트//////
		ErHiringController ehc = new ErHiringController(this,erId);
		jbDetailSearch.addActionListener(ehc);
		jcbSort.addActionListener(ehc);
		jtEeInfo.addMouseListener(ehc);
		jbSelectAll.addActionListener(ehc);
		addWindowListener(ehc);
		
		setBounds(100, 100, 1000, 500);
		setResizable(false);
		setVisible(true);
		
	}//ErHiringView

	public JButton getJbDetailSearch() {
		return jbDetailSearch;
	}

	public JComboBox<String> getJcbSort() {
		return jcbSort;
	}

	public JTable getJtEeInfo() {
		return jtEeInfo;
	}

	public DefaultTableModel getDtmEeInfo() {
		return dtmEeInfo;
	}

	public JButton getJbSelectAll() {
		return jbSelectAll;
	}

	public static void main(String[] args) {
		new ErHiringView(null, null, "moonlight");
	}
	
	
}//class















