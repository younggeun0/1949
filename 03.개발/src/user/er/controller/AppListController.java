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
import user.er.view.AppDetailView;
import user.er.view.AppListView;
import user.er.vo.DetailAppListVO;

public class AppListController extends WindowAdapter implements MouseListener {

	private AppListView alv;
	private String er_num;
	private ErDAO er_dao;

	private final int DBL_CLICK = 2; // 더블 클릭 상수

	public AppListController(AppListView alv, String er_num) {
		this.alv = alv;
		this.er_num = er_num;
		er_dao = ErDAO.getInstance();
		setDTM(er_num);
	}// 생성자

	/**
	 * 재현 : 지원현황 창 - 상세 지원 현황 창이 열릴 때 목록을 채우는 메소드.
	 * 
	 * @param er_num
	 */
	private void setDTM(String er_num) {

		DefaultTableModel dtm = alv.getDtmEeInfo();
		dtm.setRowCount(0); // DTM 0으로 초기화.

		try {
			// DB에서 관심회사를 조회.
			List<DetailAppListVO> list = er_dao.selectDetailApplist(er_num);

			// JTable에 조회한 정보를 출력.
			DetailAppListVO dalvo = null;
			String imgPath = "C:/dev/1949/03.개발/가데이터/구직자사진/150x200px/";

			Object[] rowData = null;
			for (int i = 0; i < list.size(); i++) {

				/* list에 담겨진 VO객체로 EeInterestVO객체 생성하기 */
				dalvo = list.get(i);

				// DTM에 데이터를 추가하기 위한 일차원배열(Vector)을 생성하고 데이터를 추가
				rowData = new Object[12];
				rowData[0] = new Integer(i + 1);
				rowData[1] = dalvo.getApp_num();
				rowData[2] = new ImageIcon(imgPath + dalvo.getImg());
				rowData[3] = dalvo.getName();
				rowData[4] = (dalvo.getRank().equals("N") ? "신입" : "경력");
				rowData[5] = dalvo.getLoc();
				rowData[6] = dalvo.getEducation();
				rowData[7] = dalvo.getAge();
				rowData[8] = (dalvo.getPortfolio().equals("Y") ? "존재" : "없음");
				rowData[9] = (dalvo.getGender().equals("M") ? "남자" : "여자");
				rowData[10] = dalvo.getApp_date();
				switch (dalvo.getApp_status()) {
				case "U":
					rowData[11] = "응답대기";
					break;
				case "R":
					rowData[11] = "열람";
					break;
				case "A":
					rowData[11] = "지원수락";
					break;
				case "D":
					rowData[11] = "지원거절";
				}// end switch

				// DTM에 추가
				dtm.addRow(rowData);
			} // end for

			if (list.isEmpty()) {// 등록한 메뉴가 없을 때 : 도시락 추가 버튼을 통해 메뉴를 추가 할 수 있다.
				JOptionPane.showMessageDialog(alv, "등록한 구인공고에 아직 지원한 구직자가 없습니다.");
			} // end if

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(alv, "DB에서 데이터를 받아오는 중 문제 발생...");
			e.printStackTrace();
		} // end catch

	}// setDTM

	@Override
	public void windowClosing(WindowEvent e) {
		alv.dispose();
	}// windowClosing

	@Override
	public void mouseClicked(MouseEvent me) {
		switch (me.getClickCount()) {
		case DBL_CLICK:
			if (me.getSource() == alv.getJtEeInfo()) {
				JTable jt = alv.getJtEeInfo();
				String app_num = (String) jt.getValueAt(jt.getSelectedRow(), 1);
				AppDetailView adv = new AppDetailView(alv, app_num);

				// AppDetailView객체가 동작을 멈추면 true반환
				if (adv.isActive()) {
					setDTM(er_num);
				} // end if

			} // end if
		}// end switch
	}// mouseClicked

	///////////////// 안쓰는 메서드/////////////////
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
	///////////////// 안쓰는 메서드/////////////////

}// class
