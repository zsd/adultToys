package com.zsd.comm.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 导出Excel工具.
 * @author xierh
 */
public class ExcelExportFacade<T> {
	
	/**
	 * 标题的样式
	 * @param workbook
	 * @return
	 */
	public HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {
		HSSFCellStyle titleStyle = workbook.createCellStyle();

//		titleStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
//		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 15);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		titleStyle.setFont(font);

		return titleStyle;
	}
	
	/**
	 * 列名的样式
	 * @param workbook
	 * @return
	 */
	public HSSFCellStyle getColumnStyle(HSSFWorkbook workbook) {
		HSSFCellStyle columnStyle = workbook.createCellStyle();

		columnStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		columnStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		columnStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		columnStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		columnStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		columnStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		columnStyle.setFont(font);

		return columnStyle;
	}
	
	/**
	 * 值的样式
	 * @param workbook
	 * @return
	 */
	public HSSFCellStyle getValueStyle(HSSFWorkbook workbook) {
		HSSFCellStyle valueStyle = workbook.createCellStyle();
		valueStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		valueStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		valueStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		valueStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		valueStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		return valueStyle;
	}
	
	/**
	 * 无结果记录的样式
	 * @param workbook
	 * @return
	 */
	public HSSFCellStyle noResultStyle(HSSFWorkbook workbook) {
		HSSFCellStyle noResultStyle = workbook.createCellStyle();
		noResultStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		noResultStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		noResultStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		noResultStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		noResultStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		noResultStyle.setFont(font);
		
		return noResultStyle;	
	}

    /**
     * excel的导出，不需要标题
     * @param title 标题
     * @param headers 表头
     * @param props 所要获取值的属性
     * @param dataList map集合
     * @param os
     */
	public void exportExcel(String title, String[] headers, String[] props,
			List<T> dataList, OutputStream os) {
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);

		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth(20);
		
//		HSSFCellStyle titleStyle = this.getTitleStyle(workbook);
		HSSFCellStyle columnStyle = this.getColumnStyle(workbook);
		HSSFCellStyle valueStyle = this.getValueStyle(workbook);
		HSSFCellStyle noResultStyle = this.noResultStyle(workbook);

		// 加入列的信息
		HSSFRow columnRow = sheet.createRow(0);
		for (int columnIndex = 0; columnIndex < headers.length; columnIndex++) {
			HSSFCell columnCell = columnRow.createCell(columnIndex);
			columnCell.setCellStyle(columnStyle);
			HSSFRichTextString columnText = new HSSFRichTextString(headers[columnIndex]);
			columnCell.setCellValue(columnText);
		}

		// 加入具体的值
		int row = 0;
		boolean exists = false;
		if (CollectionUtils.isNotEmpty(dataList)) {
			exists = true;
			for (T t : dataList) {
				row++;
				HSSFRow valueRow = sheet.createRow(row);
				for (int valueIndex = 0; valueIndex < props.length; valueIndex++) {
					try {
						HSSFCell valueCell = valueRow.createCell(valueIndex);
						String getName = "get" + props[valueIndex].substring(0, 1).toUpperCase() + props[valueIndex].substring(1);
						Class clz = t.getClass();
						Method method = clz.getDeclaredMethod(getName, new Class[] {});
						method.setAccessible(true);
						String valueObj = (String) method.invoke(t, new Object[] {});

						HSSFRichTextString valueText = new HSSFRichTextString(valueObj);
						valueCell.setCellStyle(valueStyle);
						valueCell.setCellValue(valueText);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}

				}
			}
		}
		
		//如果无结果记录存在, 则最后一行全合并, 并且展示 "无结果存在！"
		if(!exists) {
			row++;
			HSSFRow valueRow = sheet.createRow(row);
			for(int index = 0; index < headers.length; index++) {
				HSSFCell valueCell = valueRow.createCell(index);
				valueCell.setCellStyle(noResultStyle);
				valueCell.setCellValue("");
			}
			sheet.addMergedRegion(new CellRangeAddress(row, row, 0, headers.length - 1));
			sheet.getNumMergedRegions();
			HSSFRichTextString valueText = new HSSFRichTextString("无记录存在！");
			sheet.getRow(row).getCell(0).setCellStyle(noResultStyle);
			sheet.getRow(row).getCell(0).setCellValue(valueText);
		}
		
