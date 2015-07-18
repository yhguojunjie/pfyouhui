package com.yoxi.jgframework.extend.swftools;

/**
 * PDF文档转换接口
 * @author Administrator
 *
 */
public interface PDFConverter {
	public void convert2PDF(String inputFile,String pdfFile,String extend);
	public void convert2PDF(String inputFile,String extend);

}
