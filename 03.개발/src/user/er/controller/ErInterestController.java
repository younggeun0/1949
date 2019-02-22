package user.er.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import user.dao.ErDAO;
import user.er.view.ErDetailEeView;
import user.er.view.ErInterestView;
import user.er.vo.DetailEeInfoVO;
import user.er.vo.ErHiringForInterestVO;

public class ErInterestController extends WindowAdapter implements MouseListener {

	private ErInterestView eriv;
	private String er_id;
	private ErDAO er_dao;
	private final int DBL_CLICK = 2;

	public ErInterestController(ErInterestView eriv, String er_id) {
		this.eriv = eriv;
		this.er_id = er_id;
		er_dao = ErDAO.getInstance();
		setDTM(er_id);
	}// 생성자

	@Override
	public void windowClosing(WindowEvent e) {
		eriv.dispose(); // 종료처리
	}// windowClosing(WindowEvent e)

	/**
	 * 로그인 되어있는 기업사용자의 id를 사용해 DB에서 관심구직자 목록을 가져와 화면에 목록으로 출력하는 일.
	 * 
	 * @param er_id
	 */
	private void setDTM(String er_id) {
		DefaultTableModel dtm = eriv.getDtmEeInfo();
		dtm.setRowCount(0); // DTM 0으로 초기화.

		try {
			// DB에서 관심회사를 조회.
			List<ErHiringForInterestVO> list = er_dao.selectInterestEEInfoList(er_id);

			StringBuffer interestCnt = new StringBuffer("내 관심 구직자 수 : ");
			interestCnt.append(String.valueOf(list.size())).append(" 개");
			eriv.getJlEeInfo().setText(interestCnt.toString());

			// JTable에 조회한 정보를 출력.
			ErHiringForInterestVO erhForInterest = null;
			String imgPath = "C:/dev/1949/03.개발/가데이터/구직자사진/150x200px/";

			Object[] rowData = null;
			for (int i = 0; i < list.size(); i++) {

				/* list에 담겨진 VO객체로 EeInterestVO객체 생성하기 */
				erhForInterest = list.get(i);

				// DTM에 데이터를 추가하기 위한 일차원배열(Vector)을 생성하고 데이터를 추가
				rowData = new Object[11];
				rowData[0] = new Integer(i + 1);
				rowData[1] = erhForInterest.getEe_num();
				rowData[2] = new ImageIcon(imgPath + erhForInterest.getImg());
				rowData[3] = erhForInterest.getName();
				rowData[4] = (erhForInterest.getRank().equals("N") ? "경력" : "신입");
				rowData[5] = erhForInterest.getLoc();
				rowData[6] = erhForInterest.getEducation();
				rowData[7] = new Integer(erhForInterest.getAge());
				rowData[8] = (erhForInterest.getPortfolio().equals("Y") ? "있음" : "없음");
				rowData[9] = (erhForInterest.getGender().equals("M") ? "남자" : "여자");
				rowData[10] = erhForInterest.getInput_date();

				// DTM에 추가
				dtm.addRow(rowData);
			} // end for

			if (list.isEmpty()) {// 등록한 메뉴가 없을 때 : 도시락 추가 버튼을 통해 메뉴를 추가 할 수 있다.
				JOptionPane.showMessageDialog(eriv, "관심구인정보가 없습니다. 먼저 구인정보에서 하트를 눌러주세요.");
			} // end if

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(eriv, "DB에서 데이터를 받아오는 중 문제 발생...");
			e.printStackTrace();
		} // end catch

	}// setDTM

	/**
	 * 더블클릭했을 때 실행될 메소드.
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		switch (me.getClickCount()) {
		case DBL_CLICK:
			if (me.getSource() == eriv.getJtEeInfo()) {
				showDetailErInfo();
			} // end if
		}// end switch
	}// mouseClicked

	public void showDetailErInfo() {
		JTable jt = eriv.getJtEeInfo();
		String eeNum = String.valueOf(jt.getValueAt(jt.getSelectedRow(), 1));
		DetailEeInfoVO devo = null;
		try {
			devo = er_dao.selectDetailEe(eeNum, er_id);
			String ee_num = ((String) jt.getValueAt(jt.getSelectedRow(), 1));
			ErDetailEeView erdev = new ErDetailEeView(eriv, devo, ee_num, er_id, "1");

			// ErDetailEeView 객체가 동작을 멈추면 true발생
			if (erdev.isActive()) {
				setDTM(er_id);
			} // end if

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// showDetailErInfo()

	////////// 안쓰는 메소드 //////////
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	////////// 안쓰는 메소드 //////////

}// class
