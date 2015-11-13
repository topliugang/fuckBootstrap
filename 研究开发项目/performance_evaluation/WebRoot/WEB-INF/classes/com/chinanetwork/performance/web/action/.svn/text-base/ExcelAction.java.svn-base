package com.chinanetwork.performance.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.chinanetwork.performance.bean.Staff;
import com.chinanetwork.performance.service.StaffManageService;
import com.chinanetwork.performance.util.DataBaseUtil;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
//import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelAction {

	private InputStream is;

	private StaffManageService staffManageService = new StaffManageService();

	public String execute() throws Exception {

		WritableWorkbook workbook;
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		List<Staff> staffs = staffManageService.getAllStaffs();
		workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("Sheet1", 0);

		sheet.addCell(new jxl.write.Label(0, 0, "工号"));
		sheet.addCell(new jxl.write.Label(1, 0, "名字"));
		sheet.addCell(new jxl.write.Label(2, 0, "性别"));
		sheet.addCell(new jxl.write.Label(3, 0, "部门"));
		sheet.addCell(new jxl.write.Label(4, 0, "职位"));
		sheet.addCell(new jxl.write.Label(5, 0, "角色"));

		for (Staff staff : staffs) {
			int i = staffs.indexOf(staff);
			i=i+1;
			sheet.addCell(new jxl.write.Label(0, i, String.valueOf(staff
					.getStaffId())));
			sheet.addCell(new jxl.write.Label(1, i, staff.getStaffName()));
			sheet.addCell(new jxl.write.Label(2, i, staff.getStaffSex()));
			sheet.addCell(new jxl.write.Label(3, i, staff.getDepartment()
					.getDepartmentName()));
			sheet.addCell(new jxl.write.Label(4, i, staff.getPost()
					.getPostName()));
			sheet.addCell(new jxl.write.Label(5, i, staff.getRole()
					.getRoleName()));
		}
		workbook.write();
		workbook.close();
		is = new ByteArrayInputStream(os.toByteArray());
		return "excel";
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	private File excel;

	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public String importExcel() throws Exception {
		importExcelFile("excel.xls");
		importExcelFile("excel2.xls");
		return "success";
	}

	void importExcelFile(String fileName) throws Exception
	{
		Workbook wb = null;
		try {
			InputStream is = new FileInputStream(fileName);
			wb = Workbook.getWorkbook(is);
			Sheet[] sheets = wb.getSheets(); // 获取工作

			for (Sheet sheet : sheets) {

				// table name
				String tableName = sheet.getName();
				System.out.println("inserting table : " + tableName + "\n");
				// column names
				List<String> columnNames = new ArrayList<String>();
				System.out.println("table head : " );
				for (int i = 0; i < sheet.getColumns(); i++) {
					columnNames.add(sheet.getCell(i, 0).getContents());
					System.out.println(sheet.getCell(i, 0).getContents()+" ,");
				}
				System.out.println("\n");

				ArrayList<ArrayList<String>> contents = new ArrayList<ArrayList<String>>();

				int sb = sheet.getRows();
				System.out.println("row number: " + sb);
				for (int i = 1; i < sheet.getRows(); i++) {
					ArrayList<String> item = new ArrayList<String>();
					
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(j, i);
						if (cell.getType().equals(CellType.LABEL)
								|| cell.getType().equals(
										CellType.STRING_FORMULA)
								|| cell.getType().equals(CellType.DATE)
								|| cell.getType().equals(CellType.DATE_FORMULA)
								|| cell.getType().equals(CellType.EMPTY))
							item.add("'" + cell.getContents() + "'");
						else
							item.add(cell.getContents());
					}
					if(item.get(0).equals("''") || item.get(0) == null || item.get(0) == "")
						break;
					else
						contents.add(item);
				}
				String sql = new String();
				for (ArrayList<String> item : contents) {
					String innersql = "insert into " + tableName + "( ";
					// for (String column : columnNames) {
					for (int k = 0; k < columnNames.size(); k++) {
						String column = columnNames.get(k);
						if (k == columnNames.size() - 1)
							innersql += column;
						else
							innersql += column + ", ";
					}
					innersql += ") values( ";
					for (int k = 0; k < item.size(); k++) {
						String cell = item.get(k);
						if (k == item.size() - 1)
							innersql += cell;
						else
							innersql += cell + ", ";
					}
					innersql += "); ";
					System.out.println(innersql);
					DataBaseUtil.executeSQL(innersql);

					// sql += innersql;
				}

			}// xls file
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (wb != null) {
				wb.close();
			}
		}
	}


}
