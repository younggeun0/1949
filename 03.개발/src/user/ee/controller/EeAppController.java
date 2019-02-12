package user.ee.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import user.dao.EeDAO;
import user.ee.view.EeAppView;
import user.ee.view.EeDetailErView;
import user.ee.vo.DetailErInfoVO;
import user.ee.vo.EeInterestVO;

public class EeAppController extends WindowAdapter implements MouseListener {

	private EeAppView eav;
	private EeDAO ee_dao;

	public EeAppController(EeAppView eav) {
		this.eav = eav;
		EeDAO ee_dao = EeDAO.getInstance();
	}// 생성자

	private void setDTM() {
//		DefaultTableModel dtmErInfo = eav.getDtmEr();
//		dtmErInfo.setRowCount(0); // DTM 0으로 초기화.
//
//		try {
//			// DB에서 관심회사를 조회.
//			List<EeInterestVO> list = ee_dao.
//
////			// JTable에 조회한 정보를 출력.
////			 eeappvo = null;
////
////			Object[] rowData = null;
////			for (int i = 0; i < list.size(); i++) {
////				/* list에 담겨진 VO객체로 EeInterestVO객체 생성하기 */
////				eivo = list.get(i);
////				// DTM에 데이터를 추가하기 위한 일차원배열(Vector)을 생성하고 데이터를 추가
////				rowData = new Object[11];
////
////				// DTM에 추가
////				dtmErInfo.addRow(rowData);
////			} // end for
//
//			if (list.isEmpty()) {// 등록한 메뉴가 없을 때 : 도시락 추가 버튼을 통해 메뉴를 추가 할 수 있다.
//				JOptionPane.showMessageDialog(eav, "관심구인정보가 없습니다. 먼저 구인정보에서 하트를 눌러주세요.");
//			} // end if
//
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(eav, "DB에서 데이터를 받아오는 중 문제 발생...");
//			e.printStackTrace();
//		} // end catch

	}// setDTM

	@Override
	public void windowClosing(WindowEvent e) {
	}// windowClosing

	@Override
	public void mouseClicked(MouseEvent e) {
//		switch (me.getClickCount()) { // 클릭 횟수 비교.
//		case DBL_CLICK: // 더블 클릭 시
//			if (me.getSource() == eiv.getjtErInfo()) { // 테이블 더블클릭 시.
//				JTable jt = eiv.getjtErInfo();
//				String erNum= String.valueOf(jt.getValueAt(jt.getSelectedRow(), 1));
//				DetailErInfoVO deivo=null;
//				try {
//					deivo = ee_dao.selectDetail(erNum, ee_id);
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				EeDetailErView edev = new EeDetailErView(null, deivo, erNum, "gong1",null, deivo.getInterest());
//			} // end if
//		} // end switch
	}// mouseClicked

	/////////////////////////////// 안 쓰는 메소드들 ///////////////////////////////
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
}// class
