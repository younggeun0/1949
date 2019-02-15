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
import user.ee.vo.EeAppVO;

public class EeAppController extends WindowAdapter implements MouseListener {

	private EeAppView eav;
	private EeDAO ee_dao;
	private String ee_id;
	private EeAppVO eavo;
	private final int DBL_CLICK = 2;

	public EeAppController(EeAppView eav, String ee_id) {
		this.eav = eav;
		this.ee_id = ee_id;
		ee_dao = EeDAO.getInstance();
		setDTM(ee_id);
	}// 생성자

	private void setDTM(String ee_id) {
		DefaultTableModel dtm = eav.getDtmEr();
		dtm.setRowCount(0); // DTM 0으로 초기화.

		try {
			// DB에서 관심회사를 조회.
			List<EeAppVO> list = ee_dao.selectAppList(ee_id);

			// JTable에 조회한 정보를 출력.
			eavo = null;

			Object[] rowData = null;
			for (int i = 0; i < list.size(); i++) {
				/* list에 담겨진 VO객체로 EeInterestVO객체 생성하기 */
				eavo = list.get(i);

				// DTM에 데이터를 추가하기 위한 일차원배열(Vector)을 생성하고 데이터를 추가
				rowData = new Object[12];
				rowData[0] = new Integer(i + 1);
				rowData[1] = eavo.getEr_num(); /* 값이 안들어 옴. */
				rowData[2] = eavo.getApp_num();
				rowData[3] = eavo.getSubject();
				rowData[4] = eavo.getCo_name();
				rowData[5] = eavo.getRank();
				rowData[6] = eavo.getLoc();
				rowData[7] = eavo.getEducation();
				rowData[8] = eavo.getHire_type();
				rowData[9] = new Integer(eavo.getSal());
				rowData[10] = eavo.getApp_date();
				rowData[11] = eavo.getApp_status();
				System.out.println(eavo.getApp_status());

				// DTM에 추가
				dtm.addRow(rowData);
			} // end for

			if (list.isEmpty()) {// 등록한 메뉴가 없을 때 : 도시락 추가 버튼을 통해 메뉴를 추가 할 수 있다.
				JOptionPane.showMessageDialog(eav, "지원한 현황이 없습니다. 구인정보보기에서 먼저 지원해 주세요.");
			} // end if

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(eav, "DB에서 데이터를 받아오는 중 문제 발생...");
			e.printStackTrace();
		} // end catch

	}// setDTM

	@Override
	public void windowClosing(WindowEvent e) {
		eav.dispose();
	}// windowClosing

	/**
	 * 지원현황 목록에서 한 행을 선택해 더블클릭했을 때 상세정보 보기.
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		switch (me.getClickCount()) { // 클릭 횟수 비교.
		case DBL_CLICK: // 더블 클릭 시
			if (me.getSource() == eav.getJtEr()) { // 테이블 더블클릭 시.
				showDetailErinfo();
			} // end if
		} // end switch
	}// mouseClicked

	/**
	 * 더블 클릭시 띄우는 창
	 */
	private void showDetailErinfo() {
		JTable jt = eav.getJtEr();
		String erNum = String.valueOf(jt.getValueAt(jt.getSelectedRow(), 1));
		DetailErInfoVO deivo = null;
		try {
			deivo = ee_dao.selectDetail(erNum, ee_id);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		System.out.println(deivo);
		new EeDetailErView(null, deivo, erNum, ee_id, eavo.getApp_status());
	}// showDetailErinfo

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