		try {
			workbook.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * excel的导出，不需要标题
     * @param title 标题
     * @param headers 表头
     * @param props 所要获取值的属性
     * @param mapList map集合
     * @param os
     */
    public void exportMapExcel(String title, String[] headers, String[] props, List<Map<String, Object>> mapList,
                                      OutputStream os) {
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(20);

//		HSSFCellStyle titleStyle = this.getTitleStyle(workbook);
        HSSFCellStyle columnStyle = this.getColumnStyle(workbook);
        HSSFCellStyle valueStyle = this.getValueStyle(workbook);
        HSSFCellStyle noResultStyle = this.noResultStyle(workbook);

        // 加入列的信息
        HSSFRow columnRow = sheet.createRow(0);
        for (int columnIndex = 0; columnIndex < headers.length; columnIndex++) {
            HSSFCell columnCell = columnRow.createCell(columnIndex);
            columnCell.setCellStyle(columnStyle);
            HSSFRichTextString columnText = new HSSFRichTextString(headers[columnIndex]);
            columnCell.setCellValue(columnText);
        }

        // 加入具体的值
        int row = 0;
        boolean exists = false;
        if (CollectionUtils.isNotEmpty(mapList) && mapList.size() > 0) {
            exists = true;
            for (Map<String, Object> t : mapList) {
                row++;
                HSSFRow valueRow = sheet.createRow(row);
                for (int valueIndex = 0; valueIndex < props.length; valueIndex++) {
                    try {
                        HSSFCell valueCell = valueRow.createCell(valueIndex);
                        Object valueObj = t.get(props[valueIndex]);
                        if (ObjectUtils.equals(valueObj, null)) {
                            valueObj = "";
                        }
                        HSSFRichTextString valueText = new HSSFRichTextString(valueObj.toString());
                        valueCell.setCellStyle(valueStyle);
                        valueCell.setCellValue(valueText);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        //如果无结果记录存在, 则最后一行全合并, 并且展示 "无结果存在！"
        if(!exists) {
            row++;
            HSSFRow valueRow = sheet.createRow(row);
            for(int index = 0; index < headers.length; index++) {
                HSSFCell valueCell = valueRow.createCell(index);
                valueCell.setCellStyle(noResultStyle);
                valueCell.setCellValue("");
            }
            sheet.addMergedRegion(new CellRangeAddress(row, row, 0, headers.length - 1));
            sheet.getNumMergedRegions();
            HSSFRichTextString valueText = new HSSFRichTextString("无记录存在！");
            sheet.getRow(row).getCell(0).setCellStyle(noResultStyle);
            sheet.getRow(row).getCell(0).setCellValue(valueText);
        }

        try {
            workbook.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * excel的导出，不需要标题
     * @param workbook excel工作薄
     * @param title 标题
     * @param headers 表头
     * @param props 所要获取值的属性
     * @param mapList map集合
     */
    public void exportWorkbookExcel(HSSFWorkbook workbook, String title, String[] headers,
                                    String[] props, List<Map<String, Object>> mapList) {
        HSSFSheet sheet = null;
        // 生成一个表格
        sheet = workbook.createSheet(title);

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(20);

        HSSFCellStyle columnStyle = this.getColumnStyle(workbook);
        HSSFCellStyle valueStyle = this.getValueStyle(workbook);
        HSSFCellStyle noResultStyle = this.noResultStyle(workbook);

        // 加入列的信息
        HSSFRow columnRow = sheet.createRow(0);
        for (int columnIndex = 0; columnIndex < headers.length; columnIndex++) {
            HSSFCell columnCell = columnRow.createCell(columnIndex);
            columnCell.setCellStyle(columnStyle);
            HSSFRichTextString columnText = new HSSFRichTextString(headers[columnIndex]);
            columnCell.setCellValue(columnText);
        }

        // 加入具体的值
        int row = 0;
        boolean exists = false;
        if (CollectionUtils.isNotEmpty(mapList) && mapList.size() > 0) {
            exists = true;
            for (Map<String, Object> t : mapList) {
                row++;
                HSSFRow valueRow = sheet.createRow(row);
                for (int valueIndex = 0; valueIndex < props.length; valueIndex++) {
                    try {
                        HSSFCell valueCell = valueRow.createCell(valueIndex);
                        Object valueObj = t.get(props[valueIndex]);
                        if (ObjectUtils.equals(valueObj, null)) {
                            valueObj = "";
                        }
                        HSSFRichTextString valueText = new HSSFRichTextString(valueObj.toString());
                        valueCell.setCellStyle(valueStyle);
                        valueCell.setCellValue(valueText);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        //如果无结果记录存在, 则最后一行全合并, 并且展示 "无结果存在！"
        if(!exists) {
            row++;
            HSSFRow valueRow = sheet.createRow(row);
            for(int index = 0; index < headers.length; index++) {
                HSSFCell valueCell = valueRow.createCell(index);
                valueCell.setCellStyle(noResultStyle);
                valueCell.setCellValue("");
            }
            sheet.addMergedRegion(new CellRangeAddress(row, row, 0, headers.length - 1));
            sheet.getNumMergedRegions();
            HSSFRichTextString valueText = new HSSFRichTextString("无记录存在！");
            sheet.getRow(row).getCell(0).setCellStyle(noResultStyle);
            sheet.getRow(row).getCell(0).setCellValue(valueText);
        }


    }
}